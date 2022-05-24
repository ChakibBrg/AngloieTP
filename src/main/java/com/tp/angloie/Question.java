package com.tp.angloie;

 public abstract class Question extends Case{
   protected QuestionData qst ;

   public Question (){
       super();


   }public Question(QuestionData qst){
       super();
       this.qst=qst ;

   }

   abstract void Verification(Joueur player) ;
}
