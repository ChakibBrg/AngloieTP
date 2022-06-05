package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Parcours extends Case{
    public Parcours() {
        super();
        c.setFill(Color.WHITESMOKE);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {
        deplacement.set(0);

    }
}