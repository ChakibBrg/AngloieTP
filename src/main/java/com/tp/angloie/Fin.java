package com.tp.angloie;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Fin extends Case{
    public Fin() {
        super();
        c.setFill(Color.BLACK);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {
        message="Tu as gagné !";
        Main.scene.getRoot().setDisable(true);
        Popup victory = new Popup();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("victoryPopup.fxml"));
        StackPane sp = null;

        try {
            sp = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        victory.getContent().add(sp);
        VictoryPopup controller =  loader.getController();
        sp.getStyleClass().add("stackpane");
        sp.getStylesheets().add(getClass().getResource("app.css").toExternalForm());


        controller.popup = victory;
        victory.show(Main.scene.getWindow());


    }
}
