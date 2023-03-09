package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StorPrikk extends Entitet {

        protected Circle posisjon;
        public StorPrikk(double x, double y) {
            super(x, y);
            boks = lagBoks(x,y);
            posisjon = new Circle(x+10, y+10, 5.0, Color.LIGHTSALMON);
        }
        public static void spisStorPrikk(){
        for(int i=0; i<Spill.storPrikkListe.size();i++) {
            if (Spill.pacMan.boks.intersects(Spill.storPrikkListe.get(i).boks)) {
                Spill.spillbrett.getChildren().remove(Spill.storPrikkListe.get(i).posisjon);
                Spill.storPrikkListe.remove(i);
            }
        }
    }


    }

