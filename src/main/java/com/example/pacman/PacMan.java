package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacMan extends Entitet{


    protected Circle posisjon;
    protected String ret;

    public PacMan(double x, double y){
        super(x,y);
        posisjon = new Circle(x,y,8.0,Color.YELLOW);
        ret = "";



    }
}
