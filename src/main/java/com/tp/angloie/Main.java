package com.tp.angloie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Jeu jeu  ;
    public static Group root ;
    public static Scene scene ;

    public Main( ){
        //loading the list of the players from a file
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newGamePage.fxml"));
        jeu = new Jeu(null,new Joueur("raouf",15,null,false,null), new Partie(0,0,new Plateau()));
        root = new Group();
        scene = new Scene(root);

        //root.getChildren().add(fxmlLoader.load()); //Pour afficher le menu
        root.getChildren().add(jeu.getPartieActuelle().getPlateau()); //Pour afficher le Plateau




        stage.setTitle("Angloie");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}