package com.tp.angloie;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
        deplacement.set(0);
        message="Veuillez saisir le mot qui convient à la définition !";
        Main.jeu.getPartieActuelle().setInstruction(message);
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
        definition.setWrappingWidth(700);
        TextField mot = new TextField(motCache);
        mot.setAlignment(Pos.CENTER);

 ///////////////////// limité le length  du texte  ////////////
        ////////////////////////////////////////////////////

        mot.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (mot.getText().length() > taille) {
                    String s = mot.getText().substring(0, taille);
                    mot.setText(s);
                }
            }
        });

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
                    if ( motRecup.toLowerCase().equalsIgnoreCase( qst.getMot()) ) {

                        message="Correcte!";
                        Main.jeu.getPartieActuelle().setInstruction(message);

                        points.set(points.get()+20);
                        deplacement.set(4);
                        try {
                            Main.jeu.getPartieActuelle().setPosPoints(deplacement);
                        } catch (Partie.caseSautException e) {
                            e.printStackTrace();
                        }
                        Main.jeu.getPartieActuelle().majAvatar(deplacement);
                    }
                    else {

                        message="Faux!";
                        Main.jeu.getPartieActuelle().setInstruction(message);
                        points.set(points.get()-10);
                        try {
                            Main.jeu.getPartieActuelle().setPosPoints(deplacement);
                        } catch (Partie.caseSautException e) {
                            e.printStackTrace();
                        }
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
