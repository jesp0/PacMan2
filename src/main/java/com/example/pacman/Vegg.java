package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Vegg extends Entitet{


    public Vegg (double x, double y){
        super(x,y);
        boks = lagBoks(x, y);
    }

    public Rectangle tegnVegg(Vegg vegg) {
        Rectangle bounding = new Rectangle(x, y, 20, 20);
        bounding.setStroke(Color.BLUE);
        return bounding;
    }
    public ArrayList<Rectangle> fancyVegg(Vegg vegg){
        ArrayList<Rectangle> v = new ArrayList<>();
        double dx = 1, dy = 1;
        for (int i = 0; i<4; i++){
            for(int k = 0; k<3; k++) {
                Rectangle r = new Rectangle(x + dx ,y + dy ,5,3);
                r.setFill(Color.BLUE);
                v.add(r);
                dx += 6;
            }
            dx = 1;
            dy += 4;
        }
        return v;
    }
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x, y, 20, 20);
        return boks;
    }
}
