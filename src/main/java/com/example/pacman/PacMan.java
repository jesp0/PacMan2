package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacMan extends Entitet{


    protected Circle posisjon;
    protected String ret;

    public PacMan(){
        posisjon = new Circle(0,0,18.0,Color.YELLOW);
        ret = "";
        super.fart = 10.0;


    }
}
