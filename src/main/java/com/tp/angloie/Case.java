package com.tp.angloie;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.io.Serializable;

abstract public class Case extends Ellipse {
    public Case() {
        setStroke(Color.GREY);
        setFill(null);
        setStrokeWidth(1);
        setRadiusX(26);
        setRadiusY(26);




    }


    abstract void action(Joueur player);
}
