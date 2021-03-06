package com.tp.angloie;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

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
        imageView.setStyle("-fx-border-color: 'green'");      //Coulour verte => Correcte
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent mouseEvent) {
                if (questionData.getMot() == qst.getMot()) {
                    //message = "+10 pts";



                    imageView.setStyle("-fx-border-color: 'green'");      //Coulour verte => Correcte
                    points.set(points.get()+10);
                    deplacement.set(3);
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


                    try {
                        Main.jeu.getPartieActuelle().setPosPoints(deplacement);
                    } catch (Partie.caseSautException e) {
                        e.printStackTrace();
                    }
                    message="Faux!";
                    Main.jeu.getPartieActuelle().setInstruction(message);
                    imageView.setStyle("-fx-border-color: red");        //Couleur rouge => Fausse
                }
                //Ajouter une transition pour plustard avant le hide
                popup.hide();
                Main.scene.getRoot().setDisable(false);


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
        //Mettre tout ici permet de g??n??rer ?? chaque action d'autres questions ce qui est pratique
        deplacement.set(0);
        message="Veuillez choisir la bonne image !";
        Main.jeu.getPartieActuelle().setInstruction(message);

        Random randGen = new Random();
        int index = randGen.nextInt(0, questionData.size());
        this.qst = questionData.get(index);
        Main.scene.getRoot().setDisable(true);


        ArrayList<QuestionData> questionsChoisies = new ArrayList<QuestionData>();


        ////////////////////////////
        ImageView[] imageViews = new ImageView[4];                  //Ou Arraylist c'est meilleur
        Popup popup = new Popup();
        GridPane grid = new GridPane();
        int i = 0;
        QuestionData questionTrouvee;
        while (i < 3) {
            //Prendre au hasard trois autres questions
            index = randGen.nextInt(0, questionData.size());
            //////////////////////////////////
            questionTrouvee = questionData.get(index);
            if (questionTrouvee.getMot() != qst.getMot() && !questionsChoisies.contains(questionTrouvee)) {
                questionsChoisies.add(questionTrouvee);
                imageViews[i] = new ImageView(questionsChoisies.get(i).getImg());
                imageViews[i].setFitHeight(150);
                imageViews[i].setFitWidth(150);
                


                
                imageViews[i].setStyle("-fx-border-width: 5px;" +
                        "-fx-border-color: 'green'");
                i++;
            }
        }
        ////////////////////////////////
        questionsChoisies.add(qst);
        imageViews[3] = new ImageView(qst.getImg());
        imageViews[3].setFitHeight(150);
        imageViews[3].setFitWidth(150);
        imageViews[3].setStyle("-fx-border-width: 5px");
        for (i = 0; i < 4; i++) {
            //Affecter les ??v??nements pour chaque image view
            ////////////////////////////////////////////
            clickImageEvent(popup, imageViews[i], questionsChoisies.get(i), points, deplacement);
        }
        index = randGen.nextInt(0, 4);
        //Afin de placer la bonne image dans un emplacement al??atoire
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
        grid.setHgap(20);
        grid.setVgap(20);
        //grid.setPadding(new Insets(10,10,10,10));

        VBox question = new VBox();
        Text label = new Text(qst.getMot());
        VBox.setMargin(label,new Insets(10));

        label.setFill(Color.WHITE);
        label.setFont(Font.font("Verdana", 30));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-text-align: 'center';" +
                "-fx-padding: 1em");
        popup.setHeight(50);
        popup.setWidth(50);
        question.getChildren().add(label);
        question.getChildren().add(grid);
        question.setPadding(new Insets(15));
        question.setStyle("-fx-background-radius:1000;" +
                "      -fx-border-color: 'white';" +
                "      -fx-border-thickness: 3;" +
                "      -fx-text-fill:'white';" +
                "      -fx-background-color:'transparent';" +
                "      -fx-background-opacity:0.5;");
        popup.getContent().add(question);

        popup.setAnchorX(410);
        popup.setAnchorY(210);

        popup.show(Main.scene.getWindow());


    }

}
