package com.tp.angloie;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.util.Duration;

public class VictoryPopup {


    @FXML public StackPane rootPopup;
    @FXML public Popup popup ;

    @FXML public void goBack(ActionEvent e ){


        FadeTransition fd = new FadeTransition(new Duration(1500),popup.getContent().get(0).getParent());
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.play();
        popup.hide();

        Utilis.pageTrasition(Main.root);










    }
}
