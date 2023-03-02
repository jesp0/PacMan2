package com.example.pacman;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Spill extends Application {
    private final int BRETTHOYDE = 620;
    private final int BRETTLENGDE = 560;
    //Pixlene er kvadratiske og trenger kun 1 verdi.
    private static final int PIXEL = 20;
    PacMan pacMan; // = new PacMan();
    ArrayList<String> byggkart = new ArrayList<>();
    private static Pane spillbrett;
    double pacX, pacY; // = pacMan.posisjon.getCenterX(); // = pacMan.posisjon.getCenterY();
    protected BoundingBox pacBoks;
    protected Animation animation;
    protected String retningSjekk;
    public static ArrayList<Vegg> veggListe = new ArrayList<>();
    public static ArrayList<LitenPrikk> litenPrikkListe = new ArrayList<>();
    public static ArrayList<StorPrikk> storPrikkListe = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {



        spillbrett = new Pane();;
        pacMan = new PacMan(BRETTLENGDE/2,BRETTHOYDE*0.75+5);

        spillbrett.getChildren().add(pacMan.posisjon);
        byggkart = Kart.kartInnlesing();
        kartTolking(byggkart);
/*
        Line l = new Line(250,250,250,500);
        l.setStroke(Color.BLUE);
        l.setStrokeWidth(5);
        spillbrett.getChildren().add(l);

        //boks = new BoundingBox(249,250,1,250);
*/

        Scene scene = new Scene(spillbrett, BRETTLENGDE, BRETTHOYDE);
        scene.setFill(Color.BLACK);


        // Ved tastetrykk endres retning
        scene.setOnKeyPressed(e ->{
            switch ((e.getCode())){ //enhanced switch?
                //Prøver å fikse et problem som gjør at PacMan setter seg fat i veggen.
                case UP : if(pacMan.ret != "Nord")
                                pacBevegelse("Nord");  break;
                case DOWN : if(pacMan.ret != "Sør")
                                pacBevegelse("Sør"); break;
                case LEFT : if(pacMan.ret != "Vest")
                                pacBevegelse("Vest"); break;
                case RIGHT : if(pacMan.ret != "Øst")
                                pacBevegelse("Øst");
            }
        });
        animation = new Timeline(
                new KeyFrame(Duration.millis(15), e -> pacBevegelse(pacMan.ret)));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        //System.out.println(veggListe.size());

        stage.setResizable(false);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }
    public void pacBevegelse(String retning){
        pacMan.ret = retning;
        // Henter oppdatert posisjon
        pacX = pacMan.posisjon.getCenterX();
        pacY = pacMan.posisjon.getCenterY();
        tegnLinje(retning);
        //Oppdaterer BoundingBoksen til PacMan.
        pacBoks = new BoundingBox(pacX-7,pacY-7,14,14);
        kollisjonSjekk(retning);
        spisPrikk();
        spisStorPrikk();
        if(retningSjekk != retning) {
            animation.play();

        }
    }
    // 500 byttes etterhvert ut med distansen til nærmeste vegg i riktig retning?
    public void tegnLinje(String retning){
        if(retning.equals("Nord")){ // bedre med switch?
            pacMan.posisjon.setCenterY(pacY - 1);
        }else if(retning.equals("Sør")){
            pacMan.posisjon.setCenterY(pacY+1);
        }else if(retning.equals("Vest")){
            pacMan.posisjon.setCenterX(pacX-1);
        }else if(retning.equals("Øst")){
            pacMan.posisjon.setCenterX(pacX+1);
        }
    }

    public static void kartTolking(ArrayList<String> kart){
        double x = 0, y = 0;
        for(int i = 0; i< kart.size(); i++){
            for(int k = 0; k < kart.get(i).length(); k++){
                switch (kart.get(i).charAt(k)){
                    case '#' :  Vegg vegg = new Vegg(x,y);
                                veggListe.add(vegg);
                                Rectangle v = vegg.tegnVegg(vegg);
                                spillbrett.getChildren().add(v); break;
                    case 'G' :  break;
                    case 'D' : LitenPrikk litenPrikk = new LitenPrikk(x,y);
                                litenPrikkListe.add(litenPrikk);
                                spillbrett.getChildren().add(litenPrikk.posisjon); break;
                    case 'R' :  break;
                    case 'B' : StorPrikk storPrikk = new StorPrikk(x,y);
                                storPrikkListe.add(storPrikk);
                                spillbrett.getChildren().add(storPrikk.posisjon); break;
                    case 'Ø' : //System.out.println("Dør");  break;
                }
                x += PIXEL;
            }
            y += PIXEL;
            x = 0;
        }
    }

    public void kollisjonSjekk(String retning){
        for(int i=0; i<veggListe.size();i++){
            if(pacBoks.intersects(veggListe.get(i).boks)) {
                System.out.println("" + i + veggListe.get(i).boks.toString());
                System.out.println("X: "+pacMan.posisjon.getCenterX() + " Y: " + pacMan.posisjon.getCenterY());
                animation.pause();
                retningSjekk = retning;
                switch (retning) {
                    case "Nord":
                        pacMan.posisjon.setCenterY(pacY + 1);
                        break;
                    case "Sør":
                        pacMan.posisjon.setCenterY(pacY - 1);
                        break;
                    case "Vest":
                        pacMan.posisjon.setCenterX(pacX + 1);
                        break;
                    case "Øst":
                        pacMan.posisjon.setCenterX(pacX - 1);
                        break;
                }
            }
        }
    }
    public void spisPrikk(){
        for(int i=0; i<litenPrikkListe.size();i++){
            if(pacBoks.intersects(litenPrikkListe.get(i).boks)) {
                spillbrett.getChildren().remove(litenPrikkListe.get(i).posisjon);
                litenPrikkListe.remove(i);
            }
        }
    }
    public void spisStorPrikk(){
        for(int i=0; i<storPrikkListe.size();i++) {
            if (pacBoks.intersects(storPrikkListe.get(i).boks)) {
                spillbrett.getChildren().remove(storPrikkListe.get(i).posisjon);
                storPrikkListe.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}