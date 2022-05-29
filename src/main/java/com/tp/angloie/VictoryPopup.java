package com.tp.angloie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

public class VictoryPopup {


    @FXML public StackPane rootPopup;
    @FXML public Popup popup ;

    @FXML public void goBack(ActionEvent e ){

        Main.scene.setRoot(Main.root);
        popup.hide();
        Main.scene.getRoot().setDisable(false);


    }
}
