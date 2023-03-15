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
    /**
     Pacman opprettes og for form, farge og en BoundingBox (boks), som skal oppdage
     når Pacman krysser spøkelser eller kolliderer med vegger.
     I tillegg setter vi retningen som en tom String, retning endres basert på tastetrykk.
     */
    public PacMan(double x, double y){

        super(x,y);
        posisjon = new Arc(x,y,10.0,10.0,45,270);
        posisjon.setFill(Color.YELLOW);
        posisjon.setType(ArcType.ROUND);
        ret = "";
        boks = lagBoks(x,y);
    }

    /**
     * Sørger for at Pacman beveger seg som han skal
     * @param retning
     */
    public void bevegelse(String retning){
        ret = retning;
        // Flytter Pacman i en retning som bestemmes av tastetrykk
        flytt(retning);
        /*
            Oppdaterer BoundingBoksen til PacMan. Boksen er litt mindre en Pacman slik
            at han er litt lettere å styre rundt kantene på vegger.
            BoundingBox kan ikke flyttes på, så den blir newet hele tiden så lenge Pacman beveger seg.
         */
        boks = new BoundingBox(posisjon.getCenterX()-7, posisjon.getCenterY()-7,14,14);
        kollisjonSjekk(retning);
        LitenPrikk.spisPrikk();
        StorPrikk.spisStorPrikk();
        if(Spill.retningSjekk != retning && lever == true) {
            // Sørger for at Pacmans animasjoner kjører, så lenge han lever og ikke har en vegg foran seg
            Animasjoner.animation.play();
            Animasjoner.pacAnimation.play();
        }
    }

    /**
     * Pacman flyttes kontinuerlig i valgt retning
     * @param retning
     */
    public void flytt(String retning){
        if(retning.equals("Nord")){
            posisjon.setCenterY(posisjon.getCenterY()-1);
        }else if(retning.equals("Sør")){
            posisjon.setCenterY(posisjon.getCenterY()+1);
        }else if(retning.equals("Vest")){
            posisjon.setCenterX(posisjon.getCenterX()-1);
        }else if(retning.equals("Øst")){
            posisjon.setCenterX(posisjon.getCenterX()+1);
        }
    }

    /**
     * Sjekker om Pacman kolliderer med vegger rundt om på kartet
     * @param retning
     */
    public static void kollisjonSjekk(String retning){
        // Teleporterer Pacman dersom han kjører i "tunnelen" ut av banen.
        if (Spill.utenforHøyre.contains(Spill.pacMan.boks) ){
            Spill.pacMan.posisjon.setCenterX(-6);
        }
        if (Spill.utenforVenstre.contains(Spill.pacMan.boks) ){
            Spill.pacMan.posisjon.setCenterX(586);
        }

        for(int i=0; i<Spill.veggListe.size();i++){
            if(Spill.pacMan.boks.intersects(Spill.veggListe.get(i).boks)) {
                /*
                Stanser pacmans bevegelsesanimasjon (animation) og
                munn-animasjonen (pacAnimation), når han står mot en vegg
                 */
                Animasjoner.animation.pause();
                Animasjoner.pacAnimation.pause();
                Spill.retningSjekk = retning;
                /*
                Sørger for at Pacman ikke setter seg fast i vegger ved å flytte han
                1 piksel tilbake når han treffer en vegg
                 */
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

    /**
     * Animerer munnen til Pacman, slik at han kan spise prikker mens han
     * beveger seg rundt i forskjellige retninger.
     * @param arc
     * @param retning
     */
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

    /**
     * Animerer munnen til Pacman når han dør, slik at han spiser seg selv og forsvinner
     * @param arc
     */
    public void dødsAnimasjon(Arc arc){
        if(arc.getLength() > 0){
            arc.setStartAngle(arc.getStartAngle()+2);
            arc.setLength(arc.getLength()-4);
        }
    }

    /**
     * Tegner Pacmans hjerter ved siden av hverandre,
     * som en visuell representasjon av antall liv han har.
     */
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
