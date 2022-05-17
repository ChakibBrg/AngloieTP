package com.tp.angloie;

import javafx.scene.image.Image;

public class QuestionData {
    String mot  ;
    String def  ;
    String img  ;   // le tyrp String pour le chemin de l'image ou Image pour l'image elle meme
                    // ca dependra de notre conception xD


    public QuestionData(){

    }



    public String getDef(){
        return def  ;
    }

    public String getImg(){
        return img ;
    }
}
