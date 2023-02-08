package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacMan extends Entitet{
    protected double radius;
    protected Color farge;
    protected Circle pman;

    public PacMan(){
        radius = 50.0;
        farge = Color.YELLOW;
        pman = new Circle(100, 200,radius,farge);
        super.fart = 10.0;

    }
}
