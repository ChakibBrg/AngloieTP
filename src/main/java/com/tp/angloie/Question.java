package com.tp.angloie;

 public abstract class Question extends Case{
   protected Question qst ;

   public Question (Question qst){
       super();
       this.qst=qst ;

   }

   abstract void Verification(Joueur player) ;
}
