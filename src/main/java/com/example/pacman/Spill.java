package com.example.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spill extends Application {
    private final int BRETTHOYDE = 600;
    private final int BRETTLENGDE = 800;
    PacMan pacMan; // = new PacMan();
    double pacX, pacY; // = pacMan.posisjon.getCenterX(); // = pacMan.posisjon.getCenterY();
    @Override
    public void start(Stage stage) throws IOException {

        Kart.kartInnlesing();

        Pane spillbrett = new Pane();;
        pacMan = new PacMan();
        // PacMans startposisjon
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE/2);
        spillbrett.getChildren().add(pacMan.posisjon);

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


    public static void main(String[] args) {
        launch();
    }
}