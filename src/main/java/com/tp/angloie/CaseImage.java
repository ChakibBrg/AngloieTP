package com.tp.angloie;

import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class CaseImage extends Question {
    public CaseImage() {
        super();
        c.setFill(Color.PINK);

    }public CaseImage(QuestionData qst) {
        super(qst);
        c.setFill(Color.PINK);

    }

    @Override
    void action(AtomicInteger points, AtomicInteger deplacement) {

    }

    @Override
    void Verification(Joueur player) {

    }
}
