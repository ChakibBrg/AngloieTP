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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.ActionGroup;

import java.io.IOException;
import java.util.ArrayList;

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
        if (Main.jeu != null) Main.jeu.setPartieActuelle(new Partie(0,0,new Plateau()));
    }

    @FXML
    protected void loadGameClick(ActionEvent e){
        /// Creating the popup
        ListView<String> savedGames  = new ListView<>();
        savedGames.setPrefHeight(100);
        VBox  list = new VBox();
        list.getChildren().add(savedGames);


        PopOver pop = new PopOver();
        pop.setTitle("Sélectionner une partie");
        pop.setHeaderAlwaysVisible(true);
        pop.setDetachable(false);
        pop.setArrowSize(0);
        pop.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        pop.setContentNode(list);
        pop.setAutoHide(false);
        pop.show(Main.scene.getWindow());
        pop.centerOnScreen();
        ////
        savedGames.getItems().setAll(Main.jeu.getJoueurActuel().getParties_sauvegardees().keySet());

        Button load = new Button();
        load.setText("Charger");
        load.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                String selected = savedGames.getSelectionModel().getSelectedItem();
                if(!selected.isBlank()) {
                    Main.jeu.setPartieActuelle(Main.jeu.getJoueurActuel().getParties_sauvegardees().get(selected));
                    Main.jeu.getPartieActuelle().getPlateau().createFormRandValues(null);
                    pop.hide();


                Main.stage.setWidth(1000);
                Main.stage.setHeight(1000);

                    Main.root.getChildren().clear();
                    Main.root.getChildren().add(Main.jeu.getPartieActuelle().getPlateau());

                }
            }
        });
        load.setAlignment(Pos.CENTER);
        list.getChildren().add(load);

        ////
        pop.show(Main.scene.getWindow());
    }@FXML
    protected void quitClick(ActionEvent e){
        if (Main.jeu != null) Utilis.writeObjectTofile("abs.ser" , Main.jeu);
        Platform.exit();
    }
}
