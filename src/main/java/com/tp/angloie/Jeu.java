package com.tp.angloie;

import java.io.Serializable;
import java.util.*;

public class Jeu  implements Serializable {
    private TreeMap<String,Joueur> joueurs;
    private Joueur joueurActuel;
    private Partie partieActuelle;



    public Jeu(TreeMap<String,Joueur>  joueurs, Joueur joueurActuel, Partie partieActuelle) {

        this.joueurs = joueurs;
        this.joueurActuel = joueurActuel;
        this.partieActuelle = partieActuelle;
    }



    public TreeMap<String,Joueur>  getJoueurs() {
        return joueurs;
    }



    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public Partie getPartieActuelle() {
        return partieActuelle;
    }

    public void setJoueurs(TreeMap<String,Joueur> joueurs ) {
        this.joueurs = joueurs;
    }

    public void setJoueurActuel(Joueur joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    public void setPartieActuelle(Partie partieActuelle) {
        this.partieActuelle = partieActuelle;
    }

    public LinkedList<String> classerJoueurs(){
        ArrayList<Joueur> classer = new ArrayList<>(joueurs.values());

        classer.sort(new Comparator<Joueur>() {
            @Override
            public int compare(Joueur o1, Joueur o2) {
                return -Integer.compare(o1.getMeilleurScore(),o2.getMeilleurScore());
            }
        });



           LinkedList<String> affichage = new LinkedList<>();
           int i = 1;
            for(Joueur tmp  : classer ){
                affichage.add(i+")     "+tmp.getNom()+":     "+tmp.getMeilleurScore()+"  Pts.");
                i++;
            }


            return affichage ;
    }
}
