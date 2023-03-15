package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StorPrikk extends Entitet {

    protected Circle posisjon;

    /**
     * En stor prikk har en x og y verdi, en BoundingBox og en sirkel
     * @param x
     * @param y
     */
    public StorPrikk(double x, double y) {
        super(x, y);
        boks = lagBoks(x,y);
        posisjon = new Circle(x+10, y+10, 5.0, Color.LIGHTSALMON);
    }

    /**
     * Metoden sjekker om Pacmans boundingbox krysser en stor prikks boundingbox.
     * Dersom spørringen er true, fjernes den spesifikke prikken fra Pane, poengsum oppdateres
     * og spøkelsene blir "skremt".
     */
    public static void spisStorPrikk(){
        for(int i=0; i<Spill.storPrikkListe.size();i++) {
            if (Spill.pacMan.boks.intersects(Spill.storPrikkListe.get(i).boks)) {
                Spill.spillbrett.getChildren().remove(Spill.storPrikkListe.get(i).posisjon);
                Spill.storPrikkListe.remove(i);
                oppdaterScore();
                Spokelse.erSkremt = true;
                Spokelse.antSpist = 0;
                Spill.blinky.skremtSpokelse();
                Spill.inky.skremtSpokelse();
                Spill.pinky.skremtSpokelse();
                Spill.clyde.skremtSpokelse();
            }
        }
    }

    /**
     * Oppdaterer poengsum med 50
     */
    public static void oppdaterScore(){
        Spill.poengsum += 50;
        Spill.score.setText("" + Spill.poengsum);
    }

}

