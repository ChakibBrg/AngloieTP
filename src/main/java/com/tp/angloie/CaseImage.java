package com.tp.angloie;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CaseImage extends Question {
    private ArrayList<QuestionData> questionData;
    public CaseImage() {
        super();
        c.setFill(Color.PINK);

    }public CaseImage(ArrayList<QuestionData> questionData) {
        //super(qst);
        this.questionData = questionData;
        Random randGen =  new Random();
        int index = randGen.nextInt(0, questionData.size());
        this.qst = questionData.get(index);
        c.setFill(Color.PINK);

    }

    public void clickImageEvent(Popup popup, ImageView imageView, QuestionData questionData, AtomicInteger points, AtomicInteger deplacement) {
        //ImageView imageView = new ImageView(questionData.getImg());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent mouseEvent) {
                if (questionData.getMot() == qst.getMot()) {
                    //message = "+10 pts";
                    imageView.setStyle("-fx-border-color: green");      //Coulour verte => Correcte
                    points.set(points.get()+10);
                    deplacement.set(3);
                    try {
                        Main.jeu.getPartieActuelle().setPosPoints(deplacement);
                    } catch (Partie.caseSautException e) {
                        e.printStackTrace();
                    }
                    Main.jeu.getPartieActuelle().majAvatar(deplacement);
                }
                else {
                    imageView.setStyle("-fx-border-color: red");        //Couleur rouge => Fausse
                }
                //Ajouter une transition pour plustard avant le hide
                popup.hide();
                Main.scene.getRoot().setDisable(false);

                /*if (!popup.isShowing()) {
                    popup.show(Main.scene.getWindow());
                }
                else {
                    FadeTransition fd = new FadeTransition(new Duration(1500),popup.getContent().get(0).getParent());
                    fd.setFromValue(1);
                    fd.setToValue(0);
                    fd.play();
                    popup.hide();
                }*/
            }
        });

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setOpacity(0.5);
            }
        });

        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setOpacity(1);
            }
        });
    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {
        //Mettre tout ici permet de générer à chaque action d'autres questions ce qui est pratique
        deplacement.set(0);
        message="Veuillez choisir la bonne image !";
        Main.jeu.getPartieActuelle().setInstruction(message);

        Random randGen = new Random();
        int index = randGen.nextInt(0, questionData.size());
        this.qst = questionData.get(index);
        Main.scene.getRoot().setDisable(true);
        QuestionData[] questionsChoisies = new QuestionData[4];     //Ou utiliser un ensemble
        ImageView[] imageViews = new ImageView[4];                  //Ou Arraylist c'est meilleur
        Popup popup = new Popup();
        GridPane grid = new GridPane();
        int i = 0;
        while (i < 3) {
            //Prendre au hasard trois autres questions
            index = randGen.nextInt(0, questionData.size());
            if (questionData.get(index).getMot() != qst.getMot()) {
                questionsChoisies[i] = questionData.get(index);
                imageViews[i] = new ImageView(questionsChoisies[i].getImg());
                imageViews[i].setFitHeight(150);
                imageViews[i].setFitWidth(150);
                imageViews[i].setStyle("-fx-border-width: 5px");
                i++;
            }
        }
        questionsChoisies[3] = qst;
        imageViews[3] = new ImageView(qst.getImg());
        imageViews[3].setFitHeight(150);
        imageViews[3].setFitWidth(150);
        imageViews[3].setStyle("-fx-border-width: 5px");
        for (i = 0; i < 4; i++) {
            //Affecter les événements pour chaque image view
            clickImageEvent(popup, imageViews[i], questionsChoisies[i], points, deplacement);
        }
        index = randGen.nextInt(0, 4);
        //Afin de placer la bonne image dans un emplacement aléatoire
        switch (index){
            case 0:
                grid.add(imageViews[3], 0, 0);
                grid.add(imageViews[0], 0, 1);
                grid.add(imageViews[1], 1, 0);
                grid.add(imageViews[2], 1, 1);
                break;
            case 1:
                grid.add(imageViews[3], 1, 0);
                grid.add(imageViews[0], 0, 0);
                grid.add(imageViews[1], 0, 1);
                grid.add(imageViews[2], 1, 1);
                break;
            case 2:
                grid.add(imageViews[3], 0, 1);
                grid.add(imageViews[0], 0, 0);
                grid.add(imageViews[1], 1, 0);
                grid.add(imageViews[2], 1, 1);
                break;
            case 3:
                grid.add(imageViews[3], 1, 1);
                grid.add(imageViews[0], 0, 0);
                grid.add(imageViews[1], 1, 0);
                grid.add(imageViews[2], 0, 1);
                break;
            default:
                System.out.println("Erreur");
        }
        grid.setHgap(10);
        grid.setVgap(10);
        //grid.setPadding(new Insets(10,10,10,10));

        VBox question = new VBox();
        Text label = new Text(qst.getMot());
        label.setFill(Color.WHITE);
        label.setFont(Font.font("Verdana", 30));
        popup.setHeight(50);
        popup.setWidth(50);
        question.getChildren().add(label);
        question.getChildren().add(grid);
        popup.getContent().add(question);
        popup.setAnchorX(400);
        popup.setAnchorY(600);
        popup.show(Main.scene.getWindow());

    }

    @Override
    void Verification(Joueur player) {

    }
}
