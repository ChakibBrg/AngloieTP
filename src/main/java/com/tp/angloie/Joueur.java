package com.tp.angloie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Joueur implements Serializable {
    public static int meilleur_record;

    private String nom;
    private int meilleur_score;
    private HashMap<String,Partie> parties_sauvegardees;
    private boolean arret;
    private Set<Integer> cases_visitees;

    public Joueur(String nom, int meilleur_score, HashMap<String,Partie> parties_sauvegardees, boolean arret, Set<Integer> cases_visitees) {
        this.nom = nom;
        this.meilleur_score = meilleur_score;
        this.parties_sauvegardees = parties_sauvegardees;
        this.arret = arret;
        this.cases_visitees = cases_visitees;

    }

    public static int getMeilleur_record() {
        return meilleur_record;
    }

    public String getNom() {
        return nom;
    }

    public int getMeilleur_score() {
        return meilleur_score;
    }

    public HashMap<String,Partie> getParties_sauvegardees() {
        return parties_sauvegardees;
    }

    public boolean isArret() {
        return arret;
    }

    public Set<Integer> getCases_visitees() {
        return cases_visitees;
    }

    public static void setMeilleur_record(int meilleur_record) {
        Joueur.meilleur_record = meilleur_record;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMeilleur_score(int meilleur_score) {
        this.meilleur_score = meilleur_score;
    }

    public void setParties_sauvegardees(HashMap<String,Partie> parties_sauvegardees) {
        this.parties_sauvegardees = parties_sauvegardees;
    }

    public void setArret(boolean arret) {
        this.arret = arret;
    }

    public void setCasesVisitees(Set<Integer> cases_visitees) {
        this.cases_visitees = cases_visitees;
    }

    public void deplacer (int deplacement) {

    }

    public void majPts () {

    }

    public void arreter () {
        arret = true;
    }

    public void sauvegarderPartie (Partie partie) {
        parties_sauvegardees.put(partie.getTitle() , partie);
    }
}
