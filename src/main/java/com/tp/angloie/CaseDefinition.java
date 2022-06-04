package com.tp.angloie;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CaseDefinition extends Question{
    private ArrayList<QuestionData> questionData;
    public CaseDefinition() {
        super();
        c.setFill(Color.BLUE);

    }
    public CaseDefinition(ArrayList<QuestionData> questionData) {
        //super(qst);
        this.questionData = questionData;
        c.setFill(Color.BLUE);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {
        //Pour avoir une nouvelle question même sur une même case
        Random randGen = new Random();
        int index = randGen.nextInt(0, questionData.size());
        this.qst = questionData.get(index);
        Main.scene.getRoot().setDisable(true);
        Popup popup = new Popup();
        String motCache = "";
        int taille = qst.getMot().length();
        System.out.println("Taille: "+ taille);
        for (int i = 0; i < taille; i++) {
            motCache = motCache + "-";
        }
        Text definition = new Text(qst.getDef());
        definition.setFill(Color.WHITE);
        definition.setFont(Font.font("Verdana", 15));
        TextField mot = new TextField(motCache);
        //mot.setMaxSize(taille, taille);
        mot.setFont(Font.font("Verdana", 12));
        //Event related with text field
        mot.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCharacter());
                if (mot.getText().length() >= taille) {
                    System.out.println(mot.getLength());
                    System.out.println(mot.getText().toUpperCase());
                    //mot.setDisable(true);
                    event.consume();
                }
                else {
                    mot.setDisable(false);
                }
                if (event.getCode() == event.getCode().ENTER) {
                    event.consume();
                    String motRecup = mot.getText();
                    if (motRecup.toLowerCase() == qst.getMot()) {
                        points.set(points.get()+20);
                        deplacement.set(4);
                    }
                    else {
                        points.set(points.get()-10);
                    }
                    Main.scene.getRoot().setDisable(false);
                    popup.hide();
                }
            }
        });
        VBox vbox = new VBox();
        vbox.getChildren().add(definition);
        vbox.getChildren().add(mot);
        popup.getContent().add(vbox);
        popup.show(Main.scene.getWindow());

    }

    @Override
    void Verification(Joueur player) {

    }
}
