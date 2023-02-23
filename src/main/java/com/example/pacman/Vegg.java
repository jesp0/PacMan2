package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class Vegg{
    protected boolean venstre, hoyre, over, under;
    protected double x, y;
    protected Polyline v;
    protected BoundingBox boks, boks2;

    public Vegg(boolean venstre, boolean hoyre, boolean over, boolean under, double x, double y){
        this.venstre = venstre;
        this.hoyre = hoyre;
        this.over = over;
        this.under = under;
        this.x = x;
        this.y = y;
    }

    public Polyline tegnVegg(Vegg vegg){
        if(vegg.venstre == true && vegg.hoyre == true && vegg.over == false && vegg.under == false){
            v = new Polyline(0+x,10+y,20+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,20+x,10+y);
            return v;
        }
        else if(vegg.venstre == false && vegg.hoyre == false && vegg.over == true && vegg.under == true){
            v = new Polyline(10+x,0+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,10+x,20+y);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == true && vegg.under == false){
            v = new Polyline(0+x,10+y,10+x,10+y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,10+x,20+y);
            boks2 = new BoundingBox(x,y,10+x,20+y);
            return v;
        }
        else if(vegg.venstre == false && vegg.hoyre == true && vegg.over == true && vegg.under == false){
            v = new Polyline(20+x,10+y,10+x,10+y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == false && vegg.under == true){
            v = new Polyline(0+x,10+y,10+x,10+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == false && vegg.hoyre == true && vegg.over == false && vegg.under == true){
            v = new Polyline(20+x,10+y,10+x,10+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == true && vegg.over == false && vegg.under == true) {
            v = new Polyline(20 + x, 10 + y, 0 + x, 10 + y, 10 + x, 10 + y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == true && vegg.over == true && vegg.under == false) {
            v = new Polyline(20 + x, 10 + y, 0 + x, 10 + y, 10 + x, 10 + y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == true && vegg.under == true) {
            v = new Polyline(10 + x, 0 + y, 10 + x, 20 + y, 10 + x, 10 + y,0+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == false && vegg.hoyre == true && vegg.over == true && vegg.under == true) {
            v = new Polyline(10 + x, 0 + y, 10 + x, 20 + y, 10 + x, 10 + y,20+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == true && vegg.over == true && vegg.under == true) {
            v = new Polyline(10 + x, 0 + y, 10 + x, 20 + y, 10 + x, 10 + y,20+x,10+y, 0+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        v = new Polyline(0,0,0,0);
        return v;
    }
}
