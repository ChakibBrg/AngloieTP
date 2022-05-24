package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Malus extends Case{
    public Malus() {
        super();
        c.setFill(Color.RED);

    }

    @Override
    void action(Joueur player, AtomicInteger deplacement) {

    }
}
