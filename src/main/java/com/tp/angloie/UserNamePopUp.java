package com.tp.angloie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.util.HashMap;

public class UserNamePopUp {
    @FXML  private TextField userNametxt ;
    @FXML private ComboBox<String> userNameCombo;
    public NewGamePage newGamePageCtrl ;

    @FXML
    protected void initialize() throws IOException {
        if (Main.jeu  != null ) userNameCombo.getItems().addAll(Main.jeu.getJoueurs().keySet());


    }


        @FXML
    protected void confirmClick(ActionEvent e){
        if ( !userNametxt.getText().isBlank()   ){
            if (Main.jeu.getJoueurs().containsKey(userNametxt.getText())){
                Main.jeu.setJoueurActuel(Main.jeu.getJoueurs().get(userNametxt.getText()));
            }
            else{
                Main.jeu.getJoueurs().put(userNametxt.getText(), new Joueur(userNametxt.getText(),0,null,false,null));
            }
            newGamePageCtrl.popup.hide();
            newGamePageCtrl.grid.setDisable(false);
            newGamePageCtrl.Player.setText(userNametxt.getText());
            newGamePageCtrl.loadGameBtn.setDisable(Main.jeu.getJoueurActuel().getParties_sauvegardees() == null);
        }else{
            if ( userNameCombo.getValue()!= null){
                Main.jeu.setJoueurActuel(Main.jeu.getJoueurs().get(userNameCombo.getValue()));
                newGamePageCtrl.popup.hide();
                newGamePageCtrl.grid.setDisable(false);
                newGamePageCtrl.Player.setText(userNameCombo.getValue());
                newGamePageCtrl.loadGameBtn.setDisable(Main.jeu.getJoueurActuel().getParties_sauvegardees() == null);

            }
        }


        }
}
