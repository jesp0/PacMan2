package com.example.pacman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Spill extends Application {
    private final int BRETTHOYDE = 600;
    private final int BRETTLENGDE = 800;
    PathTransition pt;
    PacMan pacMan; // = new PacMan();

    double pacX, pacY; // = pacMan.posisjon.getCenterX(); // = pacMan.posisjon.getCenterY();


    @Override
    public void start(Stage stage) throws IOException {

        kartInnlesing();


        Pane spillbrett = new Pane();
       // PacMan pacMan = new PacMan();
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
        pt.setCycleCount(1);
        pt.setInterpolator(Interpolator.LINEAR);
        //System.out.println(pt.getOnFinished().toString());
        pt.play();


        // Setter ny Y-verdi basert på hvor langt pacman har beveget seg (hva skal stå i stedet for 50?)
        // Sett ny x/y når pacman endrer retning?
        // Kontinuerlig endre x/y etterhvert som pacman beveger seg?
        // Vi må kanskje ha kontinuerlig endring av x/y for å vite om PacMan spiser de prikkene?
        //pacMan.posisjon.setCenterY(pacY-=50);
    }
    // 500 byttes etterhvert ut med distansen til nærmeste vegg i riktig retning?
    public void tegnLinje(String retning){
        if(retning.equals("Nord")){ // bedre med switch?
            pt = new PathTransition(Duration.millis(500), new Line(pacX, pacY, pacX , pacY-50), pacMan.posisjon);
            pacMan.posisjon.setCenterY(pacY-50);
            //pt.setOnFinished (e -> {pacMan.posisjon.setCenterY(pacY-50);});

        }else if(retning.equals("Sør")){
            pt = new PathTransition(Duration.millis(500), new Line(pacX, pacY, pacX , pacY+50), pacMan.posisjon);
            pacMan.posisjon.setCenterY(pacY+50);
            //pt.setOnFinished (e -> {pacMan.posisjon.setCenterY(pacY+50);});

        }else if(retning.equals("Vest")){
            pt = new PathTransition(Duration.millis(500), new Line(pacX, pacY, pacX-50 , pacY), pacMan.posisjon);
            pacMan.posisjon.setCenterX(pacX-50);
            //pt.setOnFinished (e -> {pacMan.posisjon.setCenterX(pacX-50);});

        }else{ // i vårt tilfelle kan retning bare være "Øst" når den ikke er noen av de andre
            pt = new PathTransition(Duration.millis(500), new Line(pacX, pacY, pacX+50 , pacY), pacMan.posisjon);
            pacMan.posisjon.setCenterX(pacX+50);
            //pt.setOnFinished (e -> {pacMan.posisjon.setCenterX(pacX+50);});

        }
    }

    public void kartInnlesing(){
        try{
            File fil = new File("src/main/java/com/example/pacman/Kart.txt");
            Scanner leser = new Scanner(fil);
            ArrayList<String> test = new ArrayList<>();
            while(leser.hasNext()) {
                String hei = leser.next();
                test.add(hei);
            }
            System.out.println(test.size());
            System.out.println(test);
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet - " + e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}