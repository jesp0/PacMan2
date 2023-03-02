package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacMan extends Entitet{


    protected Circle posisjon;
    protected String ret;

    public PacMan(double x, double y){
        super(x,y);
        posisjon = new Circle(x,y,8.0,Color.YELLOW);
        ret = "";
        boks = lagBoks(x,y);
    }
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x, y, 7, 7);
        return boks;
    }
}
