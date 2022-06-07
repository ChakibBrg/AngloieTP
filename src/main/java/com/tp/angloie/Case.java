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

    String message  ;


    public Case() {
        c = new Ellipse();
        c.setStroke(Color.GREY);
        c.setFill(null);
        c.setStrokeWidth(2);
        c.setStroke(Color.DARKGRAY);
         double radius = Main.scene.getHeight()/30 ;
        c.setRadiusX(radius);
        c.setRadiusY(radius);
        this.getChildren().add(c);
        this.maxHeight(this.getHeight());
        this.maxWidth(this.getWidth());
        this.minHeight(this.getHeight());
        this.minWidth(this.getWidth());
        this.setCursor(Cursor.HAND);
        btn =new Button();



        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                c.setOpacity(0.5);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                c.setOpacity(1);


            }
        });



    }


    abstract void action(AtomicInteger points, AtomicInteger deplacement);


}
