package com.tp.angloie;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class QuestionData {
    String mot  ;
    String def  ;
    Image img  ;   // le tyrp String pour le chemin de l'image ou Image pour l'image elle meme
                    // ca dependra de notre conception xD


    /*public QuestionData(String path) throws FileNotFoundException{
        try {
            File repertoire = new File(path);       //Récupérer le repertoire contenant l'image et la déf
            if (repertoire.isDirectory()) {
                File[] files = repertoire.listFiles();

            }
        }
        catch (FileNotFoundException e) {

        }
    }*/

    public QuestionData(String mot, String def, Image img) {
        this.mot = mot;
        this.def = def;
        this.img = img;
    }


    public String getMot() {
        return mot;
    }

    public String getDef(){
        return def  ;
    }

    public Image getImg(){
        return img ;
    }
}
