package com.camelot692.webquiz2;

import com.camelot692.webquiz2.services.QuizService;
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

        List<Question> questions;
        try {
            // Use service layer for reading files
            questions = QuizService.getQuestionsFromFile(quizFile);
        } catch (IOException e) {
            throw new ServletException("Error reading quiz file", e);
        }

        req.getSession().setAttribute("questions", questions);
        resp.sendRedirect("quiz");
    }
}


