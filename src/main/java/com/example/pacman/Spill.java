package com.example.pacman;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Spill extends Application {
    private static final int BRETTHOYDE = 620;
    private static final int BRETTLENGDE = 560;
    //Pixlene er kvadratiske og trenger kun 1 verdi.
    private static final int PIXEL = 20;
    static PacMan pacMan; // = new PacMan();
    static Blinky blinky;
    protected static Pane spillbrett;
    double pacX, pacY; // = pacMan.posisjon.getCenterX(); // = pacMan.posisjon.getCenterY();
    protected BoundingBox pacBoks;
    protected static Animation animation;
    protected static Animation pacAnimation;
    protected static Animation blinkyAnimation;
    protected static String retningSjekk;
    public static Text score = new Text("0");
    public static int poengsum = 0;
    public static ArrayList<Vegg> veggListe = new ArrayList<>();
    public static ArrayList<LitenPrikk> litenPrikkListe = new ArrayList<>();
    public static ArrayList<StorPrikk> storPrikkListe = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {

        spillbrett = new Pane();;

        score.setStroke(Color.WHITE);
        score.setFill(Color.WHITE);
        score.setX(5);
        score.setY(18);
        score.setFont(new Font(20));
        spillbrett.getChildren().add(score);
        kartTolking(Kart.kartInnlesing());

        pacMan = new PacMan(BRETTLENGDE/2,BRETTHOYDE*0.75-14);
        spillbrett.getChildren().add(pacMan.posisjon);

        blinky = new Blinky(BRETTLENGDE/2,BRETTHOYDE/2-58);
        spillbrett.getChildren().add(blinky.posisjon);

        Scene scene = new Scene(spillbrett, BRETTLENGDE, BRETTHOYDE);
        scene.setFill(Color.BLACK);


        // Ved tastetrykk endres retning
        scene.setOnKeyPressed(e ->{
            pacMan.posisjon.setLength(270);
            switch ((e.getCode())){ //enhanced switch?
                //Prøver å fikse et problem som gjør at PacMan setter seg fast i veggen.
                case UP : if(pacMan.ret != "Nord") {
                                pacMan.bevegelse("Nord");
                                pacMan.posisjon.setStartAngle(135);
                                break;
                            }
                case DOWN : if(pacMan.ret != "Sør"){
                                pacMan.bevegelse("Sør");
                                pacMan.posisjon.setStartAngle(315);
                                break;
                            }
                case LEFT : if(pacMan.ret != "Vest"){
                                pacMan.bevegelse("Vest");
                                pacMan.posisjon.setStartAngle(225);
                                break;
                            }
                case RIGHT : if(pacMan.ret != "Øst"){
                                pacMan.bevegelse("Øst");
                                pacMan.posisjon.setStartAngle(45);
                            }
            }
        });
        animation = new Timeline(
                new KeyFrame(Duration.millis(15), e -> pacMan.bevegelse(pacMan.ret)));
        animation.setCycleCount(Timeline.INDEFINITE);

        pacAnimation = new Timeline(
                new KeyFrame(Duration.millis(5), e -> pacMan.pacAnimasjon(pacMan.posisjon, pacMan.ret)));
        pacAnimation.setCycleCount(Timeline.INDEFINITE);

        blinkyAnimation = new Timeline(
                new KeyFrame(Duration.millis(15), e -> blinky.bevegelse()));
        blinkyAnimation.setCycleCount(Timeline.INDEFINITE);

        stage.setResizable(false);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

    // 500 byttes etterhvert ut med distansen til nærmeste vegg i riktig retning?


    public static void kartTolking(ArrayList<String> kart){
        double x = 0, y = 0;
        for(int i = 0; i< kart.size(); i++){
            for(int k = 0; k < kart.get(i).length(); k++){
                switch (kart.get(i).charAt(k)){
                    case '#' :  Vegg vegg = new Vegg(x,y);
                                veggListe.add(vegg);
                                Rectangle v = vegg.tegnVegg(vegg);
                                spillbrett.getChildren().add(v);
                                ArrayList<Rectangle> fancy = new ArrayList<>();
                                fancy = vegg.fancyVegg(vegg);
                                for (int j = 0; j< fancy.size(); j++)
                                    spillbrett.getChildren().add(fancy.get(j)); break;

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
    public static void reset(){
        pacMan.lever = true;
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE*0.75-14);

        blinky.posisjon.setCenterX(BRETTLENGDE/2);
        blinky.posisjon.setCenterY(BRETTHOYDE/2-58);
    }



    public static void main(String[] args) {
        launch();
    }
}