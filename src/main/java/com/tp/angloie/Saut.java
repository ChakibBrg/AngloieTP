package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Saut extends Case{

    public Saut() {
        super();
        c.setFill(Color.ORANGE);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {
        Random rand = new Random();
        deplacement.set( rand.nextInt(1,98) );

    }
}
