package com.tp.angloie;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.util.concurrent.atomic.AtomicInteger;

abstract public class Case extends StackPane {
    protected Ellipse c ;
    Button btn =  null ;

    public Case() {
        c = new Ellipse();
        c.setStroke(Color.GREY);
        c.setFill(null);
        c.setStrokeWidth(1);
        c.setRadiusX(28);
        c.setRadiusY(28);
        this.getChildren().add(c);
        this.maxHeight(this.getHeight());
        this.maxWidth(this.getWidth());
        this.minHeight(this.getHeight());
        this.minWidth(this.getWidth());
        btn =new Button();



        c.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                c.setOpacity(0.5);
            }
        });
        c.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                c.setOpacity(1);
                c.setCursor(Cursor.HAND);

            }
        });



    }


    abstract void action(AtomicInteger points, AtomicInteger deplacement);
}
