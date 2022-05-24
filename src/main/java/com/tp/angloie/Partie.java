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
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Partie implements Serializable {
    String title;
    private volatile int valeurDe;
    private volatile int posActuelle ,posProchaine  , pts;
    private volatile Plateau plateau;
    private volatile AtomicBoolean canMove = new AtomicBoolean(false) ;
    private final  EventHandler<MouseEvent> clickEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (canMove.get() && mouseEvent.getTarget() instanceof Ellipse) {

                if (plateau.getCases().indexOf(((Ellipse) mouseEvent.getTarget()).getParent()) == posProchaine) {
                    System.out.println("NOICE");
                    action();
                    posActuelle=posProchaine;

                }

            }


        }
    };

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
        plateau.setOnMouseClicked(clickEvent);

    }

    public Partie(int posActurelle, int pts, Plateau plateau) {
        this.title = "";
        this.posActuelle = posActurelle;
        this.pts = pts;
        this.plateau = plateau;
        posActuelle = 0 ;

        /// Definir l'evenement du plateau
        if ( plateau != null ) {
            plateau.setOnMouseClicked(clickEvent);
        }
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

    @FXML public De D1 ;
    @FXML public De D2 ;
    @FXML public Text resultTxt ;
    @FXML public Button lancerDe ;
    @FXML public BorderPane rootContainer ;




    @FXML
    void initialize(){
        resultTxt.setText(Integer.toString(D1.getRes() + D2.getRes() + 2));
        rootContainer.setCenter(plateau);
        BorderPane.setAlignment(plateau, Pos.CENTER_LEFT);

    }


////////////////Lancer les Des //////////////////////////////////////////
    @FXML private void lancerDeClick(ActionEvent e) throws InterruptedException {




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
                                canMove.set(true);
                            }
                        });
                        D1.playSound();

                    }












    }


     void action (){
        canMove.set(false);

             AtomicInteger deplacement = new AtomicInteger(0) ;
             do {
                 plateau.deplacer(posActuelle,posProchaine);
                 posActuelle=posProchaine+deplacement.get();
                 plateau.getCases().get(posActuelle).action(Main.jeu.getJoueurActuel(), deplacement);

             }
             while ( deplacement.get() != 0 );



     }










}
