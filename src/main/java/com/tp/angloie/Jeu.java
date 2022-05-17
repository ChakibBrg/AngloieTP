package com.tp.angloie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Jeu  implements Serializable {
    private ArrayList<Joueur> joueurs;

    private Joueur joueurActuel;
    private Partie partieActuelle;



    public Jeu(ArrayList<Joueur> joueurs, Joueur joueurActuel, Partie partieActuelle) {
        super();
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
