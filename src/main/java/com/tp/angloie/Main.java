package com.tp.angloie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Jeu jeu  ;

    public Main( ){
        //loading the list of the players for a file
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newGamePage.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Angloie");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}