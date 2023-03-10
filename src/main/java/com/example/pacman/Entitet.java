package com.example.pacman;

import javafx.event.ActionEvent;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

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





