package org.artoolkit.ar.samples.ARSimpleInteraction;

import java.io.Serializable;

/**
 * Created by detuur on 12/01/2017.
 */
public class Answer implements Serializable{

    public String title, answer1, answer2, answer3, answer4, answer5;
    public int answer1votes, answer2votes, answer3votes, answer4votes, answer5votes;
    public boolean active = true;

    public Answer() {};

    public Answer(Question activeQuestion, int answer1votes, int answer2votes, int answer3votes,
                  int answer4votes, int answer5votes) {
        this.answer1votes = answer1votes;
        this.answer2votes = answer2votes;
        this.answer3votes = answer3votes;
        this.answer4votes = answer4votes;
        this.answer5votes = answer5votes;
        this.title = activeQuestion.getQuestion();
        this.answer1 = activeQuestion.getAnswer1();
        this.answer2 = activeQuestion.getAnswer2();
        this.answer3 = activeQuestion.getAnswer3();
        this.answer4 = activeQuestion.getAnswer4();
        this.answer5 = activeQuestion.getAnswer5();
    }

    static public Answer getTestAnswer() {
        Answer out = new Answer();
        //out.active = false;
        out.answer4votes = 44;
        out.title = "kek";
        out.answer5 = "helele";
        return out;
    }
}
