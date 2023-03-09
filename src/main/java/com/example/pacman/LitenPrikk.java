package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class LitenPrikk extends Entitet {
    protected Circle posisjon;
    private static int teller = 0;
    public LitenPrikk(double x, double y) {
        super(x, y);
        boks = lagBoks(x,y);
        posisjon = new Circle(x+10, y+10, 3.0, Color.LIGHTSALMON);
    }
    public static void spisPrikk(){
        for(int i=0; i<Spill.litenPrikkListe.size();i++){
            if(Spill.pacMan.boks.intersects(Spill.litenPrikkListe.get(i).boks)) {
                Spill.spillbrett.getChildren().remove(Spill.litenPrikkListe.get(i).posisjon);
                Spill.litenPrikkListe.remove(i);
                oppdaterScore();
            }
        }
    }
    public static void oppdaterScore(){
        Spill.poengsum += 10;
        Spill.score.setText("" + Spill.poengsum);
    }


}
