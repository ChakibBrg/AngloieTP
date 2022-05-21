package com.tp.angloie;

import com.tp.angloie.Utilis ;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public static Jeu jeu  ;
    public static Group root ;
    public static Scene scene ;
    public static Stage stage ;

    @Override
    public void start(Stage stage) throws IOException {

        Main.stage =stage;

        jeu =(Jeu) Utilis.readObjectFromFile("abs.ser");

        if ( jeu == null )  jeu  =new Jeu(new HashMap<String,Joueur>(),null,null);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newGamePage.fxml"));

        root = new Group();
        scene = new Scene(root);

        stage.setTitle("ANGl'Oie");
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
        //// pour sauvegarder le contexte /////
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (Main.jeu != null) Utilis.writeObjectTofile("abs.ser" , Main.jeu);
                Platform.exit();
            }
        });
        root.getChildren().add(fxmlLoader.load()); //Pour afficher le menu
    }
    public static void main(String[] args) {
        launch();
    }
}