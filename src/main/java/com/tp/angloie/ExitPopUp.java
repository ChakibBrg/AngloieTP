package com.tp.angloie;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ExitPopUp {

    @FXML public Popup popup;
    @FXML public StackPane rootPopup;
    @FXML Button closeButton;

    @FXML public void reprendre (ActionEvent e) {
        FadeTransition fd = new FadeTransition(new Duration(1500),popup.getContent().get(0).getParent());
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.play();
        popup.hide();
        Main.scene.getRoot().setDisable(false);

    }

    @FXML public void Sauvegarder (ActionEvent e) {
        //Main.stage.close();
        LocalDate date = LocalDate.now();
        Main.jeu.getPartieActuelle().setTitle("Partie sauvegardée: " + date.toString() + " " + Integer.toString(LocalTime.now().getHour()) + ":" + Integer.toString(LocalTime.now().getMinute()));
        System.out.println(Main.jeu.getPartieActuelle().getTitle());
        Main.jeu.getJoueurActuel().sauvegarderPartie(Main.jeu.getPartieActuelle());

    }

    @FXML public void goBack (ActionEvent e) {
        FadeTransition fd = new FadeTransition(new Duration(1500),popup.getContent().get(0).getParent());
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.play();
        popup.hide();
        try {
            Main.root = FXMLLoader.load(getClass().getResource("newGamePage.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Utilis.pageTrasition(Main.root);
    }

}
