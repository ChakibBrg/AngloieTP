package com.tp.angloie;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Partie implements Serializable {
    private String  title;
    private  int valeurDe;
    private  int posActuelle ,posProchaine  , pts;
    private  Plateau plateau;
    private  Boolean canMove = false;
    private Set<Integer> cases_visitees;
    private  transient   EventHandler<MouseEvent> clickEvent = null  ;


    public void setClickEventPlateau(){
        clickEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (canMove && mouseEvent.getTarget() instanceof Ellipse) {

                    if (plateau.getCases().indexOf(((Ellipse) mouseEvent.getTarget()).getParent()) == posProchaine) {
                        System.out.println("NOICE");
                        action();
                        posActuelle=posProchaine;

                    }

                }


            }
        };
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public Partie (){
        this.title = "";
        this.posActuelle = 0;
        this.pts = 0;
        this.plateau = new Plateau();
        posActuelle = 0 ;
        setClickEventPlateau();
        plateau.setOnMouseClicked(clickEvent);
    }


    public int getPosActuelle() {
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
        if ( plateau != null ) {
            plateau.setOnMouseClicked(clickEvent);
        }
        this.plateau = plateau;
    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    /////////// Partie graphique .../////////////////////

    @FXML transient public De D1 ;
    @FXML transient public De D2 ;
    @FXML transient public Text resultTxt ;
    @FXML transient public Button lancerDe ;
    @FXML transient public BorderPane rootContainer ;




    @FXML
    void initialize(){
        resultTxt.setText(Integer.toString(D1.getRes() + D2.getRes() + 2));
        rootContainer.setCenter(plateau);
        BorderPane.setAlignment(plateau, Pos.CENTER_LEFT);
        setClickEventPlateau();
        plateau.setOnMouseClicked(clickEvent);

    }


////////////////Lancer les Des //////////////////////////////////////////
    @FXML private void lancerDeClick(ActionEvent e) throws InterruptedException {

        D1.playSound();

        for (int i = 0; i < 20; i++) {
                        D1.randomizeImg(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {

                            }
                        });
                        D2.randomizeImg(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                valeurDe = D1.getRes() + D2.getRes() + 2 ;
                                posProchaine = posActuelle + valeurDe;
                                resultTxt.setText(Integer.toString(valeurDe));
                                canMove=true;
                            }
                        });

                    }












    }


     void action (){
        canMove = false;

             AtomicInteger deplacement = new AtomicInteger(0) ;
             do {
                 posProchaine += deplacement.get();
                 plateau.deplacer(posActuelle,posProchaine);
                 plateau.getCases().get(posActuelle).action(Main.jeu.getJoueurActuel(), deplacement);
                 posActuelle=posProchaine;
             }
             while ( deplacement.get() != 0 );



     }










}
