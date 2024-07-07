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

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ResultServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Handling result display");

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        if (questions == null || questions.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            String answer = (String) req.getSession().getAttribute("answer_" + i);
            int correctAnswer = questions.get(i).getCorrectAnswer() - 1;
            logger.info("Retrieved answer for question " + i + ": " + answer + "; correctAnswer: " + correctAnswer + ";");
            if (answer != null && Integer.parseInt(answer) == correctAnswer) {
                score++;
            }
        }

        int totalQuestions = questions.size();
        double percentage = ((double) score / totalQuestions) * 100;
        boolean passed = percentage >= 80;

        req.setAttribute("score", score);
        req.setAttribute("totalQuestions", totalQuestions);
        req.setAttribute("percentage", percentage);
        req.setAttribute("passed", passed);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}

