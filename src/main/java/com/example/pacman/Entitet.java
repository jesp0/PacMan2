package com.example.pacman;

import javafx.scene.shape.Circle;

public abstract class Entitet extends Circle {
    protected double fart;
    protected enum retning{
        Nord,Syd,Øst,Vest;
    }


}
