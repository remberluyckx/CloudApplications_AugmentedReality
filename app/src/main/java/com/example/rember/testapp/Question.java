package com.example.rember.testapp;

/**
 * Created by Rember on 20/11/2016.
 */

public class Question {

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String answer5) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3  = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        //ni zeker of dit klopt, miss omdraaien
    }

    private String question = null;
    public String getQuestion() { return question; }

    private String answer1 = null;
    public String getAnswer1() { return answer1; }

    private String answer2 = null;
    public String getAnswer2() { return answer2; }

    private String answer3 = null;
    public String getAnswer3() { return answer3; }

    private String answer4 = null;
    public String getAnswer4() { return answer4; }

    private String answer5 = null;
    public String getAnswer5() { return answer5; }
}
