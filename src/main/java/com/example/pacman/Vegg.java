package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Vegg extends Entitet{


    public Vegg (double x, double y){
        super(x,y);
        boks = lagBoks(x, y);
    }

    public Rectangle tegnVegg(Vegg vegg) {
        Rectangle bounding = new Rectangle(x, y, 20, 20);
        bounding.setStroke(Color.RED);
        return bounding;
    }
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x, y, 20, 20);
        return boks;
    }
}
