package com.example.pacman;

import javafx.animation.FadeTransition;
import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public abstract class Spokelse extends Entitet{
    protected Arc posisjon;
    protected Polygon poly;
    protected boolean erSkremt;
    protected String retning,retningSjekk;
    protected boolean bonusPoengSjekk;
    protected static int antSpist = 0;
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
    public void skremtBevegelse(){
        Animasjoner.blinkyAnimation.pause();
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
            if(Spill.blinky.erSkremt == false){
                Animasjoner.animation.pause();
                Animasjoner.pacAnimation.pause();
                Animasjoner.pauseSpokelser();
                Spill.pacMan.lever = false;
                System.out.println("Got you PacMan!!");

                Spill.antLiv--;
                Spill.gameoverSjekk();
            }
            if(Spill.blinky.erSkremt == true){
                Spill.spillbrett.getChildren().remove(Spill.blinky.posisjon);
                Spill.spillbrett.getChildren().remove(Spill.blinky.poly);
                Spill.spillbrett.getChildren().remove(Spill.blinky.boks);
                Animasjoner.blinkyAnimation.stop();
                if(!bonusPoengSjekk) {
                    antSpist += 1;
                    oppdaterScore(Spill.blinky.posisjon, antSpist);
                    bonusPoengSjekk = true;
                }
            }
        }
        int random = trekkTall(1,10);
        utenforSjekk();
        for (int i=0; i<Spill.kryssListe.size();i++){
            if(boks.contains(Spill.kryssListe.get(i).boks) && random > 3){
                if(Spill.blinky.erSkremt == false)
                    retning = logikk(retning);
                else if(Spill.blinky.erSkremt == true) {
                    retning = skremtLogikk(retning);
                }
            }
        }
        for(int i=0; i<Spill.veggListe.size();i++){
            if(boks.intersects(Spill.veggListe.get(i).boks)){
                veggKræsj(retning);
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
        if (Spill.blinky.erSkremt == false)
            retning = logikk(retning);
        else if (Spill.blinky.erSkremt == true) {
            retning = skremtLogikk(retning);
        }
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

    public static void oppdaterScore(Arc spokelse, int combo){
        // Poengsum økes basert på hvor mange spøkelser Pacman spiser
        int bonusPoeng = 0;
        switch(combo){
            case 1 : bonusPoeng = 200; break;
            case 2 : bonusPoeng = 400; break;
            case 3 : bonusPoeng = 600; break;
            case 4 : bonusPoeng = 800; break;
        }
        Spill.poengsum += bonusPoeng;
        Spill.score.setText("" + Spill.poengsum);

        // Bonuspoeng dukker opp over spøkelset når det blir spist
        Text bonusTekst = new Text(spokelse.getCenterX(),spokelse.getCenterY()-20,bonusPoeng + "!");
        bonusTekst.setFont(new Font(20));
        bonusTekst.setFill(Color.WHITE);
        bonusTekst.setOpacity(0.0);
        Spill.spillbrett.getChildren().add(bonusTekst);
        FadeTransition ft = new FadeTransition(Duration.millis(2000), bonusTekst);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();


        //Spill.spillbrett.getChildren().remove(bonusTekst);

        /*
        Boolsk verdi sjekker om den != "", -> legger til tekst -> venter -> fjern
         */

    }

    public abstract void nullStill();
    public abstract void utenforSjekk();


}

