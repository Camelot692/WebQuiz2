package com.camelot692.webquiz2;

import java.util.List;

//Data Model Layer
public class Question {
    private int questionNumber;
    private String questionText;
    private List<String> answers;
    private int correctAnswer;

    public Question(int questionNumber, String questionText, List<String> answers, int correctAnswer) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionNumber() { return questionNumber; }
    public String getQuestionText() { return questionText; }
    public List<String> getAnswers() { return answers; }
    public int getCorrectAnswer() { return correctAnswer; }
}
