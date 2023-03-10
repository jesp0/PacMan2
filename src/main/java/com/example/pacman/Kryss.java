package com.example.pacman;

import javafx.geometry.BoundingBox;

public class Kryss extends Entitet{
    public Kryss (double x, double y){
        super(x,y);
        boks = lagBoks(x, y);
    }
    @Override
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x+1, y+1, 18, 18);
        return boks;
    }
}
