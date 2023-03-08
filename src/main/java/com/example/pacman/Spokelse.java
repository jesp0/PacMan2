package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Spokelse extends Entitet{
    protected Circle posisjon;
    protected String retning,retningSjekk;
    public Spokelse(double x, double y) {
        super(x, y);
    }
    public abstract void bevegelse();
}
