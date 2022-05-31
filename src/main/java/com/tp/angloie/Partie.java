package com.tp.angloie;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Partie implements Serializable {
    private String  title;
    private  int valeurDe;
    private  int posActuelle=0 ,posProchaine=1  ;
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
             if ( mouseEvent.getTarget() instanceof Ellipse || mouseEvent.getTarget() instanceof Text) {
                 int clickedPos = plateau.getCases().indexOf(((Shape)mouseEvent.getTarget()).getParent());
                 if (!demo.isSelected() ) actionNormale(clickedPos);
                 else actionPourDemo(clickedPos);
             }
            }
        };
    }
private void actionPourDemo (int clickedPos){
        posProchaine = clickedPos ;
        action();

}

private  void actionNormale(int clickedPos){
        if ( canMove ) {
            if (clickedPos == posProchaine) {
                System.out.println("NOICE");
                action();

            } else try {
                throw new MauvaiseCaseException();
            } catch (MauvaiseCaseException e) {
                // afficher le message qui indique le bonne case
            }
        }

}


    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    /////////// Partie graphique .../////////////////////
    @FXML transient public De D1 ;
    @FXML transient public De D2 ;
    @FXML transient public Text resultTxt ;
    @FXML transient public Button lancerDe ;
    @FXML transient AnchorPane plateauContainer;
    @FXML transient Label instructionPartie;
    @FXML transient Label playerNameLabel;
    @FXML transient Label scoreLabel;


    @FXML transient CheckBox demo;


    @FXML
    void initialize(){


        playerNameLabel.setText(Main.jeu.getJoueurActuel().getNom());
        scoreLabel.setText(Integer.toString(points.get()));
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
                                instructionPartie.setText("Allez à la case "+posProchaine);
                                if ( posProchaine >99 ){
                                    int over =  posProchaine -100;
                                    posProchaine = 100-over ;
                                    instructionPartie.setText("Allez à la case "+posProchaine);
                                }
                            }
                        });
                    }



    }


    //////// Action a faire apres avoir cliqué sur la bonne case
     void action (){
        canMove = false;
         plateau.startAnim(posActuelle,posProchaine);
         lancerDe.setDisable(false);

        AtomicInteger deplacement = new AtomicInteger(0) ;// le deplacement est la prochaine position
         do {
                 deplacement.set(0);
                 plateau.deplacer(posActuelle,posProchaine);
                 instructionPartie.setText(plateau.getCases().get(posActuelle).message);
                 plateau.getCases().get(posProchaine).action(points, deplacement);
                 posActuelle=posProchaine;
                 posProchaine += deplacement.get();
                 if ( posProchaine <0) posProchaine = 0 ;
                 if ( posProchaine >99 ){
                     int over =  posProchaine -100;
                     posProchaine = 100-over ;
                     instructionPartie.setText("Allez à la case "+posProchaine);
                 }
                if ( plateau.getCases().get(posActuelle) instanceof Saut){
                    lancerDe.setDisable(true);
                    instructionPartie.setText("Allez à la case "+posProchaine);
                    canMove = true;
                    return ;
                }
             scoreLabel.setText(Integer.toString(points.get()));


         }while ( deplacement.get() != 0 );

     }




     class MauvaiseCaseException extends Exception {};









}
