package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Blinky extends Spokelse{
    public Blinky(double x, double y){
        super(x,y);
        boks = lagBoks(x,y);
        posisjon = new Circle(x, y, 8, Color.RED);
        retning = "Øst";
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
            boks = new BoundingBox(posisjon.getCenterX()-6,posisjon.getCenterY()-6,12,12);
            kollisjonSjekk(retning);
        }
    public void kollisjonSjekk(String ret){
        for(int i=0; i<Spill.veggListe.size();i++){
            if(boks.intersects(Spill.veggListe.get(i).boks)) {

                //System.out.println("" + i + Spill.veggListe.get(i).boks.toString());
                //System.out.println("X: "+ posisjon.getCenterX() + " Y: " + posisjon.getCenterY());
                retningSjekk = ret;
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
                retning = nyRetning(retning);
            }
        }
    }
    public String nyRetning(String gammelRetning){
        gammelRetning = retning;
        String nyRetning = "";
        switch (trekkTall(1,4)){
            case 1: nyRetning = "Nord"; break;
            case 2: nyRetning = "Sør"; break;
            case 3: nyRetning = "Øst"; break;
            case 4: nyRetning = "Vest"; break;
        }
        if(nyRetning != gammelRetning){
            return nyRetning;
        }
        return nyRetning;
    }
    public static int trekkTall(int min, int max) {
        return min + (int)( Math.random()*(max-min+1) );
    }

}
