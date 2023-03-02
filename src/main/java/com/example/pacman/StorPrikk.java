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
        public BoundingBox lagBoks(double x, double y){
            boks = new BoundingBox(x+10, y+10, 3, 3);
            return boks;
        }

    }

