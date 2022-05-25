package com.tp.angloie;

import java.io.*;
import java.util.HashMap;

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
            ex.printStackTrace();

            return new Jeu(new HashMap<>(),null,null);
        }

        return obj ;

    }

}
