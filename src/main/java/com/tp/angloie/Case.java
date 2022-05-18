package com.tp.angloie;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

abstract public class Case extends Circle implements Serializable {
    public Case() {
        setStroke(Color.GREY);
        setFill(null);
        setStrokeWidth(1);
        setRadius(26);




    }


    abstract void action(Joueur player);
}
