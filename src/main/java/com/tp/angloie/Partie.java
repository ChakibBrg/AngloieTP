package com.tp.angloie;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Partie implements Serializable {
    String title;
    private int posActuelle, pts;
    private Plateau plateau;



    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Partie(int posActurelle, int pts, Plateau plateau) {
        this.title = "";
        this.posActuelle = posActurelle;
        this.pts = pts;
        this.plateau = plateau;
    }

    public int getPosActurelle() {
        return posActuelle;
    }

    public int getPts() {
        return pts;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPosActurelle(int posActurelle) {
        this.posActuelle = posActurelle;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}
