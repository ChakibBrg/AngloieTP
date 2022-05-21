package com.tp.angloie;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.util.Random;

public class GamePage {
    @FXML public De D1 ;
    @FXML public De D2 ;
    @FXML public Text resultTxt ;
    @FXML public Button lancerDe ;
    @FXML public BorderPane rootContainer ;


    public GamePage() throws FileNotFoundException {

    }

    @FXML
    void initialize(){
        resultTxt.setText(Integer.toString(D1.getRes() + D2.getRes() + 2));
        rootContainer.setLeft(Main.jeu.getPartieActuelle().getPlateau());

    }

    ////////////// Definir l'animation //////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    class AnimationDe extends AnimationTimer {
        private long interval =  34000000 ;
        private long count = 1  ;
        private long  previous = 0 ;
        Random rand = new Random();

        @Override
        public  void handle  ( long current) {
            if (count > 18) {
                stop();
                count = 0;
                resultTxt.setText(Integer.toString(D1.getRes() + D2.getRes() + 2));
            } else {
                if (current - previous > interval * count) {
                    D1.randomizeImg();
                    D2.randomizeImg();
                    D1.playSound();
                    D2.playSound();
                    count++;
                    previous = current;
                }
            }
        }
    }
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////


////////////Lancer les Des //////////////////////////////////////////
    @FXML private void lancerDeClick(ActionEvent e){

      AnimationDe anim = new AnimationDe();
      anim.start();

    }
}
