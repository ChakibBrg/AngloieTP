package com.tp.angloie;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Plateau extends GridPane implements Serializable {
     transient private ArrayList<Case> cases ;
     private ArrayList<Integer>  randomValues  ;
     private ArrayList<Integer>  indexes  ;
    public int i = 0 , j = 0 ;
    int colLowerBond = 0 ;
    int rowLowerBond= 0 ;
    int colHigherBond = 15 +1;
    int rowHigherBond= 13 +1;
    transient Image c ;



    transient  File img = null;
    transient Image  med = null;
    transient ImageView p ;
    /////getters and setters
    public ArrayList<Case> getCases() {
        return cases;
    }

    ////constructeur
    public Plateau ()  {

        c = new Image(getClass().getResourceAsStream("among/g01.png"));



        cases = new ArrayList<>();
        setWidth(600);
        setHeight(600);
        randomValues  = new ArrayList<>();
        indexes  = new ArrayList<>();
        Random randGen =  new Random();
        while ( randomValues.size() != 25 ){
            int tmp = randGen.nextInt(1,99);
            if (! randomValues.contains(tmp) ){
                randomValues.add(tmp);
            }
        }
        QuestionData qst  = null;
        ArrayList<Case> tempCases = new ArrayList<>();
        for ( int i=0 ; i<5 ;i++){
            tempCases.add(new CaseDefinition(qst));
            tempCases.add(new CaseImage(qst));
            tempCases.add(new Bonus());
            tempCases.add(new Malus());
            tempCases.add(new Saut());
        }
        int index = 0  ;
        int caseCount = 0 ;
        Case tmp= null ;
        tmp=new Depart();
        this.add(tmp,0,0);
        cases.add(tmp);
        caseCount++;
        incrementIndexes();
        while ( caseCount != 98 ){

                    if (randomValues.contains(caseCount)){
                        if (tempCases.size()!= 0) {
                            index = randGen.nextInt(0, tempCases.size());
                            indexes.add(index);
                            tmp = tempCases.get(index);
                            tempCases.remove(index);
                        }
                    }
                    else{
                        tmp = new Parcours();
                    }
                    this.add(tmp,j,i);
                    GridPane.setHalignment(tmp, HPos.CENTER);
                    GridPane.setValignment(tmp, VPos.CENTER);
                    cases.add(tmp);
                    incrementIndexes();
                    caseCount ++ ;
                }
                tmp=new Fin();
                this.add(tmp,j,i);
                GridPane.setHalignment(tmp, HPos.CENTER);
                GridPane.setValignment(tmp, VPos.CENTER);

                p= new ImageView(med);
                p.setFitHeight(50);
                p.setFitWidth(35);
                cases.add(tmp);
                p.setImage(c);
                cases.get(0).getChildren().add(p);



    }

    public void createFormSavedValues (){ // creer un plateau selon un tableau randomValues pour les 25 cases

        c = new Image(getClass().getResourceAsStream("among/g01.png"));

        cases = new ArrayList<>();
        i=j=0;
            colLowerBond = 0 ;
            rowLowerBond= 0 ;
         colHigherBond = 15 +1;
         rowHigherBond= 13 +1;
        setWidth(600);
        setHeight(600);

        QuestionData qst  = null;
        ArrayList<Case> tempCases = new ArrayList<>();
        for ( int i=0 ; i<5 ;i++){
            tempCases.add(new CaseDefinition(qst));
            tempCases.add(new CaseImage(qst));
            tempCases.add(new Bonus());
            tempCases.add(new Malus());
            tempCases.add(new Saut());
        }

        int caseCount = 0 ;
        int index =-1 ;
        int k =0 ;
        Case tmp= null ;
        tmp=new Depart();
        this.add(tmp,0,0);
        cases.add(tmp);
        caseCount++;
        incrementIndexes();
        while ( caseCount != 98 ){
            if (randomValues.contains(caseCount)){
                if (tempCases.size()!= 0) {
                    index = indexes.get(k);
                    k++;
                    tmp = tempCases.get(index);
                    tempCases.remove(index);

                }
            }
            else{
                tmp = new Parcours();
            }
            this.add(tmp,j,i);
            GridPane.setHalignment(tmp, HPos.CENTER);
            GridPane.setValignment(tmp, VPos.CENTER);
            cases.add(tmp);
            incrementIndexes();
            caseCount ++ ;
        }
        tmp=new Fin();
        this.add(tmp,j,i);
        GridPane.setHalignment(tmp, HPos.CENTER);
        GridPane.setValignment(tmp, VPos.CENTER);
        cases.add(tmp);

        p= new ImageView(med);
        p.setFitHeight(50);
        p.setFitWidth(35);
        cases.add(tmp);
        p.setImage(c);
        cases.get(Main.jeu.getPartieActuelle().getPosActuelle()).getChildren().add(p);

    }


    //////////////////  Animation avatar //////////////////////
    class AnimationAvatar extends AnimationTimer {
        private final int inPos  ; // la case concernée
        private final int outPos  ; // la case concernée
        private int count=1 ;


        long previous =0;

            public AnimationAvatar(int inPos,int outPos ){
                 img = null;
                 med = null;

                this.inPos = inPos ;
                this.outPos = outPos ;


                StackPane.setMargin(p,new Insets(0,0,7,0));


            }

        @Override
        public  void handle  ( long current) {
            if(count == 22 ){
                cases.get(inPos).getChildren().remove(p);
                cases.get(outPos).getChildren().add(p);
                count++;
                return;
            }
            if (count > 35) {
                stop() ;
            }
            if (  current - previous > 60) {
                if (count >= 10)p.setImage( new Image(getClass().getResourceAsStream("among/g"+count+".png")));
                else p.setImage( new Image(getClass().getResourceAsStream("among/g0"+count+".png")));
                count++;
                previous = current;
            }
            }
        }

    public void deplacer(int current  , int dest){


        new AnimationAvatar(current,dest).start();


    }



/////////////////////////////////////////////////
/////////////////////////////////////////////:

   public void  incrementIndexes( ){

    if ( j == colLowerBond && i == rowLowerBond ){
        colHigherBond -=2; ;
        j++;
        return ;

    }if ( j == colLowerBond && i == rowHigherBond ){
        rowLowerBond +=2 ;
        i--;
        return;

    }if ( j == colHigherBond && i == rowLowerBond ){
        rowHigherBond -=2 ;
        i++;
        return;

    }if ( j == colHigherBond && i == rowHigherBond ){
        colLowerBond+=2;
        j-- ;
       return;

    }

       if ( i == rowLowerBond ){
           j++ ;
           return;
       }
        if ( i ==rowHigherBond) {
           j-- ;
           return;
       }
        if (j == colLowerBond){
           i-- ;
           return;
       }
        if ( j == colHigherBond){
           i++;

       }
    }
}
