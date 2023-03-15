package com.example.pacman;

import javafx.scene.paint.Color;

public class Clyde extends Spokelse{
    public Clyde(double x, double y){
        super(x,y);
        boks = lagBoks(x,y);
        posisjon.setFill(Color.ORANGE);
        retning = "Nord";
        poly.setFill(Color.ORANGE);
    }

    public String logikk(String gammelRetning){
        String nyRetning = "";
        int random = trekkTall(1,3);
        switch (gammelRetning){
            case "Nord": switch (random){
                case 1: nyRetning = "Øst"; ;break;
                case 2: nyRetning = "Vest"; break;
                case 3: nyRetning = "Nord"; break;
            } break;
            case "Sør": switch (random){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Vest"; break;
            } break;
            case "Øst": switch (random){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Nord"; break;
            } break;
            case "Vest": switch (random){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Vest"; break;
                case 3: nyRetning = "Nord"; break;
            }

                return nyRetning;
        }

        return nyRetning;
    }

    public void utenforSjekk(){
        if (Spill.utenforHøyre.contains(boks) ){
            posisjon.setCenterX(-10);
            poly.setLayoutX(-310);
        }
        if (Spill.utenforVenstre.contains(boks) ){
            posisjon.setCenterX(580);
            poly.setLayoutX(280);
        }
    }
    public void nullStill(){
        Spill.clyde.posisjon.setFill(Color.ORANGE);
        Spill.clyde.poly.setFill(Color.ORANGE);
        erSkremt = false;
        Animasjoner.clydeAnimation.play();
    }
    public void nullStillHelt(){
        nullStill();
        posisjon.setCenterX(Spill.BRETTLENGDE/2+20);
        posisjon.setCenterY(Spill.BRETTHOYDE/2-20);
        poly.setLayoutX(0);
        poly.setLayoutY(0);
        Spill.spillbrett.getChildren().add(posisjon);
        Spill.spillbrett.getChildren().add(poly);
    }
}
