package com.tp.angloie;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public static Jeu jeu  ;
    public static Scene scene ;
    public static Stage stage ;


    @Override
    public void start(Stage stage) throws IOException {

        Main.stage =stage;
        jeu = (Jeu) Utilis.readObjectFromFile("Context.ser");
        if ( jeu == null) jeu  = new Jeu(new HashMap<>(), null ,null);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newGamePage.fxml"));

         fxmlLoader.load();
        NewGamePage np  =fxmlLoader.getController();
        scene = new Scene(np.rootNewGamePage);

        Main.scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());


        stage.setMaximized(true);
        stage.setTitle("Our Game");
        stage.setScene(scene);

        stage.show();
        np.start();

        stage.centerOnScreen();


    }
    public static void main(String[] args) {
        launch();
    }
}