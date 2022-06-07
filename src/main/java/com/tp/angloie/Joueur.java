package com.tp.angloie;

import java.io.Serializable;
import java.util.HashMap;

public class Joueur implements Serializable {
    private static int meilleurRecord;

    private String nom;
    private int meilleurScore;
    private HashMap<String,Partie> partiesSauvegardees;

    public Joueur(String nom, int meilleurScore, HashMap<String, Partie> partiesSauvegardees) {
        this.nom = nom;
        this.meilleurScore = meilleurScore;
        this.partiesSauvegardees = partiesSauvegardees;


    }

    public static int getMeilleurRecord() {
        return meilleurRecord;
    }

    public String getNom() {
        return nom;
    }

    public int getMeilleurScore() {
        return meilleurScore;
    }

    public HashMap<String,Partie> getPartiesSauvegardees() {
        return partiesSauvegardees;
    }

    public static void setMeilleurRecord(int meilleurRecord) {
        Joueur.meilleurRecord = meilleurRecord;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMeilleurScore(int meilleur_score) {
        this.meilleurScore = meilleur_score;
    }

    public void setPartiesSauvegardees(HashMap<String,Partie> partiesSauvegardees) {
        this.partiesSauvegardees = partiesSauvegardees;
    }


    public void sauvegarderPartie (Partie partie) {
        partiesSauvegardees.put(partie.getTitle() , partie);
    }



}
