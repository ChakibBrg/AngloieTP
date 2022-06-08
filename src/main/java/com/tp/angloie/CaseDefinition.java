package com.tp.angloie;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CaseDefinition extends Question{
    private ArrayList<QuestionData> questionData;
    private int i = 0;
    public CaseDefinition() {
        super();
        c.setFill(Color.BLUE);

    }
    public CaseDefinition(ArrayList<QuestionData> questionData) {

        this.questionData = questionData;
        c.setFill(Color.BLUE);

    }

    public void setLimitTextField (TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (textField.getText().length() > 1) {
                    String s = textField.getText().substring(0, 1);
                    textField.setText(s);
                }
            }
        });
        textField.setFont(Font.font("Verdana", 12));
        textField.setPrefWidth(50);
    }

    public void setKeyPressedTextField (TextField[] textField) {

        for (int j = 0; j < textField.length; j++) {

            textField[j].setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    int i = 0;
                    while (!textField[i].isFocused()) {
                        i++;
                    }

                    if (event.getCode() == KeyCode.BACK_SPACE) {

                            if (i > 0) {
                                textField[i - 1].requestFocus();
                            }

                    }
                    else if (event.getCode() == KeyCode.RIGHT) {
                        if (i < textField.length - 1) {
                            textField[i + 1].requestFocus();
                        }
                    }
                    else if (event.getCode() == KeyCode.LEFT) {
                        if (i > 0) {
                            textField[i - 1].requestFocus();
                        }
                    }
                    else {
                        if (i < textField.length - 1) {
                            if (textField[i].getText().length() > 0) {
                                textField[i + 1].requestFocus();
                            }
                        }
                    }
                }
            });


        }
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
        definition.setTextAlignment(TextAlignment.CENTER);
        TextField mot = new TextField(motCache);
        HBox motContainer = new HBox();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15));
        TextField[] motNouv = new TextField[taille];
        mot.setAlignment(Pos.CENTER);

 ///////////////////// limité le length  du texte  ////////////
        ////////////////////////////////////////////////////
        int i = 0;
        for (i = 0; i < taille; i++) {
            motNouv[i] = new TextField("-");
            setLimitTextField(motNouv[i]);
        }
        motNouv[2].requestFocus();

        mot.setFont(Font.font("Verdana", 12));
        //Event related with text field
        mot.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    event.consume();
                    String motRecup = mot.getText();
                    if ( motRecup.toLowerCase().equalsIgnoreCase( qst.getMot()) ) {


                        points.set(points.get()+20);
                        deplacement.set(4);
                        try {
                            Main.jeu.getPartieActuelle().setPosPoints(deplacement);
                        } catch (Partie.caseSautException e) {
                            e.printStackTrace();
                        }
                        Main.jeu.getPartieActuelle().majAvatar(deplacement);
                        message="Correcte!";
                        Main.jeu.getPartieActuelle().setInstruction(message);
                    }
                    else {


                        points.set(points.get()-10);
                        try {
                            Main.jeu.getPartieActuelle().setPosPoints(deplacement);
                        } catch (Partie.caseSautException e) {
                            e.printStackTrace();
                        }
                    }
                    message="Faux! Bonne réponse: " + qst.getMot();
                    Main.jeu.getPartieActuelle().setInstruction(message);
                    Main.scene.getRoot().setDisable(false);
                    popup.hide();
                }
            }
        });
        setKeyPressedTextField(motNouv);
        vbox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == event.getCode().ENTER) {
                    event.consume();
                    String motRecup = "";
                    for (int i = 0; i < taille; i++) {
                        motRecup = motRecup + motNouv[i].getText();
                    }
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

                        message="Faux! La bonne réponse: " + qst.getMot();
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

        for (i = 0; i < taille; i++) {
            motContainer.getChildren().add(motNouv[i]);
            motNouv[i].setAlignment(Pos.CENTER);
            motNouv[i].setFont(new Font(15));
        }
        motContainer.setSpacing(10);
        vbox.getChildren().add(definition);
        motContainer.setAlignment(Pos.CENTER);
        vbox.getChildren().add(motContainer);
        vbox.setStyle("-fx-background-radius:1000;" +
                "      -fx-border-color: 'white';" +
                "      -fx-border-thickness: 3;" +
                "      -fx-text-fill:'white';" +
                "      -fx-background-color:'transparent';" +
                "      -fx-background-opacity:0.5;");
        popup.getContent().add(vbox);

        popup.setHideOnEscape(false);

        popup.show(Main.scene.getWindow());


    }

}
