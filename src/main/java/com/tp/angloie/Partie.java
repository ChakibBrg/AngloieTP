package com.tp.angloie;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Partie implements Serializable {
    private String  title;
    private  int valeurDe;
    private  int posActuelle ,posProchaine  ;
    private AtomicInteger points ;
    private  Plateau plateau;
    private  Boolean canMove = false;
    private Set<Integer> cases_visitees;
    private  transient   EventHandler<MouseEvent> clickEvent = null  ;

    public Partie (){
        this.title = "";
        this.posActuelle = 0;
        this.points = new AtomicInteger(0);
        this.plateau = new Plateau();
        posActuelle = 0 ;
        setClickEventPlateau();
        plateau.setOnMouseClicked(clickEvent);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }




    public int getPosActuelle() {
        return posActuelle;
    }

    public AtomicInteger getPts() {
        return points;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPosActurelle(int posActurelle) {
        this.posActuelle = posActurelle;
    }

    public void setPts(AtomicInteger pts) {
        this.points = pts;
    }

    public void setPlateau(Plateau plateau) {
        if ( plateau != null ) {
            plateau.setOnMouseClicked(clickEvent);
        }
        this.plateau = plateau;
    }




    /////// Affecter l'evenement du clique pour le plateau
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
                    else try {
                        throw new MauvaiseCaseException();
                    } catch (MauvaiseCaseException e) {
                        // afficher le message qui indique le bonne case
                    }


                }


            }
        };
    }



    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    /////////// Partie graphique .../////////////////////
    @FXML transient public De D1 ;
    @FXML transient public De D2 ;
    @FXML transient public Text resultTxt ;
    @FXML transient public Button lancerDe ;
    @FXML transient AnchorPane plateauContainer;
    @FXML
    void initialize(){
        resultTxt.setText(Integer.toString(D1.getRes() + D2.getRes() + 2));

        plateauContainer.getChildren().add(plateau);
        setClickEventPlateau();
        GridPane.setHalignment(plateauContainer, HPos.CENTER);
        GridPane.setValignment(plateauContainer, VPos.CENTER);
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
                                lancerDe.setDisable(true);
                            }
                        });
                    }


    }


    //////// Action a faire apres avoir cliqué sur la bonne case
     void action (){
        canMove = false;
         lancerDe.setDisable(false);
         AtomicInteger deplacement = new AtomicInteger(0) ;
             do {
                 posProchaine += deplacement.get();
                 plateau.deplacer(posActuelle,posProchaine);
                 plateau.getCases().get(posActuelle).action(points, deplacement);
                 posActuelle=posProchaine;
             }
             while ( deplacement.get() != 0 );
     }




     class MauvaiseCaseException extends Exception {};









}
