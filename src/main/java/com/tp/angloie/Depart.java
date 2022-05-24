package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Depart extends Case{
    public Depart() {

        super();
        c.setFill(Color.YELLOW);

    }

    @Override
    void action(Joueur player, AtomicInteger deplacement) {
    }
}
