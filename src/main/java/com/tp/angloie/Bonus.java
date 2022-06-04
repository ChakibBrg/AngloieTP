package com.tp.angloie;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.util.concurrent.atomic.AtomicInteger;

public class Bonus extends Case{
    public Bonus() {
        super();
        c.setFill(Color.GREEN);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {

        /*Main.scene.getRoot().setDisable(true);
        Popup popup = new Popup();
        Label label = new Label("Chakib");
        label.setFont(Font.font("Verdana",15));
        label.setTextFill(Color.WHITE);
        popup.getContent().add(label);
        popup.show(Main.scene.getWindow());*/
        points.set(points.get()+10);
        deplacement.set(2);
    }
}
