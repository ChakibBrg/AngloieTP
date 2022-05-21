package com.tp.angloie;

import com.tp.angloie.Utilis ;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public static Jeu jeu  ;
    public static Group root ;
    public static Scene scene ;
    public static Stage stage ;




    @Override
    public void start(Stage stage) throws IOException {
        //jeu = (Jeu)Utilis.readObjectFromFile("abs.ser");
            Main.stage =stage;
       /* HashMap<String,Joueur> joueurs   = new HashMap<>();

        Joueur p1  = new Joueur("raouf",15,null,false,null);
        Joueur p2  = new Joueur("Chakib",15,null,false,null);
        Joueur p3  = new Joueur("Akram",15,null,false,null);
        Joueur p4  = new Joueur("Wassim",15,null,false,null);
        joueurs.put(p1.getNom(),p1);
        joueurs.put(p2.getNom(),p2);
        joueurs.put(p3.getNom(),p3);
        joueurs.put(p4.getNom(),p4);
        jeu = new Jeu(joueurs ,null , null);*/

        jeu =(Jeu) Utilis.readObjectFromFile("abs.ser");
        if ( jeu == null )  jeu  =new Jeu(new HashMap<String,Joueur>(),null,null);



       // FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newGamePage.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePage.fxml"));
        root = new Group();
        scene = new Scene(root);

        //root.getChildren().add(jeu.getPartieActuelle().getPlateau()); //Pour afficher le Plateau
        stage.setTitle("ANGl'Oie");

        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);

        root.getChildren().add(fxmlLoader.load()); //Pour afficher le menu



    }
    public static void main(String[] args) {
        launch();
    }
}