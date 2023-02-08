package com.example.pacman;

import javafx.application.Application;
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
    @Override
    public void start(Stage stage) throws IOException {


        Pane spillbrett = new Pane();
        PacMan pacMan = new PacMan();
        pacMan.pman.setCenterX(BRETTLENGDE/2);
        pacMan.pman.setCenterY(BRETTHOYDE/2);
        spillbrett.getChildren().add(pacMan.pman);

        PathTransition pt = new PathTransition(Duration.millis(10000),
                new Line(100, 200, 100, 0), pacMan.pman);
        pt.setCycleCount(5);
        pt.play();


        Scene scene = new Scene(spillbrett, 800, 600);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}