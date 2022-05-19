package com.tp.angloie;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Plateau extends GridPane implements Serializable {
     transient private ArrayList<Case> cases ;
     private ArrayList<Integer>  randomValues  ;
     private ArrayList<Integer>  indexes  ;

    private int curseur = 0 ;

    public int i = 0 , j = 0 ;
    int colLowerBond = 0 ;
    int rowLowerBond= 0 ;
    int colHigherBond = 15 +1;
    int rowHigherBond= 14 +1;

    public ArrayList<Case> getCases() {
        return cases;
    }

    public Plateau (){
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
        Question qst  = null;
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
                cases.add(tmp);


    }


    public void createFormRandValues (ArrayList<Integer> randVals){ // creer un plateau selon un tableau randomValues pour les 25 cases
        if (randVals == null) randVals= randomValues;
        if ( randVals == null) return ;
        cases = new ArrayList<>();
        i=j=0;
         colLowerBond = 0 ;
         rowLowerBond= 0 ;
         colHigherBond = 15 +1;
         rowHigherBond= 14 +1;
        setWidth(600);
        setHeight(600);

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
