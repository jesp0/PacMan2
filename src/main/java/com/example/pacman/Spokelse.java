package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Spokelse extends Entitet{
    protected Circle posisjon;
    protected String retning,retningSjekk;
    public Spokelse(double x, double y) {
        super(x, y);
        posisjon = new Circle(x, y, 10);
    }
    public void bevegelse(){
        if(retning.equals("Nord")){
            posisjon.setCenterY(posisjon.getCenterY() -1);
        }else if(retning.equals("Sør")){
            posisjon.setCenterY(posisjon.getCenterY() + 1);
        }else if(retning.equals("Vest")){
            posisjon.setCenterX(posisjon.getCenterX()-1);
        }else if(retning.equals("Øst")){
            posisjon.setCenterX(posisjon.getCenterX()+1);
        }
        boks = new BoundingBox(posisjon.getCenterX()-9,posisjon.getCenterY()-9,18,18);
        kollisjonSjekk(this);
    }
    public void kollisjonSjekk(Spokelse spokelse){
        int random = trekkTall(1,10);
        utenforSjekk(spokelse);
        for (int i=0; i<Spill.kryssListe.size();i++){
            if(boks.contains(Spill.kryssListe.get(i).boks) && random > 3){
                retning = spokelse.logikk(retning);
                //System.out.println("Kryss!");
            }
        }
        for(int i=0; i<Spill.veggListe.size();i++){
            if(boks.intersects(Spill.veggListe.get(i).boks)){
                veggKræsj(retning);
                //System.out.println("Vegg!");
            }
        }
    }
    private void veggKræsj(String ret) {
        switch (ret) {
            case "Nord":
                posisjon.setCenterY(posisjon.getCenterY() + 1);
                break;
            case "Sør":
                posisjon.setCenterY(posisjon.getCenterY() - 1);
                break;
            case "Vest":
                posisjon.setCenterX(posisjon.getCenterX() + 1);
                break;
            case "Øst":
                posisjon.setCenterX(posisjon.getCenterX() - 1);
                break;
        }
        retning = logikk(retning);
    }
    public static int trekkTall(int min, int max) {
        return min + (int)( Math.random()*(max-min+1) );
    }
    public abstract String logikk(String s);
    public void utenforSjekk(Spokelse spokelse){
        if (Spill.utenforHøyre.contains(spokelse.boks) ){
            spokelse.posisjon.setCenterX(-20);
        }
        if (Spill.utenforVenstre.contains(spokelse.boks) ){
            spokelse.posisjon.setCenterX(580);
        }
    }
}

