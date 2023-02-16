package com.example.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kart {
    private static ArrayList<String> kart;
    public static void kartInnlesing(){
        try{
            File fil = new File("src/main/java/com/example/pacman/Kart.txt");
            Scanner leser = new Scanner(fil);
            kart = new ArrayList<>();
            while(leser.hasNext()) {
                String hei = leser.next();
                kart.add(hei);
            }
            System.out.println(kart.size());
            System.out.println(kart);
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet - " + e);
        }
        kartTolking();
    }
    public static void kartTolking(){
        for(int i = 0; i< kart.size(); i++)
            for(int k = 0; k < kart.get(i).length(); k++){
                //System.out.println(kart.get(i).charAt(k));
                switch (kart.get(i).charAt(k)){
                    case '#' : System.out.println("Vegg"); break;
                    case 'G' : System.out.println("Spøkelse");  break;
                    case 'D' : System.out.println("Liten prikk");  break;
                    case 'R' : System.out.println("Tomrom!");  break;
                    case 'B' : System.out.println("Bigboy");  break;
                    case 'Ø' : System.out.println("Dør");  break;
                }
        }
    }
}
