package com.tp.angloie;

import java.lang.reflect.Array;

public class Partie {
    private int posActurelle, pts;
    private Plateau plateau;

    public Partie(int posActurelle, int pts, Plateau plateau) {
        this.posActurelle = posActurelle;
        this.pts = pts;
        this.plateau = plateau;
    }

    public int getPosActurelle() {
        return posActurelle;
    }

    public int getPts() {
        return pts;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPosActurelle(int posActurelle) {
        this.posActurelle = posActurelle;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}
