package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polygon;


public abstract class Spokelse extends Entitet{
    protected Arc posisjon;
    protected Polygon poly;
    protected boolean erSkremt;
    protected String retning,retningSjekk;
    public Spokelse(double x, double y) {
        super(x, y);
        posisjon = new Arc(x,y,10.0,10.0,0,180);
        poly = new Polygon(x-10,y,x-10,y+10,x-5,y+5,x,y+10,x+5,y+5,x+10,y+10,x+10,y,x-10,y);
        erSkremt = false;
    }
    public void bevegelse(){
        if(retning.equals("Nord")){
            posisjon.setCenterY(posisjon.getCenterY() -1);
            poly.setLayoutY(poly.getLayoutY()-1);
        }else if(retning.equals("Sør")){
            posisjon.setCenterY(posisjon.getCenterY() + 1);
            poly.setLayoutY(poly.getLayoutY()+1);
        }else if(retning.equals("Vest")){
            posisjon.setCenterX(posisjon.getCenterX()-1);
            poly.setLayoutX(poly.getLayoutX()-1);
        }else if(retning.equals("Øst")){
            posisjon.setCenterX(posisjon.getCenterX()+1);
            poly.setLayoutX(poly.getLayoutX()+1);
        }
        boks = new BoundingBox(posisjon.getCenterX()-9,posisjon.getCenterY()-9,18,18);
        kollisjonSjekk();
    }
    public void kollisjonSjekk(){
        if(boks.intersects(Spill.pacMan.boks)){
            Animasjoner.animation.pause();
            Animasjoner.pacAnimation.pause();
            Animasjoner.blinkyAnimation.pause();
            Animasjoner.inkyAnimation.pause();
            Animasjoner.pinkyAnimation.pause();
            Animasjoner.clydeAnimation.pause();
            Spill.pacMan.lever = false;
            System.out.println("Got you PacMan!!");

            Spill.antLiv--;
            Spill.gameoverSjekk();

        }
        int random = trekkTall(1,10);
        utenforSjekk();
        for (int i=0; i<Spill.kryssListe.size();i++){
            if(boks.contains(Spill.kryssListe.get(i).boks) && random > 3){
                //if(erSkremt == false)
                    retning = logikk(retning);
                /*else if(erSkremt == true) {
                    retning = skremtLogikk(retning);
                }*/
            }
        }
        for(int i=0; i<Spill.veggListe.size();i++){
            if(boks.intersects(Spill.veggListe.get(i).boks)){
                //if(erSkremt == false)
                    veggKræsj(retning);
                /*else if(erSkremt == true) {
                    retning = skremtLogikk(retning);
                }*/
            }
        }
    }
    private void veggKræsj(String ret) {
        switch (ret) {
            case "Nord":
                posisjon.setCenterY(posisjon.getCenterY() + 1);
                poly.setLayoutY(poly.getLayoutY()+1);
                break;
            case "Sør":
                posisjon.setCenterY(posisjon.getCenterY() - 1);
                poly.setLayoutY(poly.getLayoutY()-1);
                break;
            case "Vest":
                posisjon.setCenterX(posisjon.getCenterX() + 1);
                poly.setLayoutX(poly.getLayoutX()+1);
                break;
            case "Øst":
                posisjon.setCenterX(posisjon.getCenterX() - 1);
                poly.setLayoutX(poly.getLayoutX()-1);
                break;
        }
        retning = logikk(retning);
    }
    public boolean sjekkVeggkræsj(String ret){
        double dX = 0, dY = 0;

        switch (ret) {
            case "Nord":
                dY = 1;
                break;
            case "Sør":
                dY = -1;
                break;
            case "Vest":
                dX = 1;
                break;
            case "Øst":
                dX = -1;
                break;
        }
        BoundingBox boks2 = new BoundingBox(posisjon.getCenterX()-9 + dX,posisjon.getCenterY()-9 + dY,18,18);

        for(int i=0; i<Spill.veggListe.size();i++){
            if(boks2.intersects(Spill.veggListe.get(i).boks)){
                return true;
            }
        }
        return false;
    }
    public static int trekkTall(int min, int max) {
        return min + (int)( Math.random()*(max-min+1) );
    }
    public abstract String logikk(String s);
    public abstract String skremtLogikk(String s);
    public abstract void utenforSjekk();


}

