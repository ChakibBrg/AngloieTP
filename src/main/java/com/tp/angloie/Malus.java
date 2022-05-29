package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Malus extends Case{
    public Malus() {
        super();
        c.setFill(Color.RED);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {
        points.set(points.get()-10);
        deplacement.set(-2);
    }
}
