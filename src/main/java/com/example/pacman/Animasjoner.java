package com.example.pacman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Animasjoner {
    protected static Animation animation, blinkyAnimation, inkyAnimation, pinkyAnimation, clydeAnimation, pacAnimation;

    public static void startAnimation(){
        animation = new Timeline(
                    new KeyFrame(Duration.millis(15), e -> Spill.pacMan.bevegelse(Spill.pacMan.ret)));
            animation.setCycleCount(Timeline.INDEFINITE);

        pacAnimation = new Timeline(
                    new KeyFrame(Duration.millis(5), e -> Spill.pacMan.pacAnimasjon(Spill.pacMan.posisjon, Spill.pacMan.ret)));
            pacAnimation.setCycleCount(Timeline.INDEFINITE);

        blinkyAnimation = new Timeline(
                    new KeyFrame(Duration.millis(15), e -> Spill.blinky.bevegelse()));
            blinkyAnimation.setCycleCount(Timeline.INDEFINITE);

        inkyAnimation = new Timeline(
                    new KeyFrame(Duration.millis(15), e -> Spill.inky.bevegelse()));
            inkyAnimation.setCycleCount(Timeline.INDEFINITE);

        pinkyAnimation = new Timeline(
                    new KeyFrame(Duration.millis(15), e -> Spill.pinky.bevegelse()));
            pinkyAnimation.setCycleCount(Timeline.INDEFINITE);

        clydeAnimation = new Timeline(
                    new KeyFrame(Duration.millis(15), e -> Spill.clyde.bevegelse()));
            clydeAnimation.setCycleCount(Timeline.INDEFINITE);

        //if(Spill.retningSjekk != retning && lever == true) {


        //}
    }
    public static void pauseSpokelser(){
        blinkyAnimation.pause();
        pinkyAnimation.pause();
        inkyAnimation.pause();
        clydeAnimation.pause();
    }
    public static void startSpokelser(){
        blinkyAnimation.play();
        pinkyAnimation.play();
        inkyAnimation.play();
        clydeAnimation.play();
    }

    public static void skremtSpokelse(){
        Spill.blinky.posisjon.setFill(Color.BLUE);
        Spill.blinky.poly.setFill(Color.BLUE);
        Animation blinkySkremt = new Timeline(
                new KeyFrame(Duration.millis(35), e -> Spill.blinky.skremtBevegelse()));
        blinkySkremt.setCycleCount(200);

        //blinkySkremt.setOnFinished(e -> blinkyAnimation.play());
        blinkySkremt.setOnFinished(e -> Spill.blinky.nullStill());
        blinkySkremt.play();
    }

}
