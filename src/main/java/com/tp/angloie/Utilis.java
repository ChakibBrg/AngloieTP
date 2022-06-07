package com.tp.angloie;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public  class Utilis {
    public static  void writeObjectTofile(String path , Object obj){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
            System.out.println("serialized");

        } catch(IOException ee){
            ee.printStackTrace();

        }
    }

    public static void pageTrasition(Parent node){

        FadeTransition fd1 =  new FadeTransition(new Duration(1000),Main.scene.getRoot());
        fd1.setFromValue(1);
        fd1.setToValue(0);

        fd1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FadeTransition fd2 =  new FadeTransition(new Duration(1000),node);
                fd2.setFromValue(0);
                fd2.setToValue(1);

                fd2.play();
                Main.scene.setRoot(node);

            }
        });
        fd1.play();








    }


    public static Object readObjectFromFile(String path){
        Object obj   = null ;
        FileInputStream fo  = null ;
        ObjectInput os = null ;
        try {
             fo  = new FileInputStream(path);
             os = new ObjectInputStream(fo);
            obj= os.readObject();
            os.close();
            fo.close();
            System.out.println("Deserialized");

        } catch (IOException | ClassNotFoundException ex) {
            return new Jeu(new TreeMap<>(),null,null);
        }

        return obj ;

    }

}
