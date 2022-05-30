package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Bonus extends Case{
    public Bonus() {
        super();
        c.setFill(Color.GREEN);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {



        points.set(points.get()+10);
        deplacement.set(2);
    }
}
