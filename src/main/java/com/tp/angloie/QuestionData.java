package com.tp.angloie;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class QuestionData {
    String mot  ;
    String def  ;
    Image img  ;



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
