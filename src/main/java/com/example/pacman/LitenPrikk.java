package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Oppretter små prikker og behandler hva som skjer når Pacman spiser en liten prikk
 */
public class LitenPrikk extends Entitet {
    protected Circle posisjon;

    /**
     * En liten prikk har en x og y verdi, en BoundingBox og en sirkel
     * @param x
     * @param y
     */
    public LitenPrikk(double x, double y) {
        super(x, y);
        boks = lagBoks(x,y);
        posisjon = new Circle(x+10, y+10, 3.0, Color.LIGHTSALMON);
    }

    /**
     * De små prikkene fjernes fra Pane etterhvert som de blir spist.
     * Poengsum oppdateres også.
     */
    public static void spisPrikk(){
        for(int i=0; i<Spill.litenPrikkListe.size();i++){
            if(Spill.pacMan.boks.intersects(Spill.litenPrikkListe.get(i).boks)) {
                Spill.spillbrett.getChildren().remove(Spill.litenPrikkListe.get(i).posisjon);
                Spill.litenPrikkListe.remove(i);
                oppdaterScore();
                Spill.win();
            }
        }
    }
    /**
     * Oppdaterer poengsum med 10
     */
    public static void oppdaterScore(){
        Spill.poengsum += 10;
        Spill.score.setText("" + Spill.poengsum);
    }


}
