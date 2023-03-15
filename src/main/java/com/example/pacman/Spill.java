package com.example.pacman;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;


public class Spill extends Application {
    protected static final int BRETTHOYDE = 620;
    protected static final int BRETTLENGDE = 560;
    //Pixlene er kvadratiske og trenger kun 1 verdi.
    private static final int PIXEL = 20;
    static PacMan pacMan;
    static Spokelse blinky, inky, pinky, clyde;
    protected static Pane spillbrett;
    protected static String retningSjekk;
    public static Text score = new Text("0");
    public static Text gameoverTekst;
    public static int poengsum = 0;
    public static ArrayList<Vegg> veggListe = new ArrayList<>();
    public static ArrayList<Kryss> kryssListe = new ArrayList<>();
    public static ArrayList<LitenPrikk> litenPrikkListe = new ArrayList<>();
    public static ArrayList<StorPrikk> storPrikkListe = new ArrayList<>();
    public static ArrayList<String> highScores = new ArrayList<>();
    protected static BoundingBox utenforVenstre = new BoundingBox(-21,270,20,60);
    protected static BoundingBox utenforHøyre = new BoundingBox(581,270,20,60);
    public static int antLiv = 3;
    @Override
    public void start(Stage stage) throws IOException {

        spillbrett = new Pane();;
        nyttSpill();
        Animasjoner.startAnimation();
        Scene scene = new Scene(spillbrett, BRETTLENGDE, BRETTHOYDE);
        scene.setFill(Color.BLACK);
        spillbrett.setBackground(Background.fill(Color.BLACK));
        /*
         * Ved tastetrykk endres Pacmans retning, og spøkelser startes dersom de ikke er skremt (de blir skremt når Pacman spiser store prikker)
         */
        scene.setOnKeyPressed(e ->{
            if(!Spokelse.erSkremt){
                Animasjoner.startSpokelser();
            }
            pacMan.posisjon.setLength(270);
            switch ((e.getCode())){
                case UP :   pacMan.bevegelse("Nord");
                            pacMan.posisjon.setStartAngle(135);
                            break;

                case DOWN : pacMan.bevegelse("Sør");
                            pacMan.posisjon.setStartAngle(315);
                            break;
                case LEFT : pacMan.bevegelse("Vest");
                            pacMan.posisjon.setStartAngle(225);
                            break;

                case RIGHT : pacMan.bevegelse("Øst");
                             pacMan.posisjon.setStartAngle(45);
            }
        });
        stage.setResizable(false);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }
    /**
     Denne metoden får inn en liste med char som leses fra en fil (filinnlesing skjer i Kart-klassen).
     Basert på char i tabellen tegnes forskjellige elementer på spillbrettet
     */
    public static void kartTolking(ArrayList<String> kart){

        double x = 0, y = 0;
        for(int i = 0; i< kart.size(); i++){
            for(int k = 0; k < kart.get(i).length(); k++){
                switch (kart.get(i).charAt(k)){
                    case '#' :  Vegg vegg = new Vegg(x,y);
                                veggListe.add(vegg);
                                Rectangle v = vegg.tegnVegg();
                                spillbrett.getChildren().add(v);
                                ArrayList<Rectangle> fancy;
                                fancy = vegg.fancyVegg();
                                for (int j = 0; j< fancy.size(); j++)
                                    spillbrett.getChildren().add(fancy.get(j)); break;

                    case 'K' : Kryss kryss = new Kryss(x,y);
                               kryssListe.add(kryss);
                               /** Kryss brukes for at spøkelsene skal kunne skifte retning selv om de ikke møter på en vegg */
                    case 'D' : LitenPrikk litenPrikk = new LitenPrikk(x,y);
                                litenPrikkListe.add(litenPrikk);
                                spillbrett.getChildren().add(litenPrikk.posisjon); break;
                    case 'R' :  break;
                    case 'B' : StorPrikk storPrikk = new StorPrikk(x,y);
                                storPrikkListe.add(storPrikk);
                                spillbrett.getChildren().add(storPrikk.posisjon); break;
                    case 'G' : Kryss kryssUten = new Kryss(x,y);
                                kryssListe.add(kryssUten); break;
                                /** Som kryss, men dette er kryss som ikke skal ha prikker tegnet på seg */
                }
                x += PIXEL;
            }
            y += PIXEL;
            x = 0;
        }
    }
    /**
     Tilbakestiller posisjoner på Pacman og spøkelser
     */
    public static void reset(){
        pacMan.lever = true;
        pacMan.posisjon.setStartAngle(45);
        pacMan.posisjon.setLength(270);
        pacMan.posisjon.setCenterX(BRETTLENGDE/2);
        pacMan.posisjon.setCenterY(BRETTHOYDE*0.75-14);

        blinky.posisjon.setCenterX(BRETTLENGDE/2);
        blinky.posisjon.setCenterY(BRETTHOYDE/2-60);
        inky.retning = "Vest";
        blinky.poly.setLayoutX(0);
        blinky.poly.setLayoutY(0);

        inky.posisjon.setCenterX(BRETTLENGDE/2-20);
        inky.posisjon.setCenterY(BRETTHOYDE/2-20);
        inky.retning = "Øst";
        inky.poly.setLayoutX(0);
        inky.poly.setLayoutY(0);

        pinky.posisjon.setCenterX(BRETTLENGDE/2);
        pinky.posisjon.setCenterY(BRETTHOYDE/2-20);
        inky.retning = "Øst";
        pinky.poly.setLayoutX(0);
        pinky.poly.setLayoutY(0);

        clyde.posisjon.setCenterX(BRETTLENGDE/2+20);
        clyde.posisjon.setCenterY(BRETTHOYDE/2-20);
        inky.retning = "Vest";
        clyde.poly.setLayoutX(0);
        clyde.poly.setLayoutY(0);

    }
    /**
     Tegner alt på spillbrettet på nytt
     Denne metoden kjører første gang man starter spillet
     og når bruker trykker "Restart"
     */
    public static void nyttSpill(){
        /* Gir farge og fasong til teksten som viser poengsum */
        score.setStroke(Color.WHITE);
        score.setFill(Color.WHITE);
        score.setX(5);
        score.setY(18);
        score.setFont(new Font(20));

        /* Fjerner alt fra spillbrettet (Pane)*/
        spillbrett.getChildren().clear();

        /* Tømmer alle listene vi bruker for å tegne kartet*/
        if(PacMan.hjerteliste != null)
            PacMan.hjerteliste.clear();
        if(veggListe != null)
            veggListe.clear();
        if(kryssListe != null)
            kryssListe.clear();
        if(litenPrikkListe != null)
            litenPrikkListe.clear();
        if (storPrikkListe != null)
            storPrikkListe.clear();

        /* Her begynner vi å tegne alt som skal med på spillbrettet
           og endrer verdier slik at de er klare for et nytt spill
        */

        PacMan.tegnHjerter();
        score.setText("0");
        spillbrett.getChildren().add(score);
        poengsum = 0;
        antLiv = 3;
        if(gameoverTekst != null)
            gameoverTekst.setText("");

        /* Kartet leses inn og tolkes*/
        kartTolking(Kart.kartInnlesing());

        /* Pacman og spøkelsene opprettes, får startposisjon og legges til i Pane */
        pacMan = new PacMan(BRETTLENGDE/2,BRETTHOYDE*0.75-14);
        spillbrett.getChildren().add(pacMan.posisjon);

        /* posisjon er felles navn på en Arc (per spøkelse), den tegnes som en halvsirkel (øvre del),
           poly er et Polygon som er nedre halvdel av spøkelsene, også henger øvre og nedre del sammen.
           Vi gjorde det på denne måten for å kunne bruke getCenterX() og andre nyttige metoder Arc har,
           men ikke Polygon
         */
        blinky = new Blinky(BRETTLENGDE/2,BRETTHOYDE/2-60);
        spillbrett.getChildren().add(blinky.posisjon);
        spillbrett.getChildren().add(blinky.poly);

        pinky = new Pinky(BRETTLENGDE/2,BRETTHOYDE/2-20);
        spillbrett.getChildren().add(pinky.posisjon);
        spillbrett.getChildren().add(pinky.poly);

        inky = new Inky(BRETTLENGDE/2-20, BRETTHOYDE/2-20);
        spillbrett.getChildren().add(inky.posisjon);
        spillbrett.getChildren().add(inky.poly);

        clyde = new Clyde(BRETTLENGDE/2+20, BRETTHOYDE/2-20);
        spillbrett.getChildren().add(clyde.posisjon);
        spillbrett.getChildren().add(clyde.poly);
    }
    /**
     Gameover-sjekken kjøres hver gang Pacman blir spist, og gjør en rekke tiltak
     dersom det viser seg at Pacman har 0 liv igjen
     */
    public static void gameoverSjekk(){
        if(antLiv == 0) {
            /* Forteller spilleren at spillet er over*/
            gameoverTekst = new Text("GAME OVER");
            gameoverTekst.setFont(new Font(80));
            gameoverTekst.setStroke(Color.RED);
            gameoverTekst.setFill(Color.DARKRED);
            gameoverTekst.setX(BRETTLENGDE/11);
            gameoverTekst.setY(BRETTHOYDE/6);
            spillbrett.getChildren().add(gameoverTekst);

            /* Henter highscore-liste fra en fil og viser den i Pane*/
            highScores = Highscore.getHighScores();
            int x = 60, y = 140;
            for (int i = 0; i < highScores.size(); i++){
                Text t = new Text(Highscore.finTekst(highScores.get(i)));
                t.setX(x); t.setY(y);
                t.setFont(new Font(40));
                t.setStroke(Color.DARKGRAY);
                t.setFill(Color.WHITE);
                spillbrett.getChildren().add(t);
                y+=50;
            }
            int plassering = Highscore.checkHighScore(poengsum);

            if (plassering >= 0 && plassering < 10){
                /*
                 Sjekker om den nye poengsummen er god nok til å komme inn på topp-10 lista.
                 Dersom den er det kommer det opp et TextField der bruker kan skrive inn 3 bokstaver og trykke ENTER.
                 Navnet lagres og skrives inn i hs.txt på riktig plass
                */
                TextField textNavn = new TextField();
                textNavn.setLayoutX(BRETTLENGDE/2 - 75);
                textNavn.setLayoutY(BRETTHOYDE/2);
                textNavn.setFont(new Font(50));
                textNavn.setPrefColumnCount(3);
                textNavn.setStyle("-fx-background-color: #000000");
                textNavn.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER){
                        Highscore.setHighScore(plassering, textNavn.getText(), poengsum);
                        nyttSpill();
                    }
                });
                spillbrett.getChildren().add(textNavn);
            }

            /* Tegner er ellipse med tekst på, som fungerer som en restart-knapp*/
            Ellipse button = new Ellipse(100,40);
            button.setStroke(Color.YELLOW);
            button.setCenterX(BRETTLENGDE/2);
            button.setCenterY(BRETTHOYDE/2+250);
            Text rs = new Text(BRETTLENGDE/2-75,BRETTHOYDE/2+265,"Restart");
            rs.setStroke(Color.YELLOW);
            rs.setFont(new Font(50));
            rs.setOnMouseClicked(e-> nyttSpill());
            button.setOnMouseClicked(e-> nyttSpill());
            spillbrett.getChildren().addAll(button,rs);

        }
        /* Hjertene blir svarte etterhvert som Pacman mister liv*/
        PacMan.hjerteliste.get(Spill.antLiv).setFill(Color.BLACK);

        /* Dødsanimasjon der Pacman spiser seg selv.
           Når animasjonen er ferdig kjøres metoden reset() som oppretter alle entitetenes opprinnelige posisjon
         */
        Animation deathAnimation = new Timeline(
                new KeyFrame(Duration.millis(20), e -> pacMan.dødsAnimasjon(pacMan.posisjon)));
        deathAnimation.setCycleCount(100);
        deathAnimation.setOnFinished(e -> reset());
        deathAnimation.play();
    }

    public static void main(String[] args) {
        launch();
    }
}