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
import javafx.stage.Screen;

import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Plateau extends GridPane implements Serializable {
    private final int COL_MIN_LOWER_BOUND =0 ;
    private final int COL_MAX_HIGHER_BOUND =19 +1 ;
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

    private transient ArrayList<QuestionData> questions;            // Pour préparer les questions

    public transient   AnimationAvatar animAvatar;




    /////getters and setters
    public ArrayList<Case> getCases() {
        return cases;
    }

    ////constructeur
    public Plateau () throws IOException {

        avatar = new Image(getClass().getResourceAsStream("among/g01.png"));

        colLowerBond = COL_MIN_LOWER_BOUND ;
        rowLowerBond= ROW_MIN_LOWER_BOUND ;
        colHigherBond = COL_MAX_HIGHER_BOUND;
        rowHigherBond= ROW_MAX_HIGHER_BOUND;

        cases = new ArrayList<>();
        // ajout
        questions = new ArrayList<QuestionData>();      //Contiendra toutes les questions (image + définition)
        BufferedReader in = null;
        File repertoire = null;
        String ligne, mot, def;
        Image image;
        try {
            repertoire = new File("src/main/resources/com/tp/angloie/Questions/");
            if (repertoire.isDirectory()) {
                //On est rentré dans le répertoire Questions
                File[] sousRépertoires = repertoire.listFiles();
                for (File question : sousRépertoires) {
                    //Chaque sous répertoire comportant une question
                    if (question.isDirectory()) {
                        File[] fichiers = question.listFiles();
                        try {
                            //Accéder au fichier contenant la définition
                            def = "";
                            mot = question.getName();       //Le répertoire est nommé directement comme il faut
                            in = new BufferedReader(new FileReader("src/main/resources/com/tp/angloie/Questions/"+mot+"/"+fichiers[1].getName()));
                            while ((ligne = in.readLine()) != null) {
                                def = def + ligne + "\n";           //Récupérer la chaîne contenant la définition
                            }
                            image = new Image(getClass().getResourceAsStream("Questions/"+mot+"/"+fichiers[0].getName()));      //Récupérer l'image
                            questions.add(new QuestionData(mot, def, image));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if (in != null) {
                                in.close();
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ///////////////////
        setWidth(400);

        //setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        setHeight(400);
        //setHeight(Screen.getPrimary().getVisualBounds().getHeight());
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
            tempCases.add(new CaseDefinition(questions));
            tempCases.add(new CaseImage(questions));
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


        startAnim(0,0);
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
            tempCases.add(new CaseDefinition(questions));
            tempCases.add(new CaseImage(questions));
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
        num.setFill(Color.GREY);
        num.setFontSmoothingType(FontSmoothingType.LCD);
        num.setTabSize(3);
        num.setStrokeWidth(3);
        tmp.getChildren().add(num);

    }

    //////////////////  Animation avatar //////////////////////
    class AnimationAvatar extends AnimationTimer {


        private  int inPos  ; // la case concernée
        private  int outPos  ; // la case concernée

        private int  currOut, currIn ;
        private int count=1 ;


        private boolean  run  = false ;


        long previous =0;

        public AnimationAvatar(int inPos,int outPos ){


            this.inPos=this.currIn = inPos ;
            this.outPos=this.currOut = outPos ;


            StackPane.setMargin(imageViewCase,new Insets(0,0,7,0));


        }




        public void stop1(){
            run = false ;
        }

        public void setInPos(int inPos) {
            this.inPos = inPos;
        }

        public void setOutPos(int outPos) {
            this.outPos = outPos;
        }


        public boolean restart(){
            count = 1 ;
            if ( currIn != inPos){
                currIn = inPos ;
                currOut= outPos ;
                return true;
            }
            else return false ;
        }

        @Override
        public  void handle  ( long current) {
            if (run) {
                if (count == 22) {
                    cases.get(currIn).getChildren().remove(imageViewCase);
                    cases.get(currOut).getChildren().add(imageViewCase);
                    count++;
                    return;
                }
                if (count > 36) {
                    if ( !restart() ) {
                        stop1();
                    }
                    return ;
                }
                if (current - previous > 60) {
                    if (count >= 10)
                        imageViewCase.setImage(new Image(getClass().getResourceAsStream("among/g" + count + ".png")));
                    else imageViewCase.setImage(new Image(getClass().getResourceAsStream("among/g0" + count + ".png")));
                    count++;
                    previous = current;
                }
            }

        }
    }


    public void startAnim(int curr  , int dest){
        animAvatar = new AnimationAvatar(curr,dest);
        animAvatar.start();

    }
    /// Deplcement de l'avatar sur le plateau
    public void deplacer(int current  , int dest){
        if (current == dest) return;

        animAvatar.run = true ;
        animAvatar.setInPos(current);
        animAvatar.setOutPos(dest);

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