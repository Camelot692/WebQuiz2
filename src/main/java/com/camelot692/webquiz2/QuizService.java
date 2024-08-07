package com.camelot692.webquiz2.services;

import com.camelot692.webquiz2.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Service Layer
public class QuizService {
    private static final Logger logger = LogManager.getLogger(QuizService.class);

    public void deleteAllFiles(ServletContext context) {
        File uploadDir = new File(context.getRealPath("/uploads"));
        File[] files = uploadDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.delete()) {
                    logger.info("Deleted file: {}", file.getName());
                } else {
                    logger.warn("Failed to delete file: {}", file.getName());
                }
            }
        }
    }

    public static List<Question> getQuestionsFromFile(File quizFile) throws IOException {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(quizFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int questionNumber = Integer.parseInt(parts[0]);
                String questionText = parts[1];
                List<String> answers = Arrays.asList(parts[2], parts[3], parts[4], parts[5]);
                int correctAnswer = Integer.parseInt(parts[6]);
                questions.add(new Question(questionNumber, questionText, answers, correctAnswer));
            }
        }
        return questions;
    }

}
