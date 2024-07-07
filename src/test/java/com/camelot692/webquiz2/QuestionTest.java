package com.camelot692.webquiz2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    @Test
    public void testQuestionCreation() {
        List<String> answers = List.of("Answer 1", "Answer 2", "Answer 3", "Answer 4");
        Question question = new Question(1, "What is 2+2?", answers, 3);

        assertEquals(1, question.getQuestionNumber());
        assertEquals("What is 2+2?", question.getQuestionText());
        assertEquals(answers, question.getAnswers());
        assertEquals(3, question.getCorrectAnswer());
    }
}

