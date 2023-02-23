package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class Vegg{
    protected boolean venstre, hoyre, over, under;
    protected double x, y;
    protected Polyline v;
    public BoundingBox boks, boks2;

    public Vegg(boolean venstre, boolean hoyre, boolean over, boolean under, double x, double y){
        this.venstre = venstre;
        this.hoyre = hoyre;
        this.over = over;
        this.under = under;
        this.x = x;
        this.y = y;
    }

    public Rectangle tegnVegg(Vegg vegg){
        if(vegg.venstre == true && vegg.hoyre == true && vegg.over == false && vegg.under == false){
            v = new Polyline(0+x,10+y,20+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,20,10);
            Rectangle bounding = new Rectangle(x,y,20,10);
            bounding.setFill(Color.RED);

            return bounding;
        }

        else if(vegg.venstre == false && vegg.hoyre == false && vegg.over == true && vegg.under == true){
            v = new Polyline(10+x,0+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,10,20);
            Rectangle bounding = new Rectangle(x,y,10,20);
            bounding.setFill(Color.RED);

            return bounding;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == true && vegg.under == false){
            v = new Polyline(0+x,10+y,10+x,10+y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,20,20);
            //boks2 = new BoundingBox(x,y,10,20);
            Rectangle bounding = new Rectangle(x,y,10,10);
            bounding.setFill(Color.RED);

            return bounding;
        }
        else if(vegg.venstre == false && vegg.hoyre == true && vegg.over == true && vegg.under == false){
            v = new Polyline(20+x,10+y,10+x,10+y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,20,10);
            //return v;
            Rectangle bounding = new Rectangle(x,y,20,10);
            bounding.setFill(Color.RED);

            return bounding;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == false && vegg.under == true){
            v = new Polyline(0+x,10+y,10+x,10+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);

            boks = new BoundingBox(x,y,10,20);
            Rectangle bounding = new Rectangle(x,y,10,20);
            bounding.setFill(Color.RED);
            return bounding;
            //return v;
        }
        else if(vegg.venstre == false && vegg.hoyre == true && vegg.over == false && vegg.under == true){
            v = new Polyline(20+x,10+y,10+x,10+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);

            boks = new BoundingBox(x,y,10,20);
            Rectangle bounding = new Rectangle(x,y,10,20);
            bounding.setFill(Color.RED);
            return bounding;

            //return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == true && vegg.over == false && vegg.under == true) {
            v = new Polyline(20 + x, 10 + y, 0 + x, 10 + y, 10 + x, 10 + y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,20,10);
            Rectangle bounding = new Rectangle(x,y,20,10);
            bounding.setFill(Color.RED);
            return bounding;

            //return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == true && vegg.over == true && vegg.under == false) {
            v = new Polyline(20 + x, 10 + y, 0 + x, 10 + y, 10 + x, 10 + y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,20,10);
            Rectangle bounding = new Rectangle(x,y,20,10);
            bounding.setFill(Color.RED);
            return bounding;

            //return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == true && vegg.under == true) {
            v = new Polyline(10 + x, 0 + y, 10 + x, 20 + y, 10 + x, 10 + y,0+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);

            boks = new BoundingBox(x,y,10,20);
            Rectangle bounding = new Rectangle(x,y,10,20);
            bounding.setFill(Color.RED);
            return bounding;

            //return v;
        }
        else if(vegg.venstre == false && vegg.hoyre == true && vegg.over == true && vegg.under == true) {
            v = new Polyline(10 + x, 0 + y, 10 + x, 20 + y, 10 + x, 10 + y,20+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,10,20);
            Rectangle bounding = new Rectangle(x,y,10,20);
            bounding.setFill(Color.RED);
            return bounding;
        }
        else if(vegg.venstre == true && vegg.hoyre == true && vegg.over == true && vegg.under == true) {
            v = new Polyline(10 + x, 0 + y, 10 + x, 20 + y, 10 + x, 10 + y,20+x,10+y, 0+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            boks = new BoundingBox(x,y,10,10);
            Rectangle bounding = new Rectangle(x,y,10,10);
            bounding.setFill(Color.BLUE);
            return bounding;
        }/*
        v = new Polyline(0,0,0,0);*/
        Rectangle bounding = new Rectangle(0,0,0,0);
        return bounding;
    }
    public Polyline tegnVegg2(Vegg vegg){
        if(vegg.venstre == true && vegg.hoyre == true && vegg.over == false && vegg.under == false){
            v = new Polyline(0+x,10+y,20+x,10+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }

        else if(vegg.venstre == false && vegg.hoyre == false && vegg.over == true && vegg.under == true){
            v = new Polyline(10+x,0+y,10+x,20+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
            return v;
        }
        else if(vegg.venstre == true && vegg.hoyre == false && vegg.over == true && vegg.under == false){
            v = new Polyline(0+x,10+y,10+x,10+y,10+x,0+y);
            v.setStroke(Color.BLUE); v.setStrokeWidth(3);
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
