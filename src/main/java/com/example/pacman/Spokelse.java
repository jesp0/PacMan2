package com.example.pacman;

/**Spokelse er superklassen for spøkelsene. De arver fra Enitetet men har en del attributter i tillegg
* Det er laget av en Arc(halvmåne) og et Polygon som er "skjørtet". Spøkelsene deler en go del metoder og er hovedsaklig
* skilt av logikken.*/

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    protected Animation spokelseSkremt;
    protected static boolean erSkremt;
    protected String retning;
    protected boolean bonusPoengSjekk;
    protected static int antSpist = 0;
    public Spokelse(double x, double y) {
        super(x, y);
        posisjon = new Arc(x,y,10.0,10.0,0,180);
        poly = new Polygon(x-10,y,x-10,y+10,x-5,y+5,x,y+10,x+5,y+5,x+10,y+10,x+10,y,x-10,y);
        erSkremt = false;
    }
    /**Bevegelse metoden er hvordan spøkelsene flytter på seg. Alle spøkelsene har en retning
    * og metoden blir kjørt om og om igjen i en keyframe animasjon. Refresh rate på animasjonen styrer farten
    * til spøkelsene. Verdt å merke seg at spøkelse sin BoundingBox blir "new"`et hele tiden for så å sjekke etter
    * kollisjon med Vegg/Kryss eller PacMan*/
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
    /** KollisjonSjekk metoden er omfattende og sjekker etter kollisjon med PacMan, kryss og vegger.
    * Dersom spøkelse ikke er skremt vil det spise PacMan ved en kollisjon og omvendt dersom det er skremt.
    * For å bedre flyten til spøkelsene har vi lagt til en 30% sjanse for at de ikke tar noe valg i hvert kryss.*/
    public void kollisjonSjekk(){
        if(boks.intersects(Spill.pacMan.boks)){
            if(erSkremt == false){
                Animasjoner.animation.pause();
                Animasjoner.pacAnimation.pause();
                Animasjoner.pauseSpokelser();
                Spill.pacMan.lever = false;
                System.out.println("Got you PacMan!!");
                Spill.antLiv--;
                Spill.gameoverSjekk();
            }
            if(erSkremt == true){
                erSpist(this);

                if(!bonusPoengSjekk) {
                    antSpist += 1;
                    oppdaterScore(this.posisjon, antSpist);
                    bonusPoengSjekk = true;
                }
            }
        }
        int random = trekkTall(1,10);
        utenforSjekk();
        for (int i=0; i<Spill.kryssListe.size();i++){
            if(boks.contains(Spill.kryssListe.get(i).boks) && random > 3){
                if(erSkremt == false)
                    retning = logikk(retning);
                else if(erSkremt == true) {
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
    /**Dersom et spøkelse blir spist stanser blir alle animasjoner stanset og det blir usynlig i 5 sekunder før det blir
    * nullstillt.Boundingboksen blir flyttet utenfor kartet midlertidig.*/
    private void erSpist(Spokelse spokelse) {
        spokelse.spokelseSkremt.stop();
        spokelse.posisjon.setFill(Color.TRANSPARENT);
        spokelse.poly.setFill(Color.TRANSPARENT);
        spokelse.boks = new BoundingBox(-20,-20,1,1);
        Animation spokelseSpist = new Timeline(
                new KeyFrame(Duration.millis(5000), e -> {
                }));
        spokelseSpist.setCycleCount(1);
        spokelseSpist.setOnFinished(e -> spokelse.nullStillHelt());
        spokelseSpist.play();
    }
    /** Hvis et spøkelse treffer en vegg blir det skøvet en pixel ut av vegger før den utfører et logisk valg
     * om neste retning*/
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
        if (erSkremt == false)
            retning = logikk(retning);
        else if (erSkremt == true) {
            retning = skremtLogikk(retning);
        }
    }
    /** Trekktall er hentet fra PRO1000 kurset og leverer en tilfeldig integer.*/
    public static int trekkTall(int min, int max) {
        return min + (int)( Math.random()*(max-min+1) );
    }
    /** Logikk er abstract her og implementeres i individuelle spøkelser. Alle har samme logikk når de er skremt.*/
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
    }
    /** Noen abstrakte klasser til. Disse er ganske like i spøkelsene men har f.eks med posisjoner og farger å gjøre.*/
    public abstract void skremtSpokelse();
    public abstract void nullStill();
    public abstract void nullStillHelt();
    public abstract void utenforSjekk();


}

