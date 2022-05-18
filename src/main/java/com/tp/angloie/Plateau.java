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
    int colHigherBond = 15 +1;
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
        Question qst  = null;

        ArrayList<Case> tempCases = new ArrayList<>();
        for ( int i=0 ; i<5 ;i++){
            tempCases.add(new CaseDefinition(qst));
            tempCases.add(new CaseImage(qst));
            tempCases.add(new Bonus());
            tempCases.add(new Malus());
            tempCases.add(new Saut());
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
        Case tmp= null ;
                while ( caseCount != 100 ){

                    if (randomValues.contains(caseCount)){
                        if (tempCases.size()!= 0) {

                            tmp = tempCases.get(randGen.nextInt(0, tempCases.size()));
                            tempCases.remove(tmp);
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
                this.add(new Fin(),j,i);
                GridPane.setHalignment(tmp, HPos.CENTER);
                GridPane.setValignment(tmp, VPos.CENTER);

                cases.add(tmp);





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
