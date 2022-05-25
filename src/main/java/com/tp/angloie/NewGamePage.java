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
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.ActionGroup;

import java.io.IOException;
import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.HashMap;

public class NewGamePage {


    ///// Start Game page attributs
   @FXML  public Button newGameBtn ;
   @FXML  public Button loadGameBtn ;
   @FXML  public GridPane grid ;
   @FXML  public Hyperlink Player ;
    public PopOver popup = new PopOver();
    VBox register;

    UserNamePopUp registerCtrl;
    FXMLLoader fxmlLoader;
    @FXML
    protected void initialize() throws IOException {
        loadGameBtn.setDisable(true);
        showRegisterPop(true);


    }


    public void showSavedGamesPop(boolean first) throws IOException {

        fxmlLoader = new FXMLLoader(Main.class.getResource("UserNamePopUp.fxml"));
        register = fxmlLoader.load() ;
        registerCtrl = fxmlLoader.getController();
        registerCtrl.newGamePageCtrl= this;
        popup.setTitle("Sélectionner");
        popup.setHeaderAlwaysVisible(true);
        popup.setDetachable(false);
        popup.setArrowSize(0);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        popup.setContentNode(register);
        popup.setCloseButtonEnabled(!first);
        popup.setAutoHide(false);
        grid.setDisable(true);
        popup.show(Main.scene.getWindow());
        popup.centerOnScreen();
    }

    public void showRegisterPop(boolean first) throws IOException {

        fxmlLoader = new FXMLLoader(Main.class.getResource("UserNamePopUp.fxml"));
        register = fxmlLoader.load() ;
        registerCtrl = fxmlLoader.getController();
        registerCtrl.newGamePageCtrl= this;
        popup.setTitle("Sélectionner");
        popup.setHeaderAlwaysVisible(true);
        popup.setDetachable(false);
        popup.setArrowSize(0);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        popup.setContentNode(register);
        popup.setCloseButtonEnabled(!first);
        popup.setAutoHide(false);
        grid.setDisable(true);
        popup.show(Main.scene.getWindow());
        popup.centerOnScreen();
    }
//////// Start Game page Events ///////////////////////////////////////////////////////////////////////////


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


                    Main.stage.setWidth(1300);
                    Main.stage.setWidth(900);

                    Main.root.getChildren().clear();
                    try {
                        Main.root.getChildren().add(fxmlLoader.load());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Main.stage.centerOnScreen();
        }







    @FXML
    protected void loadGameClick(ActionEvent e) throws IOException {
        ListView<String> savedGames = new ListView<>();
        PopOver savedPop = new PopOver();
        StackPane stack = new StackPane();





        ////

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

                    Main.root.getChildren().clear();
                    try {
                        Main.root.getChildren().add(fxmlLoader.load());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Main.stage.centerOnScreen();
                }
            }
        });

        stack.getChildren().add(savedGames);
        stack.getChildren().add(load);
        StackPane.setAlignment(load,Pos.BOTTOM_LEFT);

        savedGames.getItems().setAll(Main.jeu.getJoueurActuel().getParties_sauvegardees().keySet());
        savedPop.setContentNode(stack);
        savedPop.setTitle("Charger une partie");
        savedPop.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        savedPop.setHeaderAlwaysVisible(true);
        savedPop.setDetachable(false);
        savedPop.setArrowSize(0);
        savedPop.show(Main.scene.getWindow());
        savedPop.centerOnScreen();
        savedPop.setMaxHeight(500);



    }


    @FXML
    protected void quitClick(ActionEvent e){


        HashMap<String,Partie> parts = new HashMap<>();
        Partie test  = new Partie();
        test.setTitle("saved1");
        parts.put("saved1",test);

        test  = new Partie();
        test.setTitle("saved2");
        parts.put("saved2",test);

        Joueur p1 = new Joueur("Chakib",511,parts,false,null);

        Main.jeu.getJoueurs().put(p1.getNom(), p1);

        if (Main.jeu != null) {
            try {
                Utilis.writeObjectTofile("abs.ser", Main.jeu);
            } catch(Exception ex2){
                System.out.println(ex2.getMessage());
            }
        }
        Platform.exit();
    }
}
