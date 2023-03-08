package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

public class PacMan extends Entitet{


    protected Arc posisjon;
    protected String ret;
    protected boolean munnSjekk = true;

    public PacMan(double x, double y){
        super(x,y);
        posisjon = new Arc(x,y,8.0,8.0,45,270);
        posisjon.setFill(Color.YELLOW);
        posisjon.setType(ArcType.ROUND);
        ret = "";
        boks = lagBoks(x,y);
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
}
