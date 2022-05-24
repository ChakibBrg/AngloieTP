package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class CaseDefinition extends Question{
    public CaseDefinition() {
        super();
        c.setFill(Color.BLUE);

    }
    public CaseDefinition(QuestionData qst) {
        super(qst);
        c.setFill(Color.BLUE);

    }

    @Override
    void action(Joueur player, AtomicInteger deplacement) {

    }

    @Override
    void Verification(Joueur player) {

    }
}
