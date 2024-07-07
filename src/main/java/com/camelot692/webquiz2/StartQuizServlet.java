package com.camelot692.webquiz2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/startQuiz")
public class StartQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedQuiz = req.getParameter("selectedQuiz");
        if (selectedQuiz == null || selectedQuiz.isEmpty()) {
            resp.sendRedirect("upload");
            return;
        }

        File quizFile = new File(getServletContext().getRealPath("/uploads"), selectedQuiz);
        if (!quizFile.exists()) {
            resp.sendRedirect("upload");
            return;
        }

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

        req.getSession().setAttribute("questions", questions);
        resp.sendRedirect("quiz");
    }
}


