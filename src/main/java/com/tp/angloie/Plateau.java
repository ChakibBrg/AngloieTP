package com.tp.angloie;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Plateau extends GridPane implements Serializable {
    private Case[] cases;
    private int curseur;

    public Plateau (){
        curseur = 0 ;


    }
}
