package com.example.pacman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Animasjoner {
    protected static Animation animation;
    protected static Animation pacAnimation;
    protected static Animation blinkyAnimation, inkyAnimation, pinkyAnimation, clydeAnimation;

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
    }
}
