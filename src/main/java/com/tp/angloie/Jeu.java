package com.tp.angloie;

import java.io.Serializable;
import java.util.HashMap;

public class Jeu  implements Serializable {
    private HashMap<String,Joueur> joueurs;
    private Joueur joueurActuel;
    private Partie partieActuelle;



    public Jeu(HashMap<String,Joueur>  joueurs, Joueur joueurActuel, Partie partieActuelle) {
        super();
        this.joueurs = joueurs;
        this.joueurActuel = joueurActuel;
        this.partieActuelle = partieActuelle;
    }

    public HashMap<String,Joueur>  getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public Partie getPartieActuelle() {
        return partieActuelle;
    }

    public void setJoueurs(HashMap<String,Joueur> joueurs ) {
        this.joueurs = joueurs;
    }

    public void setJoueurActuel(Joueur joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    public void setPartieActuelle(Partie partieActuelle) {
        this.partieActuelle = partieActuelle;
    }
}
