package com.example.pacman;

/** Klassen tar seg av highscores og er ganske gunnleggende.*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscore {
    public static ArrayList<String> highScores;

    /** Metoden henter ut highscores fra en tekstfil og gir tilbake en Arraylist av strings.
     * Her må du kanskje pathe til hs.txt selv? */
    public static ArrayList<String> getHighScores(){
        try{
            File fil = new File("src/main/java/com/example/pacman/hs.txt");
            Scanner leser = new Scanner(fil);
            highScores = new ArrayList<>();
            while(leser.hasNext()) {
                String hei = leser.next();
                highScores.add(hei);
            }
            leser.close();
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet - " + e);
        }
        return highScores;
    }
    /** Sjekker om du har kommet på highscorelista*/
    public static int checkHighScore(int poeng){
        for (int i = 0; i < Spill.highScores.size(); i++){
            String s = Spill.highScores.get(i);
            String[] as = s.split(";");
            if(poeng > Integer.parseInt(as[2])){
                return i;
            }
        }
        return -1;
    }
    /** Setter inn din score i listen og skyver alle under deg ett hakk ned.
     * Her er vi klar over endel svakheter som kan utnyttes. Skriver man inn ";" vil hele koden sikkert gå ned
     * men vi prioriterer ikke det nå.*/
    public static void setHighScore(int plassering, String navn, int poeng){
        ArrayList<String> oppdatertListe = new ArrayList<>();
        Spill.highScores.add(plassering,plassering + ";" + navn + ";" + poeng);

        for (int i= 0; i < plassering; i++){
            oppdatertListe.add(Spill.highScores.get(i));
        }
        for (int i = plassering; i<Spill.highScores.size(); i++){
            String s = Spill.highScores.get(i);
            String[] as = s.split(";");
            oppdatertListe.add(Integer.parseInt(as[0])+1 + ";" + as[1] + ";"+ as[2]);
        }
        try{
        PrintWriter writer = new PrintWriter(new File("src/main/java/com/example/pacman/hs.txt"));
        for (int i = 0; i < oppdatertListe.size(); i++) {

            writer.println(oppdatertListe.get(i));
        }
        writer.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    /** Forskyver teksten litt for det blir lagt ut på spillbrettet.*/
    public static String finTekst(String s){
        String[] as = s.split(";");
        return as[0] + " :          " + as[1] + "      :     "  + as[2];
    }
}
