package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class PacMan extends Entitet{


    protected Arc posisjon;
    protected String ret;
    protected boolean munnSjekk = true;
    protected static boolean lever = true;
    public static ArrayList<Polygon> hjerteliste;

    public PacMan(double x, double y){
        super(x,y);
        posisjon = new Arc(x,y,8.0,8.0,45,270);
        posisjon.setFill(Color.YELLOW);
        posisjon.setType(ArcType.ROUND);
        ret = "";
        boks = lagBoks(x,y);
    }
    public void bevegelse(String retning){
        ret = retning;
        // Henter oppdatert posisjon

        flytt(retning);
        //Oppdaterer BoundingBoksen til PacMan.
        boks = new BoundingBox(posisjon.getCenterX()-7, posisjon.getCenterY()-7,14,14);
        kollisjonSjekk(retning);
        LitenPrikk.spisPrikk();
        StorPrikk.spisStorPrikk();
        if(Spill.retningSjekk != retning && lever == true) {
            Spill.animation.play();
            Spill.pacAnimation.play();
            Spill.blinkyAnimation.play();
            Spill.pinkyAnimation.play();
            Spill.inkyAnimation.play();
            Spill.clydeAnimation.play();
        }
    }
    public void flytt(String retning){
        if(retning.equals("Nord")){
            posisjon.setCenterY(posisjon.getCenterY() - 1);
        }else if(retning.equals("Sør")){
            posisjon.setCenterY(posisjon.getCenterY()+1);
        }else if(retning.equals("Vest")){
            posisjon.setCenterX(posisjon.getCenterX()-1);
        }else if(retning.equals("Øst")){
            posisjon.setCenterX(posisjon.getCenterX()+1);
        }
    }
    public static void kollisjonSjekk(String retning){
        if (Spill.utenforHøyre.contains(Spill.pacMan.boks) ){
            Spill.pacMan.posisjon.setCenterX(-6);
        }
        if (Spill.utenforVenstre.contains(Spill.pacMan.boks) ){
            Spill.pacMan.posisjon.setCenterX(586);
            //Spill.pacMan.posisjon.setCenterX(-6);
        }
        for(int i=0; i<Spill.veggListe.size();i++){
            if(Spill.pacMan.boks.intersects(Spill.veggListe.get(i).boks)) {
                //System.out.println("" + i + Spill.veggListe.get(i).boks.toString());
                //System.out.println("X: "+ Spill.pacMan.posisjon.getCenterX() + " Y: " + Spill.pacMan.posisjon.getCenterY());
                Spill.animation.pause();
                Spill.pacAnimation.pause();
                Spill.retningSjekk = retning;
                switch (retning) {
                    case "Nord":
                        Spill.pacMan.posisjon.setCenterY(Spill.pacMan.posisjon.getCenterY() + 1);
                        break;
                    case "Sør":
                        Spill.pacMan.posisjon.setCenterY(Spill.pacMan.posisjon.getCenterY() - 1);
                        break;
                    case "Vest":
                        Spill.pacMan.posisjon.setCenterX(Spill.pacMan.posisjon.getCenterX() + 1);
                        break;
                    case "Øst":
                        Spill.pacMan.posisjon.setCenterX(Spill.pacMan.posisjon.getCenterX() - 1);
                        break;
                }
            }
        }
    }


    public void pacAnimasjon(Arc arc, String retning){
        if(retning == "Øst"){
            if (munnSjekk == true){
                if(arc.getStartAngle() < 75){
                    arc.setStartAngle(arc.getStartAngle()+1);
                    arc.setLength(arc.getLength()-2);
                    if(arc.getStartAngle() == 75)
                        munnSjekk = false;
                }
            }
            if (munnSjekk == false){
                if(arc.getStartAngle() > 0){
                    arc.setStartAngle(arc.getStartAngle()-1);
                    arc.setLength(arc.getLength()+2);
                    if(arc.getStartAngle() == 0)
                        munnSjekk = true;
                }
            }
        }
        if(retning == "Vest"){
            if (munnSjekk == true){
                if(arc.getStartAngle() < 255){
                    arc.setStartAngle(arc.getStartAngle()+1);
                    arc.setLength(arc.getLength()-2);
                    if(arc.getStartAngle() == 255)
                        munnSjekk = false;
                }
            }
            if (munnSjekk == false){
                if(arc.getStartAngle() > 180){
                    arc.setStartAngle(arc.getStartAngle()-1);
                    arc.setLength(arc.getLength()+2);
                    if(arc.getStartAngle() == 180)
                        munnSjekk = true;
                }
            }
        }
        if(retning == "Sør"){
            if (munnSjekk == true){
                if(arc.getStartAngle() < 345){
                    arc.setStartAngle(arc.getStartAngle()+1);
                    arc.setLength(arc.getLength()-2);
                    if(arc.getStartAngle() == 345)
                        munnSjekk = false;
                }
            }
            if (munnSjekk == false){
                if(arc.getStartAngle() > 270){
                    arc.setStartAngle(arc.getStartAngle()-1);
                    arc.setLength(arc.getLength()+2);
                    if(arc.getStartAngle() == 270)
                        munnSjekk = true;
                }
            }
        }
        if(retning == "Nord"){
            if (munnSjekk == true){
                if(arc.getStartAngle() < 165){
                    arc.setStartAngle(arc.getStartAngle()+1);
                    arc.setLength(arc.getLength()-2);
                    if(arc.getStartAngle() == 165)
                        munnSjekk = false;
                }
            }
            if (munnSjekk == false){
                if(arc.getStartAngle() > 90){
                    arc.setStartAngle(arc.getStartAngle()-1);
                    arc.setLength(arc.getLength()+2);
                    if(arc.getStartAngle() == 90)
                        munnSjekk = true;
                }
            }
        }
    }
    public void dødsAnimasjon(Arc arc, String retning){
        //arc.getStartAngle();
        System.out.println("the death of the pacman starts now");
        arc.setFill(Color.GREEN);

        //if(arc.getStartAngle() < 75){
        arc.setStartAngle(arc.getStartAngle());
        arc.setLength(arc.getLength()+100);
        //arc.rotateProperty();


        //arc.setStartAngle(arc.getStartAngle()-30);
        //arc.setLength(arc.getLength() + 90);

        //Spill.deathAnimation.play();
        System.out.println("pacman døøøøøøøøør");

    }

    public static void tegnHjerter(){
        // Tegner 3 hjerter ved siden av hverandre
        hjerteliste = new ArrayList<>();
        double forskyv = 0.0;
        for(int i=0; i<3; i++){
            Polygon hjerte = new Polygon();
            hjerte.getPoints().addAll(
                    forskyv + 20.0, 610.0,
                    forskyv + 22.5, 605.0,
                    forskyv + 25.0, 605.0,
                    forskyv + 27.5, 610.0,
                    forskyv + 30.0, 605.0,
                    forskyv + 32.5, 605.0,
                    forskyv + 35.0, 610.0,
                    forskyv + 27.5, 620.0);

            hjerte.setStroke(Color.DARKRED);
            hjerte.setFill(Color.RED);
            hjerteliste.add(hjerte);
            Spill.spillbrett.getChildren().add(hjerteliste.get(i));
            forskyv += 20;
        }
    }
}
