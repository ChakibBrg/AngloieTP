package com.tp.angloie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public static Jeu jeu  ;
    public static Group root ;
    public static Scene scene ;
    public static Stage stage ;


    @Override
    public void start(Stage stage)  {

        Main.stage =stage;
        try {
            jeu = (Jeu) Utilis.readObjectFromFile("abs.ser");
            if ( jeu == null) jeu  = new Jeu(new HashMap<>(), null ,null);

            root = new Group();
            scene = new Scene(root);

            stage.setTitle("Our Game");
            stage.setScene(scene);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.show();
            stage.centerOnScreen();


            //// pour sauvegarder le contexte /////
       /* stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (Main.jeu != null) Utilis.writeObjectTofile("abs.ser" , Main.jeu);
                Platform.exit();
            }
        });*/


            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newGamePage.fxml"));
            root.getChildren().add(fxmlLoader.load()); //Pour afficher le menu
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println("OUpsss");

        }
    }
    public static void main(String[] args) {
        launch();
    }
}