package com.tp.angloie;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Plateau extends GridPane implements Serializable {
    private ArrayList<Case> cases = new ArrayList<>();
    private int curseur = 0 ;

    public int i = 0 , j = 0 ;
    int colLowerBond = 0 ;
    int rowLowerBond= 0 ;
    int colHigherBond = 13 +1;
    int rowHigherBond= 14 +1;


    public Plateau (){

        setWidth(600);
        setHeight(600);
        ArrayList<Integer> randomValues  = new ArrayList<>();




        Random randGen =  new Random();

        while ( randomValues.size() != 25 ){
            int tmp = randGen.nextInt(1,99);
            if (! randomValues.contains(tmp) ){
                randomValues.add(tmp);
            }
        }


        int caseCount = 0 ;
        int k = 0  ;

       this.add(new Depart(),0,0);
        int image =0 ;
        int def  =0 ;
        int saut = 0 ;
        int bonus =0;
        int malus =0 ;



        incrementIndexes();
        Question qst  = null;
        Case tmp= null ;
                while ( caseCount != 100 ){

                    if (randomValues.contains(caseCount)){
                        if ( image != 5){
                            tmp = new CaseImage(qst);
                            image ++ ;
                        }
                        else if( def != 5) {
                            tmp = new CaseDefinition(qst);
                            def ++;

                        }else if( bonus != 5) {
                            tmp = new Bonus();
                            bonus++ ;

                        }else if( malus != 5) {
                            tmp = new Malus();
                            malus ++ ;

                        }else if( saut != 5) {
                            tmp = new Saut();
                            saut ++ ;
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


    }


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




       if ( i == rowLowerBond){
           j++ ;
       }
        if ( i ==rowHigherBond) {
           j-- ;
       }
        if (j == colLowerBond){
           i-- ;
       }
        if ( j == colHigherBond){
           i++;
       }
    }
}
