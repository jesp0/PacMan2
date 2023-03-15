package com.example.pacman;

//Klassen er superklassen for alt som blir lagt ut på brettet. Felles er at de har en x/y posisjon og en BoundingBox.

import javafx.geometry.BoundingBox;

public abstract class Entitet{
   protected double x, y;
    protected BoundingBox boks;
    public Entitet(double x, double y){
        this.x = x;
        this.y = y;
    }
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x, y, 20, 20);
        return boks;
    }

    }





