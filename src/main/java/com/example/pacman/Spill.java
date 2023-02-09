package com.example.pacman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

import java.io.IOException;

public class Spill extends Application {
    private final int BRETTHOYDE = 600;
    private final int BRETTLENGDE = 800;
    PathTransition pt;
    PacMan pacMan; // = new PacMan();

    double pacX; // = pacMan.posisjon.getCenterX();
    double pacY; // = pacMan.posisjon.getCenterY();

    @Override
    public void start(Stage stage) throws IOException {

        Pane spillbrett = new Pane();
       // PacMan pacMan = new PacMan();
        pacMan = new PacMan();
        // PacMans startposisjon
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE/2);
        spillbrett.getChildren().add(pacMan.posisjon);

        Scene scene = new Scene(spillbrett, BRETTLENGDE, BRETTHOYDE);

        // Ved tastetrykk endres retning
        scene.setOnKeyPressed(e ->{
            switch ((e.getCode())){ //enhanced switch?
                case UP : pacBevegelse("Nord"); break;
                case DOWN : pacBevegelse("Sør"); break;
                case LEFT : pacBevegelse("Vest"); break;
                case RIGHT : pacBevegelse("Øst");
            }
        });

        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

    public void pacBevegelse(String retning){
        pacMan.ret = retning;
        // Henter oppdatert posisjon
        pacX = pacMan.posisjon.getCenterX();
        pacY = pacMan.posisjon.getCenterY();
        // Oppretter ny linje (fra pacman til nærmeste vegg i retning nord)
        // pt = new PathTransition(Duration.millis(5000), new Line(pacX, pacY, pacX , pacY-500), pacMan.posisjon);
        tegnLinje(retning);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();

        // Setter ny Y-verdi basert på hvor langt pacman har beveget seg (hva skal stå i stedet for 50?)
        // Sett ny x/y når pacman endrer retning?
        // Kontinuerlig endre x/y etterhvert som pacman beveger seg?
        // Vi må kanskje ha kontinuerlig endring av x/y for å vite om PacMan spiser de prikkene?
        pacMan.posisjon.setCenterY(pacY-=50);
    }
    // 500 byttes etterhvert ut med distansen til nærmeste vegg i riktig retning?
    public void tegnLinje(String retning){
        if(retning.equals("Nord")){ // bedre med switch?
            pt = new PathTransition(Duration.millis(5000), new Line(pacX, pacY, pacX , pacY-500), pacMan.posisjon);
        }else if(retning.equals("Sør")){
            pt = new PathTransition(Duration.millis(5000), new Line(pacX, pacY, pacX , pacY+500), pacMan.posisjon);
        }else if(retning.equals("Vest")){
            pt = new PathTransition(Duration.millis(5000), new Line(pacX, pacY, pacX-500 , pacY), pacMan.posisjon);
        }else{ // i vårt tilfelle kan retning bare være "Øst" når den ikke er noen av de andre
            pt = new PathTransition(Duration.millis(5000), new Line(pacX, pacY, pacX+500 , pacY), pacMan.posisjon);
        }
    }
    /*
    // Pacman beverger seg oppover på spillbrettet
    public void nord(){
        pacMan.ret = "Nord";
        pacX = pacMan.posisjon.getCenterX();
        pacY = pacMan.posisjon.getCenterY();
        // Oppretter ny linje (fra pacman til nærmeste vegg i retning nord)
        pt = new PathTransition(Duration.millis(5000),
                new Line(pacX, pacY, pacX , pacY-500), pacMan.posisjon);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();
        pacMan.posisjon.setCenterY(pacY-=50);
    }
    public void sør(){
        pacMan.ret = "Sør";
        pacX = pacMan.posisjon.getCenterX();
        pacY = pacMan.posisjon.getCenterY();
        pt = new PathTransition(Duration.millis(5000),
                new Line(pacX, pacY, pacX , pacY+500), pacMan.posisjon);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();
        pacMan.posisjon.setCenterY(pacY+=50);
    }
    public void vest(){
        pacMan.ret = "Vest";
        pacX = pacMan.posisjon.getCenterX();
        pacY = pacMan.posisjon.getCenterY();
        pt = new PathTransition(Duration.millis(5000),
                new Line(pacX, pacY, pacX-500 , pacY), pacMan.posisjon);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();
        pacMan.posisjon.setCenterX(pacX-=50);
    }
    public void øst(){
        pacMan.ret = "Øst";
        pacX = pacMan.posisjon.getCenterX();
        pacY = pacMan.posisjon.getCenterY();
        pt = new PathTransition(Duration.millis(5000),
                new Line(pacX, pacY, pacX+500 , pacY), pacMan.posisjon);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();
        pacMan.posisjon.setCenterX(pacX+=50);
    }

*/
    public static void main(String[] args) {
        launch();
    }
}