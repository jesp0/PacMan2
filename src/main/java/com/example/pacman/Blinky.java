package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Blinky extends Spokelse{
    public Blinky(double x, double y){
        super(x,y);
        boks = lagBoks(x,y);
        posisjon = new Circle(x, y, 10, Color.RED);
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
            boks = new BoundingBox(posisjon.getCenterX()-9,posisjon.getCenterY()-9,18,18);
            kollisjonSjekk();
        }
    public void kollisjonSjekk(){
        int random = trekkTall(1,10);
        for (int i=0; i<Spill.kryssListe.size();i++){
            if(boks.contains(Spill.kryssListe.get(i).boks) && random > 3){
                retning = nyRetning(retning);
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


    public String nyRetning(String gammelRetning){
        String nyRetning = "";
        switch (gammelRetning){
            case "Nord": switch (trekkTall(1,3)){
                case 1: nyRetning = "Nord"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Vest"; break;
            }
            case "Sør": switch (trekkTall(1,3)){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Vest"; break;
            }
            case "Øst": switch (trekkTall(1,3)){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Nord"; break;
            }
            case "Vest": switch (trekkTall(1,3)){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Nord"; break;
                case 3: nyRetning = "Vest"; break;
            }
            return nyRetning;
        }

        return nyRetning;
    }
    public static int trekkTall(int min, int max) {
        return min + (int)( Math.random()*(max-min+1) );
    }

}
