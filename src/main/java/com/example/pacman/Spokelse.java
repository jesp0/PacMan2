package com.example.pacman;

import javafx.scene.shape.Circle;

public abstract class Spokelse extends Entitet{
    protected Circle posisjon;
    protected String retning,retningSjekk;
    public Spokelse(double x, double y) {
        super(x, y);
    }
    public abstract void bevegelse();
    public void utenforSjekk(Spokelse spokelse){
        if (Spill.utenforHøyre.contains(spokelse.boks) ){
            spokelse.posisjon.setCenterX(-20);
        }
        if (Spill.utenforVenstre.contains(spokelse.boks) ){
            spokelse.posisjon.setCenterX(580);
        }
    }
}

