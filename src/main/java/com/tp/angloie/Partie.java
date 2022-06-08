package com.tp.angloie;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
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
    private transient   EventHandler<MouseEvent> clickEvent = null  ;
    private transient EventHandler<KeyEvent> exitEvent=null;

    public Partie (){
        this.title = "";
        this.posActuelle = 0;
        this.points = new AtomicInteger(0);
        try {
            this.plateau = new Plateau();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        posActuelle = 0 ;




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
                 else {
                     if (!(plateau.getCases().get(clickedPos) instanceof Parcours)) actionPourDemo(clickedPos);
                     else actionNormale(clickedPos);
                 }
             }
            }
        };
    }
    public void setExitEvent() {
        exitEvent = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == event.getCode().ESCAPE) {
                    //Main.stage.close();
                    Main.scene.getRoot().setDisable(true);
                    Popup quitter = new Popup();
                    quitter.setHideOnEscape(false);

                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("ExitPopUp.fxml"));
                    StackPane sp = null;

                    try {
                        sp = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    quitter.getContent().add(sp);
                    ExitPopUp controller =  loader.getController();
                    sp.getStyleClass().add("stackpane");
                    sp.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

                    controller.popup = quitter;
                    quitter.show(Main.scene.getWindow());
                    FadeTransition fd = new FadeTransition(new Duration(1000),quitter.getContent().get(0).getParent());
                    fd.setFromValue(0);
                    fd.setToValue(1);
                    fd.play();
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
                    action();

                } else try {
                    throw new MauvaiseCaseException();
                } catch (MauvaiseCaseException e) {
                    FadeTransition ft = new FadeTransition();
                    ft.setNode(instructionPartie);
                    ft.setFromValue(1);
                    ft.setCycleCount(2);
                    ft.setToValue(0);
                    ft.setAutoReverse(true);
                    ft.setDuration(new Duration(500));
                    ft.play();
                    instructionPartie.setTextFill(Color.RED);
                    ft.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            instructionPartie.setTextFill(Color.WHITE);
                        }
                    });
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
    @FXML transient GridPane plateauContainer;
    @FXML transient Label instructionPartie;
    @FXML transient Label playerNameLabel;
    @FXML transient Label scoreLabel;
    @FXML transient CheckBox demo;
    @FXML transient GridPane infosBar;

    @FXML
    void initialize(){


        infosBar.getStyleClass().add("gridpane");
        infosBar.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

        plateau.getStyleClass().add("gridpane");
        plateau.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

        playerNameLabel.getStyleClass().add("instructionLabel");
        playerNameLabel.getStylesheets().add(getClass().getResource("app.css").toExternalForm());


        instructionPartie.getStyleClass().add("instructionLabel");
        instructionPartie.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

        playerNameLabel.setText(Main.jeu.getJoueurActuel().getNom());
        scoreLabel.setText(Integer.toString(points.get()));
        resultTxt.setText(Integer.toString(D1.getRes() + D2.getRes() + 2));

        GridPane.setHalignment(plateau,HPos.RIGHT);
        GridPane.setValignment(plateau,VPos.CENTER);

        plateauContainer.setPadding(new Insets(20));
        plateauContainer.add(plateau,1,0);


        setClickEventPlateau();

        plateau.setOnMouseClicked(clickEvent);

        setExitEvent();
        plateauContainer.setOnKeyPressed(exitEvent);



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
        majAvatar(deplacement);
    }







    public void majAvatar( AtomicInteger deplacement){
            do {
                plateau.deplacer(posActuelle,posProchaine);
                plateau.getCases().get(posProchaine).action(points, deplacement);

                try {
                    setPosPoints(deplacement);
                }catch (caseSautException e){
                    return; //aller attendre le clique du joueur
                }

            }while ( deplacement.get() != 0 );
        }



        public void setInstruction(String msg ){
            instructionPartie.setText(msg);
        }


     public void setPosPoints ( AtomicInteger deplacement) throws caseSautException{

         posActuelle=posProchaine;
         posProchaine += deplacement.get();
         if ( plateau.getCases().get(posActuelle) instanceof Saut){
             posProchaine = deplacement.get();
             lancerDe.setDisable(true);
             instructionPartie.setText("Allez à la case "+posProchaine);
             canMove = true;
             throw new caseSautException();
         }
         if ( posProchaine >99 ){
             int over =  posProchaine -99;
             posProchaine = 99-over ;
             instructionPartie.setText("Allez à la case "+posProchaine);
         }
         if ( posProchaine <0) posProchaine = 0 ; // Si on tombe sur une case malus qui est  la premiere case
         if ( points.get()<0) points.set(0);
         scoreLabel.setText(Integer.toString(points.get()));
     }


     class MauvaiseCaseException extends Exception {};
     class caseSautException extends Exception{}; // pour attendre le clique de lutilisateur en cas d'une case saut








}
