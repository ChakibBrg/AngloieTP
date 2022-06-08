package com.tp.angloie;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Path;
import javafx.stage.Popup;

import java.io.IOException;
import java.util.HashMap;

public class NewGamePage {


    ///// Les attributs de la page d'acceuil
   @FXML  public Button newGameBtn ;
   @FXML  public Button loadGameBtn ;
   @FXML  public GridPane grid ;
   @FXML  public Hyperlink Player ;
   @FXML  public AnchorPane rootNewGamePage ;

    public Popup popup = new Popup();
    VBox register;
    UserNamePopUp registerCtrl;
    FXMLLoader fxmlLoader;

    @FXML
    protected void initialize() throws IOException {
        if( Main.jeu.getJoueurActuel() != null) {
            if (Main.jeu.getJoueurActuel().getPartiesSauvegardees().size() != 0) loadGameBtn.setDisable(false);
            else loadGameBtn.setDisable(true);
            Player.setText(Main.jeu.getJoueurActuel().getNom());
        }
    }

    public void start() throws IOException {
        showRegisterPop(true);
    }

    public void showRegisterPop(boolean first) throws IOException {



        Path path = new Path();
        path.getStyleClass().add("border"); //$NON-NLS-1$
        path.setManaged(false);


        fxmlLoader = new FXMLLoader(Main.class.getResource("UserNamePopUp.fxml"));
        register = fxmlLoader.load() ;
        registerCtrl = fxmlLoader.getController();
        registerCtrl.newGamePageCtrl= this;
        register.getStyleClass().add("vbox");
        register.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        grid.setDisable(true);

        popup = new Popup();
        popup.setHideOnEscape(false);
        popup.getContent().add(register);
        popup.show(Main.scene.getWindow());
    }

//////// Les evenements de la page d'acceuil///////////////////////////////////////////////////////////////////////////


    @FXML
    protected void userNameClick(ActionEvent e) throws IOException {
       showRegisterPop(false);
    }
    @FXML
    protected void newGameClick(ActionEvent e){
        Partie controller = new Partie() ;
        Main.jeu.setPartieActuelle(controller);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(Main.jeu.getPartieActuelle());
        fxmlLoader.setLocation(Main.class.getResource("Partie.fxml"));
                    try {
                        Utilis.pageTrasition(fxmlLoader.load());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
        }

    @FXML
    protected void loadGameClick(ActionEvent e) throws IOException {
        ListView<String> savedGames = new ListView<>();
        Main.scene.getRoot().setDisable(true);
        Popup savedPop = new Popup();
        StackPane stack = new StackPane();
        Button load = new Button();
        load.setText("Charger");
        load.setStyle(" -fx-background-radius:1000;" +
                "    -fx-text-align: center;" +
                "-fx-border-radius: 1000;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: 'black';" +
                "-fx-border-thickness: 3;" +
                "-fx-text-fill:'black';" +
                "-fx-background-color:'transparent';" );
        load.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.scene.getRoot().setDisable(false);

                String selected = savedGames.getSelectionModel().getSelectedItem();
                if(!selected.isBlank()) {
                    Main.jeu.setPartieActuelle(Main.jeu.getJoueurActuel().getPartiesSauvegardees().get(selected));
                    try {
                        Main.jeu.getPartieActuelle().getPlateau().createFormSavedValues();
                    }catch (IOException e ){
                        System.out.println(e.getMessage());
                    }
                    savedPop.hide();

                    Main.stage.setMaximized(true);

                    fxmlLoader = new FXMLLoader(Main.class.getResource("Partie.fxml"));
                    fxmlLoader.setController(Main.jeu.getPartieActuelle());

                    try {
                        Parent node = fxmlLoader.load();
                        Utilis.pageTrasition(node);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Button fermerBtn = new Button();
        fermerBtn.setText("Fermer");
        fermerBtn.setStyle(" -fx-background-radius:1000;" +
                "    -fx-text-align: center;" +
                "-fx-border-radius: 1000;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: 'black';" +
                "-fx-border-thickness: 3;" +
                "-fx-text-fill:'black';" +
                "-fx-background-color:'transparent';" );
        fermerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                savedPop.hide();
                Main.scene.getRoot().setDisable(false);
            }
        });



        savedGames.setPadding(new Insets(10,10,10,10));
        stack.getChildren().add(savedGames);
        stack.getChildren().add(load);
        stack.getChildren().add(fermerBtn);
        StackPane.setAlignment(load,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(fermerBtn,Pos.BOTTOM_LEFT);
        StackPane.setMargin(load,new Insets(10));
        StackPane.setMargin(fermerBtn,new Insets(10));
        savedGames.getItems().setAll(Main.jeu.getJoueurActuel().getPartiesSauvegardees().keySet());
        savedGames.setPrefHeight(300);

        savedPop.getContent().add(stack);
        savedPop.show(Main.scene.getWindow());



    }


    @FXML
    protected void quitClick(ActionEvent e){

        HashMap<String,Partie> parts = new HashMap<>();
        Partie test  = new Partie();
        test.setTitle("saved1");
        parts.put("saved1",test);

        test  = new Partie();
        test.setPosActurelle(3);
        test.setTitle("saved2");
        parts.put("saved2",test);

        Joueur p1 = new Joueur("Chakib",511,parts);

        Main.jeu.getJoueurs().put(p1.getNom(), p1);

        if (Main.jeu != null) {
            try {
                Utilis.writeObjectTofile("Context.ser", Main.jeu);
            } catch(Exception ex2){
                System.out.println(ex2.getMessage());
            }
        }
        Platform.exit();
    }




    public void classementClick(ActionEvent r){


        ListView<String> leaderboard = new ListView<>();
        Popup savedPop = new Popup();
        savedPop.setHideOnEscape(true);

        StackPane stack = new StackPane();
        Label title = new Label("Classement");

        title.getStyleClass().add("classement");
        title.getStylesheets().add(getClass().getResource("app.css").toExternalForm());



        stack.setStyle(" -fx-background-color:white;");
        Button load = new Button();
        load.setText("Fermer");
        load.setStyle(" -fx-background-radius:1000;" +
                "    -fx-text-align: center;" +
                "-fx-border-radius: 1000;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: 'black';" +
                "-fx-border-thickness: 3;" +
                "-fx-text-fill:'black';" +
                "-fx-background-color:'transparent';" );
        load.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                savedPop.hide();
            }
        });

        leaderboard.setPadding(new Insets(20,10,10,10));
        leaderboard.setEditable(false);

        stack.getChildren().add(leaderboard);
        stack.getChildren().add(load);
        stack.getChildren().add(title);



        StackPane.setAlignment(load,Pos.BOTTOM_CENTER);
        StackPane.setMargin(load,new Insets(15));

        StackPane.setAlignment(title,Pos.TOP_CENTER);
        StackPane.setMargin(title,new Insets(15));


        StackPane.setMargin(leaderboard,new Insets(70,60,60,60));

        leaderboard.getItems().setAll(Main.jeu.classerJoueurs());
        leaderboard.setPrefHeight(500);
        leaderboard.setPrefWidth(300);
        savedPop.getContent().add(stack);
        savedPop.show(Main.scene.getWindow());

    }
}
