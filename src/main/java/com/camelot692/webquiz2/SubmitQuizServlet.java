package com.camelot692.webquiz2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/submit-quiz")
public class SubmitQuizServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SubmitQuizServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Submitting quiz results");

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        if (questions == null || questions.isEmpty()) {
            logger.warn("No questions found in session");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            String answer = req.getParameter("answer_" + i);
            if (answer != null && Integer.parseInt(answer) == questions.get(i).getCorrectAnswer()) {
                score++;
            }
        }

        req.setAttribute("score", score);
        req.setAttribute("total", questions.size());
        logger.info("Quiz submitted: score = {}, total questions = {}", score, questions.size());
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}

