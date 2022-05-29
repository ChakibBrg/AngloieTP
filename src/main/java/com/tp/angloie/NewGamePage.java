package com.tp.angloie;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import com.tp.angloie.Utilis;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.ActionGroup;

import java.io.IOException;
import java.io.NotSerializableException;
import java.util.ArrayList;
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

    }

    public void start() throws IOException {
        loadGameBtn.setDisable(true);
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
            popup.getContent().add(register);
            popup.show(Main.scene.getWindow());
    }
//////// Les evenements de la page d'acceuil page Events ///////////////////////////////////////////////////////////////////////////


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
                        Main.scene.setRoot(fxmlLoader.load());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Main.stage.centerOnScreen();
        }







    @FXML
    protected void loadGameClick(ActionEvent e) throws IOException {
        ListView<String> savedGames = new ListView<>();
        Popup savedPop = new Popup();
        StackPane stack = new StackPane();



        Button load = new Button();
        load.setText("Charger");
        load.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                String selected = savedGames.getSelectionModel().getSelectedItem();
                if(!selected.isBlank()) {
                    Main.jeu.setPartieActuelle(Main.jeu.getJoueurActuel().getParties_sauvegardees().get(selected));
                    Main.jeu.getPartieActuelle().getPlateau().createFormSavedValues();
                    savedPop.hide();

                    Main.stage.setMaximized(true);

                    fxmlLoader = new FXMLLoader(Main.class.getResource("Partie.fxml"));
                    fxmlLoader.setController(Main.jeu.getPartieActuelle());

                    try {
                        Main.scene.setRoot(fxmlLoader.load());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Main.stage.centerOnScreen();
                }
            }
        });

        stack.getChildren().add(savedGames);
        stack.getChildren().add(load);
        StackPane.setAlignment(load,Pos.BOTTOM_CENTER);

        savedGames.getItems().setAll(Main.jeu.getJoueurActuel().getParties_sauvegardees().keySet());


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

        Joueur p1 = new Joueur("Chakib",511,parts,false,null);

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
}
