package com.tp.angloie;

import java.util.ArrayList;

public class Jeu {
    private ArrayList<Joueur> joueurs;
    private Joueur joueurActuel;
    private Partie partieActuelle;

    public Jeu(ArrayList<Joueur> joueurs, Joueur joueurActuel, Partie partieActuelle) {
        this.joueurs = joueurs;
        this.joueurActuel = joueurActuel;
        this.partieActuelle = partieActuelle;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public Partie getPartieActuelle() {
        return partieActuelle;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public void setJoueurActuel(Joueur joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    public void setPartieActuelle(Partie partieActuelle) {
        this.partieActuelle = partieActuelle;
    }
}
