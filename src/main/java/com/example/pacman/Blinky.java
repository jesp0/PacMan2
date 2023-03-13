package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;

public class Blinky extends Spokelse{
    public Blinky(double x, double y){
        super(x,y);
        boks = lagBoks(x,y);
        posisjon.setFill(Color.RED);
        retning = "Vest";
        poly.setFill(Color.RED);
    }
    public String logikk(String gammelRetning){
        String nyRetning = "";
        int random = trekkTall(1,100);
        double dX = Spill.pacMan.posisjon.getCenterX() - posisjon.getCenterX();
        double dY = Spill.pacMan.posisjon.getCenterY() - posisjon.getCenterY();
        int pri1 = 50, pri2 = 25, pri3 = 15,pri4 = 10;
        boolean nord = false,sør = false, øst = false,vest = false;
        double diff = Math.abs(dX) - Math.abs(dY);

        if(dX > 0 && dY > 0 && diff > 0){
            if(random <= 50)
                return "Sør";
            if(random > 50 && random <= 75)
                return "Øst";
            if(random > 75 && random <= 90)
                return "Nord";
            if(random > 90)
                return "Vest";
        }
        if(dX > 0 && dY > 0 && diff < 0){
            if(random <= 50)
                return "Øst";
            if(random > 50 && random <= 75)
                return "Sør";
            if(random > 75 && random <= 90)
                return "Vest";
            if(random > 90)
                return "Nord";
        }
        if(dX < 0 && dY > 0 && diff > 0){
            if(random <= 50)
                return "Sør";
            if(random > 50 && random <= 75)
                return "Vest";
            if(random > 75 && random <= 90)
                return "Nord";
            if(random > 90)
                return "Øst";
        }
        if(dX < 0 && dY > 0 && diff < 0){
            if(random <= 50)
                return "Vest";
            if(random > 50 && random <= 75)
                return "Sør";
            if(random > 75 && random <= 90)
                return "Øst";
            if(random > 90)
                return "Nord";
        }
        if(dX < 0 && dY < 0 && diff > 0){
            if(random <= 50)
                return "Nord";
            if(random > 50 && random <= 75)
                return "Vest";
            if(random > 75 && random <= 90)
                return "Øst";
            if(random > 90)
                return "Sør";
        }
        if(dX < 0 && dY < 0 && diff < 0){
            if(random <= 50)
                return "Vest";
            if(random > 50 && random <= 75)
                return "Nord";
            if(random > 75 && random <= 90)
                return "Sør";
            if(random > 90)
                return "Øst";
        }

        if(dX > 0 && dY < 0 && diff < 0){
            if(random <= 50)
                return "Øst";
            if(random > 50 && random <= 75)
                return "Nord";
            if(random > 75 && random <= 90)
                return "Sør";
            if(random > 90)
                return "Vest";
        }
        if(dX < 0 && dY < 0 && diff > 0){
            System.out.println("IntelliJ tar feil!");
            if(random <= 50)
                return "Nord";
            if(random > 50 && random <= 75)
                return "Øst";
            if(random > 75 && random <= 90)
                return "Vest";
            if(random > 90)
                return "Sør";
        }
        if(dX > 0 && dY < 0 && diff > 0){
            if(random <= 50)
                return "Øst";
            if(random > 50 && random <= 75)
                return "Nord";
            if(random > 75 && random <= 90)
                return "Vest";
            if(random > 90)
                return "Sør";
        }


        return nyRetning;

        /*if(!sjekkVeggkræsj("Vest"))
            vest = true;
        if(!sjekkVeggkræsj("Sør"))
            sør = true;
        if(!sjekkVeggkræsj("Øst")){
            øst = true;
        }
        if(!sjekkVeggkræsj("Nord")){
            nord = true;
        }

        if(sør == true && nord == true)
            if(dY > 0)
                return "Sør";
            else return "Nord";
        if(øst == true && vest == true)
            if(dX > 0)
                return "Øst";
            else return "Vest";
        if(sør == true)
            return "Sør";
        if (nord == true)
            return "Nord";
        if (vest == true)
            return "Vest";
        if (øst == true)
            return "Øst";
        return nyRetning;*/
        }

    public String skremtLogikk(String gammelRetning){
        String nyRetning = "";
        int random = trekkTall(1,3);
        switch (gammelRetning){
            case "Nord": switch (random){
                case 1: nyRetning = "Øst"; ;break;
                case 2: nyRetning = "Vest"; break;
                case 3: nyRetning = "Nord"; break;
            } break;
            case "Sør": switch (random){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Vest"; break;
            } break;
            case "Øst": switch (random){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Øst"; break;
                case 3: nyRetning = "Nord"; break;
            } break;
            case "Vest": switch (random){
                case 1: nyRetning = "Sør"; break;
                case 2: nyRetning = "Vest"; break;
                case 3: nyRetning = "Nord"; break;
            }

                return nyRetning;
        }

        return nyRetning;
    }

    public void utenforSjekk(){
        if (Spill.utenforHøyre.contains(boks) ){
            posisjon.setCenterX(-10);
            poly.setLayoutX(-290);
        }
        if (Spill.utenforVenstre.contains(boks) ){
            posisjon.setCenterX(580);
            poly.setLayoutX(300);
        }
    }


}
