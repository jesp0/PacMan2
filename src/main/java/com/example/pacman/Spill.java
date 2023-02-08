package com.example.pacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Spill extends Application {
    private final int BRETTHOYDE = 600;
    private final int BRETTLENGDE = 800;
    @Override
    public void start(Stage stage) throws IOException {


        Pane spillbrett = new Pane();
        PacMan pacMan = new PacMan();
        pacMan.setCenterX(BRETTLENGDE/2);
        pacMan.setCenterY(BRETTHOYDE/2);
        spillbrett.getChildren().add(pacMan.pman);


        Scene scene = new Scene(spillbrett, 800, 600);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}