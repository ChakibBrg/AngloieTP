package com.tp.angloie;

import javafx.scene.paint.Color;

public class CaseDefinition extends Question{
    public CaseDefinition() {
        super();
        setFill(Color.BLUE);

    }
    public CaseDefinition(Question qst) {
        super(qst);
        setFill(Color.BLUE);

    }

    @Override
    void action(Joueur player) {

    }

    @Override
    void Verification(Joueur player) {

    }
}
