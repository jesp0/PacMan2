package com.example.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Inky extends Spokelse{
        public Inky(double x, double y){
            super(x,y);
            boks = lagBoks(x,y);
            posisjon.setFill(Color.CYAN);
            retning = "Nord";
            poly.setFill(Color.CYAN);
        }
    public String logikk(String gammelRetning) {
        String nyRetning = "";
        int random = trekkTall(1, 100);
        double dX = Spill.pacMan.posisjon.getCenterX() - posisjon.getCenterX();
        double dY = Spill.pacMan.posisjon.getCenterY() - posisjon.getCenterY();
        double diff = Math.abs(dX) - Math.abs(dY);

        // Pacman er sør-øst for Inky, nærmere øst enn sør
        if (dX > 0 && dY > 0 && diff > 0) {
            if (random > 0 && random <= 60)
                return "Sør";
            if (random > 60 && random <= 90)
                return "Øst";
            if (random > 90 && random <= 95)
                return "Vest";
            if (random > 95 && random <= 100)
                return "Nord";
        }
        // Pacman er sør-øst, nærmere sør enn øst
        if (dX > 0 && dY > 0 && diff < 0) {
            if (random > 0 && random <= 60)
                return "Øst";
            if (random > 60 && random <= 90)
                return "Sør";
            if (random > 90 && random <= 95)
                return "Vest";
            if (random > 95 && random <= 100)
                return "Nord";
        }
        // Pacman er sør-vest, nærmere vest enn sør
        if (dX < 0 && dY > 0 && diff > 0) {
            if (random > 0 && random <= 60)
                return "Sør";
            if (random > 60 && random <= 90)
                return "Vest";
            if (random > 90 && random <= 95)
                return "Vest";
            if (random > 95 && random <= 100)
                return "Nord";
        }
        // Pacman er sør-vest, nærmere sør enn vest
        if (dX < 0 && dY > 0 && diff < 0) {
            if (random > 0 && random <= 60)
                return "Vest";
            if (random > 60 && random <= 90)
                return "Sør";
            if (random > 90 && random <= 95)
                return "Øst";
            if (random > 95 && random <= 100)
                return "Nord";
        }
        // Pacman er nord-vest, nærmere vest enn nord
        if (dX < 0 && dY < 0 && diff > 0) {
            if (random > 0 && random <= 60)
                return "Nord";
            if (random > 60 && random <= 90)
                return "Vest";
            if (random > 90 && random <= 95)
                return "Øst";
            if (random > 95 && random <= 100)
                return "Sør";
        }
        // Pacman er nord-vest, nærmere nord enn vest
        if (dX < 0 && dY < 0 && diff < 0) {
            if (random > 0 && random <= 60)
                return "Vest";
            if (random > 60 && random <= 90)
                return "Nord";
            if (random > 90 && random <= 95)
                return "Sør";
            if (random > 95 && random <= 100)
                return "Øst";
        }
        // Pacman er nord-øst, nærmere nord enn øst
        if (dX > 0 && dY < 0 && diff < 0) {
            if (random > 0 && random <= 60)
                return "Øst";
            if (random > 60 && random <= 90)
                return "Nord";
            if (random > 90 && random <= 95)
                return "Sør";
            if (random > 95 && random <= 100)
                return "Vest";
        }
        // Pacman er nord-øst, nærmere øst enn nord
        if (dX > 0 && dY < 0 && diff > 0) {
            if (random > 0 && random <= 60)
                return "Nord";
            if (random > 60 && random <= 90)
                return "Øst";
            if (random > 90 && random <= 95)
                return "Sør";
            if (random > 95 && random <= 100)
                return "Vest";
        }
        // Pacman er rett over
        if(dX == 0 && dY < 0){
            return "Nord";
        }
        // Pacman er rett under
        if(dX == 0 && dY > 0){
            return "Sør";
        }
        // Pacman er rett vest
        if(dX < 0 && dY == 0){
            return "Vest";
        }
        // Pacman er rett øst
        if(dX > 0 && dY == 0){
            return "Øst";
        }
        return nyRetning;
    }

    public void utenforSjekk(){
        if (Spill.utenforHøyre.contains(boks) ){
            posisjon.setCenterX(-10);
            poly.setLayoutX(-270);
        }
        if (Spill.utenforVenstre.contains(boks) ){
            posisjon.setCenterX(580);
            poly.setLayoutX(320);
        }
    }
    public void skremtSpokelse() {
        Animasjoner.pauseSpokelser();
        posisjon.setFill(Color.BLUE);
        poly.setFill(Color.BLUE);
        spokelseSkremt = new Timeline(
                new KeyFrame(Duration.millis(35), e -> bevegelse()));
        spokelseSkremt.setCycleCount(200);

        spokelseSkremt.setOnFinished(e -> nullStill());
        spokelseSkremt.play();
    }
    public void nullStill(){
        Spill.inky.posisjon.setFill(Color.CYAN);
        Spill.inky.poly.setFill(Color.CYAN);
        erSkremt = false;
        Animasjoner.inkyAnimation.play();
    }
    public void nullStillHelt(){
        nullStill();
        posisjon.setCenterX(Spill.BRETTLENGDE/2-20);
        posisjon.setCenterY(Spill.BRETTHOYDE/2-20);
        poly.setLayoutX(0);
        poly.setLayoutY(0);

    }


}

