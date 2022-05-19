package com.tp.angloie;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;

import java.io.*;
import java.util.ArrayList;

public class NewGamePage {
    @FXML
    protected void newGameClick(ActionEvent e){
        Main.jeu.setPartieActuelle(new Partie(0,0,new Plateau()));




        try {
            FileOutputStream fos = new FileOutputStream("abc.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Method for serialization of B's class object

            oos.writeObject(Main.jeu.getPartieActuelle().getPlateau());


            // closing streams
            oos.close();
            fos.close();
            Main.jeu.getPartieActuelle().setPlateau(null);

            System.out.println("serializzed");

        }catch(IOException ee){}


    }@FXML
    protected void loadGameClick(ActionEvent e){
        FileInputStream fo = null;
        try {
            fo = new FileInputStream("abc.ser");
            ObjectInput os = new ObjectInputStream(fo);
            // Method for serialization of B's class object
            Plateau  tmp  =(Plateau)os.readObject();
            Main.jeu.getPartieActuelle().setPlateau(tmp);
            Main.root.getChildren().clear();
            Main.root.getChildren().add(Main.jeu.getPartieActuelle().getPlateau());
            tmp.createFormRandValues(null);

            // closing streams
            os.close();
            fo.close();
            System.out.println("DEEserializzed");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


    }@FXML
    protected void quitClick(ActionEvent e){


        Platform.exit();
    }
}
