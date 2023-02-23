package com.example.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kart {
    private static ArrayList<String> kart;
    public static ArrayList<String> kartInnlesing(){
        try{
            File fil = new File("src/main/java/com/example/pacman/Kart.txt");
            Scanner leser = new Scanner(fil);
            kart = new ArrayList<>();
            while(leser.hasNext()) {
                String hei = leser.next();
                kart.add(hei);
            }
            leser.close();
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet - " + e);
        }
        return kart;
    }

}
