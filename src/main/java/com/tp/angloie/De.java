package com.tp.angloie;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.media.*;
import javafx.util.Duration;

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
        randomizeImg(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });



    }


    public int getRes(){return res;}
        public void playSound(){
           /* MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();*/

        }
      public void randomizeImg(EventHandler<ActionEvent> ev) {
          Random rand = new Random();
          RotateTransition rot =  new RotateTransition();
          rot.setAutoReverse(true);
          int sens = rand.nextInt() % 2 == 0 ? 1 : -1 ;
          rot.setByAngle(180 * sens );
          rot.setNode(this);
          rot.setDuration(new Duration(500));

          FadeTransition fadeIn = getFadeTransition(this, 0.0, 1.0, 500);
          PauseTransition stayOn = new PauseTransition(Duration.millis(500));
          FadeTransition fadeOut = getFadeTransition(this, 1.0, 0.0, 500);
        for ( int i = 0 ; i<5 ; i++) {
            fadeOut.play();
            rot.play();

            fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    res = rand.nextInt(0, 6);
                    setImage(faces[res]);
                    stayOn.play();
                    stayOn.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            fadeIn.play();
                            fadeIn.setOnFinished(ev);
                        }
                    });

                }
            });
        }

      }
    public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

        FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
        ft.setFromValue(fromValue);
        ft.setToValue(toValue);

        return ft;

    }






}
