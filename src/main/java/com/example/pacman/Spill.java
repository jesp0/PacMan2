package com.example.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spill extends Application {
    private final int BRETTHOYDE = 620;
    private final int BRETTLENGDE = 560;
    PacMan pacMan; // = new PacMan();
    ArrayList<String> byggkart = new ArrayList<>();
    private static Pane spillbrett;
    double pacX, pacY; // = pacMan.posisjon.getCenterX(); // = pacMan.posisjon.getCenterY();
    @Override
    public void start(Stage stage) throws IOException {



        spillbrett = new Pane();;
        pacMan = new PacMan();
        // PacMans startposisjon
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE*0.75);
        spillbrett.getChildren().add(pacMan.posisjon);
        byggkart = Kart.kartInnlesing();
        kartTolking(byggkart);

        Scene scene = new Scene(spillbrett, BRETTLENGDE, BRETTHOYDE);
        scene.setFill(Color.BLACK);


        // Ved tastetrykk endres retning
        scene.setOnKeyPressed(e ->{
            switch ((e.getCode())){ //enhanced switch?
                case UP : pacBevegelse("Nord");  break;
                case DOWN : pacBevegelse("Sør"); break;
                case LEFT : pacBevegelse("Vest"); break;
                case RIGHT : pacBevegelse("Øst");
            }
        });
        Animation animation = new Timeline(
                new KeyFrame(Duration.millis(20), e -> pacBevegelse(pacMan.ret)));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

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
                    case '#' :  boolean venstre = false, hoyre = false, under = false, over = false;
                                if(k>0)
                                    if(kart.get(i).charAt(k - 1) == '#')
                                        venstre = true;
                                if(k < kart.get(i).length()-1)
                                    if(kart.get(i).charAt(k + 1) == '#')
                                        hoyre = true;
                                if(i>0)
                                    if(kart.get(i - 1).charAt(k) == '#')
                                        over = true;
                                if(i < kart.size()-1)
                                    if(kart.get(i + 1).charAt(k) == '#')
                                        under = true;
                                System.out.println(venstre + " " + hoyre + " " + over + " " + under);
                                Vegg vegg = new Vegg(venstre,hoyre,over,under, x, y);
                                Polyline v = vegg.tegnVegg(vegg);
                                spillbrett.getChildren().add(v); break;
                    case 'G' : //System.out.println("Spøkelse");  break;
                    case 'D' : //System.out.println("Liten prikk");  break;
                    case 'R' : //System.out.println("Tomrom!");  break;
                    case 'B' : //System.out.println("Bigboy");  break;
                    case 'Ø' : //System.out.println("Dør");  break;
                }
                x += 20;
            }
            y += 20;
            x = 0;
        }
    }


    public static void main(String[] args) {
        launch();
    }
}