package com.tp.angloie;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.media.*;

public class De extends ImageView {
    public static  String path ="C:\\Users\\raouf\\IdeaProjects\\Angloie\\src\\main\\resources\\com\\tp\\angloie\\DiceImages" ;
    private int res = 0;
    public Media  sound   = new Media(new File(path + "\\dice.mp3").toURI().toString());
    public static Image[] faces;

    static {
        try {
            faces = new Image[]{
                    new Image(new FileInputStream(path+"\\d1.png")),
                    new Image(new FileInputStream(path+"\\d2.png")),
                    new Image(new FileInputStream(path+"\\d3.png")),
                    new Image(new FileInputStream(path+"\\d4.png")),
                    new Image(new FileInputStream(path+"\\d5.png")),
                    new Image(new FileInputStream(path+"\\d6.png")),
            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public De() throws FileNotFoundException {
        super();
        randomizeImg();
    }


    public int getRes(){return res;}
        public void playSound(){
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();

        }
      public void randomizeImg(){
          Random rand = new Random();
          res = rand.nextInt(0,6) ;
          this.setImage(faces[res]);


      }





}
