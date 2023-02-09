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

    @Override
    public void start(Stage stage) throws IOException {


        Pane spillbrett = new Pane();
        PacMan pacMan = new PacMan();
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE/2);
        spillbrett.getChildren().add(pacMan.posisjon);

        Scene scene = new Scene(spillbrett, 800, 600);




        scene.setOnKeyPressed(e ->{
            switch ((e.getCode())){
                case UP : pacMan.ret = "Nord";
                    pt = new PathTransition(Duration.millis(5000),
                            new Line(pacMan.posisjon.getCenterX(), pacMan.posisjon.getCenterY(),pacMan.posisjon.getCenterX() , pacMan.posisjon.getCenterY()-500), pacMan.posisjon);
                    pt.setCycleCount(5);
                    pt.play(); break;
                case DOWN : pacMan.ret = "Sør"; pt = new PathTransition(Duration.millis(5000),
                        new Line(pacMan.posisjon.getCenterX(), pacMan.posisjon.getCenterY(),pacMan.posisjon.getCenterX() , pacMan.posisjon.getCenterY()+500), pacMan.posisjon);
                    pt.setCycleCount(5);
                    pt.play();break;
                case LEFT : pacMan.ret = "Vest"; pt = new PathTransition(Duration.millis(5000),
                        new Line(pacMan.posisjon.getCenterX(), pacMan.posisjon.getCenterY(),pacMan.posisjon.getCenterX()-500 , pacMan.posisjon.getCenterY()), pacMan.posisjon);
                    pt.setCycleCount(5);
                    pt.play();break;
                case RIGHT : pacMan.ret = "Øst"; pt = new PathTransition(Duration.millis(5000),
                        new Line(pacMan.posisjon.getCenterX(), pacMan.posisjon.getCenterY(),pacMan.posisjon.getCenterX()+500 , pacMan.posisjon.getCenterY()), pacMan.posisjon);
                    pt.setCycleCount(5);
                    pt.play();
            }
        });






        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}