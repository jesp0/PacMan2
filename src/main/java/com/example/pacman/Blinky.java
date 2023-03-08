package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Blinky extends Spokelse{
    public Blinky(double x, double y){
        super(x,y);
        boks = lagBoks(x,y);
        posisjon = new Circle(x, y, 8, Color.RED);
    }
}
