package com.tp.angloie;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

abstract public class Case extends Circle implements Serializable {
    public Case() {
        setStroke(Color.BROWN);
        setFill(null);
        setStrokeWidth(2);
        setRadius(10);




    }


    abstract void action(Joueur player);
}
