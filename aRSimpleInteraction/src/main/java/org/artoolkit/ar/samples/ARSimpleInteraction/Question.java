package org.artoolkit.ar.samples.ARSimpleInteraction;

import java.io.Serializable;

/**
 * Created by Rember on 5/01/2017.
 */

public class Question implements Serializable {
    public Question(int id, String question, String answer1, String answer2, String answer3, String answer4, String answer5) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3  = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
    }

    public Question() {

    }

    private int id;
    public void setId(int id) {this.id = id;}
    public int getId() {return id;}

    private String question;
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() { return question; }

    private String answer1;
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getAnswer1() { return answer1; }

    private String answer2;
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    public String getAnswer2() { return answer2; }

    private String answer3;
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public String getAnswer3() { return answer3; }

    private String answer4;
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    public String getAnswer4() { return answer4; }

    private String answer5;
    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }
    public String getAnswer5() { return answer5; }
}
