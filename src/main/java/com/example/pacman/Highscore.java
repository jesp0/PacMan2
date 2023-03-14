package com.example.pacman;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscore {
    public static ArrayList<String> highScores;

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
    public static String finTekst(String s){
        String[] as = s.split(";");
        return as[0] + " :          " + as[1] + "      :     "  + as[2];
    }
}

class Score{
    int plassering;
    String navn;
    int poeng;
    public Score(int plassering, String navn, int poeng){
        this.plassering = plassering;
        this.navn = navn;
        this.poeng = poeng;
    }
}
