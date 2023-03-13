package com.example.pacman;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
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
    static PacMan pacMan;
    static Spokelse blinky, inky, pinky, clyde;
    protected static Pane spillbrett;
    protected static Animation animation;
    protected static Animation pacAnimation, deathAnimation;
    protected static Animation blinkyAnimation, inkyAnimation, pinkyAnimation, clydeAnimation;
    protected static String retningSjekk;
    public static Text score = new Text("0");
    public static Text gameoverTekst;
    public static int poengsum = 0;
    public static ArrayList<Vegg> veggListe = new ArrayList<>();
    public static ArrayList<Kryss> kryssListe = new ArrayList<>();
    public static ArrayList<LitenPrikk> litenPrikkListe = new ArrayList<>();
    public static ArrayList<StorPrikk> storPrikkListe = new ArrayList<>();
    protected static BoundingBox utenforVenstre = new BoundingBox(-21,270,20,60);
    protected static BoundingBox utenforHøyre = new BoundingBox(581,270,20,60);
    public static int antLiv = 3;
    static Rectangle button;
    @Override
    public void start(Stage stage) throws IOException {

        spillbrett = new Pane();;


        nyttSpill();
        Scene scene = new Scene(spillbrett, BRETTLENGDE, BRETTHOYDE);
        scene.setFill(Color.BLACK);

        // Ved tastetrykk endres retning
        scene.setOnKeyPressed(e ->{
            pacMan.posisjon.setLength(270);
            switch ((e.getCode())){ //enhanced switch?
                //Prøver å fikse et problem som gjør at PacMan setter seg fast i veggen.
                case UP :   pacMan.bevegelse("Nord");
                            pacMan.posisjon.setStartAngle(135);
                            break;

                case DOWN : pacMan.bevegelse("Sør");
                            pacMan.posisjon.setStartAngle(315);
                            break;
                case LEFT : pacMan.bevegelse("Vest");
                            pacMan.posisjon.setStartAngle(225);
                            break;

                case RIGHT : pacMan.bevegelse("Øst");
                             pacMan.posisjon.setStartAngle(45);
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

        inkyAnimation = new Timeline(
                new KeyFrame(Duration.millis(15), e -> inky.bevegelse()));
        inkyAnimation.setCycleCount(Timeline.INDEFINITE);

        pinkyAnimation = new Timeline(
                new KeyFrame(Duration.millis(15), e -> pinky.bevegelse()));
        pinkyAnimation.setCycleCount(Timeline.INDEFINITE);

        clydeAnimation = new Timeline(
                new KeyFrame(Duration.millis(15), e -> clyde.bevegelse()));
        clydeAnimation.setCycleCount(Timeline.INDEFINITE);

        stage.setResizable(false);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

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

                    case 'K' : Kryss kryss = new Kryss(x,y);
                               kryssListe.add(kryss);
                    case 'D' : LitenPrikk litenPrikk = new LitenPrikk(x,y);
                                litenPrikkListe.add(litenPrikk);
                                spillbrett.getChildren().add(litenPrikk.posisjon); break;
                    case 'R' :  break;
                    case 'B' : StorPrikk storPrikk = new StorPrikk(x,y);
                                storPrikkListe.add(storPrikk);
                                spillbrett.getChildren().add(storPrikk.posisjon); break;
                    case 'G' : Kryss kryssUten = new Kryss(x,y);
                                kryssListe.add(kryssUten); break;
                }
                x += PIXEL;
            }
            y += PIXEL;
            x = 0;
        }
    }
    public static void reset(){
        pacMan.lever = true;
        pacMan.posisjon.setStartAngle(45);
        pacMan.posisjon.setLength(270);
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE*0.75-14);

        blinky.posisjon.setCenterX(BRETTLENGDE/2);
        blinky.posisjon.setCenterY(BRETTHOYDE/2-60);
        blinky.poly.setLayoutX(0);
        blinky.poly.setLayoutY(0);

        inky.posisjon.setCenterX(BRETTLENGDE/2-20);
        inky.posisjon.setCenterY(BRETTHOYDE/2-20);
        inky.poly.setLayoutX(0);
        inky.poly.setLayoutY(0);

        pinky.posisjon.setCenterX(BRETTLENGDE/2);
        pinky.posisjon.setCenterY(BRETTHOYDE/2-20);
        pinky.poly.setLayoutX(0);
        pinky.poly.setLayoutY(0);

        clyde.posisjon.setCenterX(BRETTLENGDE/2+20);
        clyde.posisjon.setCenterY(BRETTHOYDE/2-20);
        clyde.poly.setLayoutX(0);
        clyde.poly.setLayoutY(0);

        // Fjerner et hjerte


    }

    public static void nyttSpill(){
        // Tanken er at denne metoden skal kjøre når bruker trykker "Prøv igjen"
        score.setStroke(Color.WHITE);
        score.setFill(Color.WHITE);
        score.setX(5);
        score.setY(18);
        score.setFont(new Font(20));
        spillbrett.getChildren().remove(score);

        spillbrett.getChildren().clear();

        if(PacMan.hjerteliste != null)
            PacMan.hjerteliste.clear();
        if(veggListe != null)
            veggListe.clear();
        if(kryssListe != null)
            kryssListe.clear();
        if(litenPrikkListe != null)
            litenPrikkListe.clear();
        if (storPrikkListe != null)
            storPrikkListe.clear();
        PacMan.tegnHjerter();
        score.setText("0");
        spillbrett.getChildren().add(score);
        poengsum = 0;
        antLiv = 3;
        if(gameoverTekst != null)
            gameoverTekst.setText("");

        kartTolking(Kart.kartInnlesing());

        pacMan = new PacMan(BRETTLENGDE/2,BRETTHOYDE*0.75-14);
        spillbrett.getChildren().add(pacMan.posisjon);

        blinky = new Blinky(BRETTLENGDE/2,BRETTHOYDE/2-60);
        spillbrett.getChildren().add(blinky.posisjon);
        spillbrett.getChildren().add(blinky.poly);

        pinky = new Pinky(BRETTLENGDE/2,BRETTHOYDE/2-20);
        spillbrett.getChildren().add(pinky.posisjon);
        spillbrett.getChildren().add(pinky.poly);

        inky = new Inky(BRETTLENGDE/2-20, BRETTHOYDE/2-20);
        spillbrett.getChildren().add(inky.posisjon);
        spillbrett.getChildren().add(inky.poly);

        clyde = new Clyde(BRETTLENGDE/2+20, BRETTHOYDE/2-20);
        spillbrett.getChildren().add(clyde.posisjon);
        spillbrett.getChildren().add(clyde.poly);
    }

    public static void gameoverSjekk(){
        if(antLiv == 0) {
            System.out.println("GAME OVER");
            gameoverTekst = new Text("GAME OVER");
            gameoverTekst.setFont(new Font(80));
            gameoverTekst.setStroke(Color.RED);
            gameoverTekst.setFill(Color.DARKRED);
            gameoverTekst.setX(BRETTLENGDE/11);
            gameoverTekst.setY(BRETTHOYDE/2);
            spillbrett.getChildren().add(gameoverTekst);

            button = new Rectangle(100,20);
            button.setFill(Color.YELLOW);
            button.setLayoutX(BRETTLENGDE/2-50);
            button.setLayoutY(BRETTHOYDE/2+100);
            button.setOnMouseClicked(e-> nyttSpill());
            spillbrett.getChildren().add(button);

            //nyttSpill(); // kjøres når prøv igjen knappen er trykket?
        }
        PacMan.hjerteliste.get(Spill.antLiv).setFill(Color.BLACK);

        deathAnimation = new Timeline(
                new KeyFrame(Duration.millis(20), e -> pacMan.dødsAnimasjon(pacMan.posisjon)));
        deathAnimation.setCycleCount(100);
        deathAnimation.setOnFinished(e -> reset());
        deathAnimation.play();
    }

    public static void main(String[] args) {
        launch();
    }
}