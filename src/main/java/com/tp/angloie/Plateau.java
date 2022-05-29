package com.tp.angloie;

import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Plateau extends GridPane implements Serializable {
    private final int COL_MIN_LOWER_BOUND =0 ;
    private final int COL_MAX_HIGHER_BOUND =15 +1 ;
    private final int ROW_MIN_LOWER_BOUND =0 ;
    private final int ROW_MAX_HIGHER_BOUND =13 +1 ;

    private transient int colLowerBond  ;
    private transient int rowLowerBond ;
    private transient int  colHigherBond ;
    private transient int rowHigherBond;

     transient private ArrayList<Case> cases ;
     private ArrayList<Integer>  randomValues  ;
     private ArrayList<Integer>  indexes  ;
     public int i = 0 , j = 0 ;

     private transient Image avatar ;
     private transient ImageView imageViewCase ;




    /////getters and setters
    public ArrayList<Case> getCases() {
        return cases;
    }

    ////constructeur
    public Plateau ()  {

        avatar = new Image(getClass().getResourceAsStream("among/g01.png"));

        colLowerBond = COL_MIN_LOWER_BOUND ;
        rowLowerBond= ROW_MIN_LOWER_BOUND ;
        colHigherBond = COL_MAX_HIGHER_BOUND;
        rowHigherBond= ROW_MAX_HIGHER_BOUND;

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

        addNum(caseCount,tmp);


        cases.add(tmp);
        caseCount++;
        incrementIndexes();
        while ( caseCount != 99 ){

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
                    addNum(caseCount,tmp);


                    cases.add(tmp);
                    incrementIndexes();
                    caseCount ++ ;
                }
                tmp=new Fin();
                this.add(tmp,j,i);
                GridPane.setHalignment(tmp, HPos.CENTER);
                GridPane.setValignment(tmp, VPos.CENTER);

        addNum(caseCount,tmp);



        cases.add(tmp);

                    imageViewCase= new ImageView();
                    imageViewCase.setFitHeight(50);
                    imageViewCase.setFitWidth(35);
                    imageViewCase.setImage(avatar);

                    cases.get(0).getChildren().add(imageViewCase);



    }

    public void createFormSavedValues (){ // creer un plateau en utilisant le tableau sauvegardeé precedemment ( serialisé )

        avatar = new Image(getClass().getResourceAsStream("among/g01.png"));

        cases = new ArrayList<>();
        i=j=0;
              colLowerBond = COL_MIN_LOWER_BOUND ;
              rowLowerBond= ROW_MIN_LOWER_BOUND ;
              colHigherBond = COL_MAX_HIGHER_BOUND;
              rowHigherBond= ROW_MAX_HIGHER_BOUND;
        setWidth(600);
        setHeight(600);

        QuestionData qst  = null;

        /////////// traitement de questions /////

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
        addNum(caseCount,tmp);

        cases.add(tmp);
        caseCount++;
        incrementIndexes();
        while ( caseCount != 99 ){
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
            addNum(caseCount,tmp);

            cases.add(tmp);
            incrementIndexes();
            caseCount ++ ;
        }
        tmp=new Fin();
        this.add(tmp,j,i);
        GridPane.setHalignment(tmp, HPos.CENTER);
        GridPane.setValignment(tmp, VPos.CENTER);
        addNum(caseCount,tmp);

        cases.add(tmp);

        imageViewCase= new ImageView();
        imageViewCase.setFitHeight(50);
        imageViewCase.setFitWidth(35);

        imageViewCase.setImage(avatar);
        cases.get(Main.jeu.getPartieActuelle().getPosActuelle()).getChildren().add(imageViewCase);

    }


    private void addNum ( int i , Case tmp){
        Text num = new Text(Integer.toString(i));
        num.setTextAlignment(TextAlignment.CENTER);
        num.setFill(Color.WHITE);
        num.setFontSmoothingType(FontSmoothingType.LCD);
        num.setTabSize(3);
        num.setStrokeWidth(3);
        tmp.getChildren().add(num);

    }

    //////////////////  Animation avatar //////////////////////
    class AnimationAvatar extends AnimationTimer {
        private final int inPos  ; // la case concernée
        private final int outPos  ; // la case concernée
        private int count=1 ;


        long previous =0;

            public AnimationAvatar(int inPos,int outPos ){


                this.inPos = inPos ;
                this.outPos = outPos ;


                StackPane.setMargin(imageViewCase,new Insets(0,0,7,0));


            }

        @Override
        public  void handle  ( long current) {
            if(count == 22 ){
                cases.get(inPos).getChildren().remove(imageViewCase);
                cases.get(outPos).getChildren().add(imageViewCase);
                count++;
                return;
            }
            if (count > 35) {
                stop() ;
            }
            if (  current - previous > 60) {
                if (count >= 10) imageViewCase.setImage( new Image(getClass().getResourceAsStream("among/g"+count+".png")));
                else imageViewCase.setImage( new Image(getClass().getResourceAsStream("among/g0"+count+".png")));
                count++;
                previous = current;
            }
            }
        }

        /// Deplcement de l'avatar sur le plateau
    public void deplacer(int current  , int dest){
        new AnimationAvatar(current,dest).start();
    }



/////// Gerer les indice de la grille au cours de la creation du tableau /////////////////////

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
