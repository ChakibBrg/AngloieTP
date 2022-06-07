package com.tp.angloie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;

public class UserNamePopUp {
    public NewGamePage newGamePageCtrl ;

    @FXML  private TextField userNametxt ;
    @FXML private ComboBox<String> userNameCombo;
    @FXML
    protected void initialize() throws IOException {
        if (Main.jeu  != null ) {
            if ( Main.jeu.getJoueurs() != null) userNameCombo.getItems().addAll(Main.jeu.getJoueurs().keySet());
        }
    }


    @FXML
    protected void confirmClick(ActionEvent e){
            if ( !userNametxt.getText().isBlank()   ){
                if (Main.jeu.getJoueurs().containsKey(userNametxt.getText())){
                    Main.jeu.setJoueurActuel(Main.jeu.getJoueurs().get(userNametxt.getText()));
                }
                else{
                    Joueur nouvJoueur  = new Joueur(userNametxt.getText(),0,new HashMap<>());
                    Main.jeu.getJoueurs().put(userNametxt.getText(),nouvJoueur );
                    Main.jeu.setJoueurActuel(nouvJoueur);
                    if (Main.jeu  != null ) userNameCombo.getItems().addAll(Main.jeu.getJoueurs().keySet());

                }
                newGamePageCtrl.popup.hide();
                newGamePageCtrl.grid.setDisable(false);
                newGamePageCtrl.Player.setText(userNametxt.getText());
                newGamePageCtrl.loadGameBtn.setDisable(Main.jeu.getJoueurActuel().getPartiesSauvegardees().size() == 0);
            }else{
                if ( userNameCombo.getValue()!= null){

                    Main.jeu.setJoueurActuel(Main.jeu.getJoueurs().get(userNameCombo.getValue()));
                    newGamePageCtrl.popup.hide();
                    newGamePageCtrl.grid.setDisable(false);
                    newGamePageCtrl.Player.setText(userNameCombo.getValue());
                    newGamePageCtrl.loadGameBtn.setDisable(Main.jeu.getJoueurActuel().getPartiesSauvegardees().size() == 0);

                }

            }

        }
}
