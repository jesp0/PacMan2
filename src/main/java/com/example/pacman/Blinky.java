package com.example.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/** Blinky er det røde spøkelset. */
public class Blinky extends Spokelse{
    public Blinky(double x, double y){
        super(x,y);
        boks = lagBoks(x,y);
        posisjon.setFill(Color.RED);
        retning = "Vest";
        poly.setFill(Color.RED);
    }
    /** Blinky forsøker å ta gode valg her i livet. Han vet hvor pacman er i forhold til seg selv og har høy
     * sannsynlighet for å ta et valg som vil ta blinky nærmere pacman. Han har muligheten til å ta "dårlige valg"
     * dersom han stanger i veggen.*/
    public String logikk(String gammelRetning){
        String nyRetning = "";
        int random = trekkTall(1,100);
        double dX = Spill.pacMan.posisjon.getCenterX() - posisjon.getCenterX();
        double dY = Spill.pacMan.posisjon.getCenterY() - posisjon.getCenterY();
        double diff = Math.abs(dX) - Math.abs(dY);

        if(dX > 0 && dY > 0 && diff > 0){
            if(random <= 50)
                return "Sør";
            if(random > 50 && random <= 75)
                return "Øst";
            if(random > 75 && random <= 90)
                return "Nord";
            if(random > 90)
                return "Vest";
        }
        if(dX > 0 && dY > 0 && diff < 0){
            if(random <= 50)
                return "Øst";
            if(random > 50 && random <= 75)
                return "Sør";
            if(random > 75 && random <= 90)
                return "Vest";
            if(random > 90)
                return "Nord";
        }
        if(dX < 0 && dY > 0 && diff > 0){
            if(random <= 50)
                return "Sør";
            if(random > 50 && random <= 75)
                return "Vest";
            if(random > 75 && random <= 90)
                return "Nord";
            if(random > 90)
                return "Øst";
        }
        if(dX < 0 && dY > 0 && diff < 0){
            if(random <= 50)
                return "Vest";
            if(random > 50 && random <= 75)
                return "Sør";
            if(random > 75 && random <= 90)
                return "Øst";
            if(random > 90)
                return "Nord";
        }
        if(dX < 0 && dY < 0 && diff > 0){
            if(random <= 50)
                return "Nord";
            if(random > 50 && random <= 75)
                return "Vest";
            if(random > 75 && random <= 90)
                return "Øst";
            if(random > 90)
                return "Sør";
        }
        if(dX < 0 && dY < 0 && diff < 0){
            if(random <= 50)
                return "Vest";
            if(random > 50 && random <= 75)
                return "Nord";
            if(random > 75 && random <= 90)
                return "Sør";
            if(random > 90)
                return "Øst";
        }

        if(dX > 0 && dY < 0 && diff < 0){
            if(random <= 50)
                return "Øst";
            if(random > 50 && random <= 75)
                return "Nord";
            if(random > 75 && random <= 90)
                return "Sør";
            if(random > 90)
                return "Vest";
        }
        if(dX < 0 && dY < 0 && diff > 0){
            System.out.println("IntelliJ tar feil!");
            if(random <= 50)
                return "Nord";
            if(random > 50 && random <= 75)
                return "Øst";
            if(random > 75 && random <= 90)
                return "Vest";
            if(random > 90)
                return "Sør";
        }
        if(dX > 0 && dY < 0 && diff > 0){
            if(random <= 50)
                return "Øst";
            if(random > 50 && random <= 75)
                return "Nord";
            if(random > 75 && random <= 90)
                return "Vest";
            if(random > 90)
                return "Sør";
        }
        return nyRetning;
        }

    /** Denne sjekker tar Blinky fra den ene siden av tunellen til den andre.*/
    public void utenforSjekk(){
        if (Spill.utenforHøyre.contains(boks) ){
            posisjon.setCenterX(-10);
            poly.setLayoutX(-290);
        }
        if (Spill.utenforVenstre.contains(boks) ){
            posisjon.setCenterX(580);
            poly.setLayoutX(300);
        }
    }
    /** Denne animasjonen var opprinnelig felles men ble individuell da vi trengte å stoppe animasjonen dersom
     * spøkelset ble spist.*/
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
    /** Nulstiller farge og posisjon på spøkelset*/
    public void nullStill(){
        Spill.blinky.posisjon.setFill(Color.RED);
        Spill.blinky.poly.setFill(Color.RED);
        erSkremt = false;
        Animasjoner.blinkyAnimation.play();
    }
    public void nullStillHelt(){
        nullStill();
        posisjon.setCenterX(Spill.BRETTLENGDE/2);
        posisjon.setCenterY(Spill.BRETTHOYDE/2-60);
        poly.setLayoutX(0);
        poly.setLayoutY(0);
    }
}
