package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static com.example.pacman.Spokelse.trekkTall;

/**
 * Brukes for å tegne vegger på spillbrettet og å opprette tilhørende boundingbokser
 */
public class Vegg extends Entitet{
int red = 0;
int green = 255;
int blue = 0;
double sat = 1.0;
    /**
     * Et vegg-objekt inneholder en x-verdi, en y-verdi og en boundingbox
     * @param x
     * @param y
     */
    public Vegg (double x, double y){
        super(x,y);
        boks = lagBoks(x, y);
    }

    /**
     * Tegner vegg i form av et vanlig blått rektangel
     * @return
     */
    public Rectangle tegnVegg(Color farge) {
        Rectangle bounding = new Rectangle(x, y, 20, 20);
        bounding.setStroke(farge);
        return bounding;
    }

    /**
     * Tegner mer fancy vegger for at banen skal bli kulere
     * @return
     */
    public ArrayList<Rectangle> fancyVegg(Color farge, int random, int masseFarger){
        ArrayList<Rectangle> v = new ArrayList<>();
        double dx = 1, dy = 1;
        for (int i = 0; i<4; i++){
            for(int k = 0; k<3; k++) {
                Rectangle r = new Rectangle(x + dx ,y + dy ,5,3);

                if(masseFarger == 1){
                    switch (random) {
                        case 1: // Sunset
                            red = 255;
                            blue = 0;
                            farge = (Color.rgb(red, green -= 20, blue));
                            break;

                        case 2: // Blå/lilla
                            red += 20;
                            green = 0;
                            blue = 255;
                            farge = (Color.rgb(red, green, blue));
                            break;

                        case 3: // Grønn/svart
                            red = 40;
                            green -= 20;
                            blue = 40;
                            farge = (Color.rgb(red, green, blue));
                            break;

                        case 4: // Tilfeldige farger
                            farge = (Color.rgb(trekkTall(0, 255), trekkTall(0, 255), trekkTall(0, 255)));
                    }
                }
                r.setFill(farge);
                v.add(r);
                dx += 6;
            }
            dx = 1;
            dy += 4;
        }
        return v;
    }

    /**
     * Oppretter en boundingbox per vegg, slik at vi kan oppdage når Pacman og spøkelser kjører i en vegg
     * @param x
     * @param y
     * @return
     */
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x, y, 20, 20);
        return boks;
    }
    public double saturation(){
        if(sat == 0)
            sat += 0.1;
        else if(sat == 1.0)
            sat -= 0.1;
        return sat;
    }
}
