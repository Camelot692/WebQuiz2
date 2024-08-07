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

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(QuizServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Handling GET request");

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        if (questions == null || questions.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        int questionIndex = 0;
        if (req.getParameter("questionIndex") != null) {
            questionIndex = Integer.parseInt(req.getParameter("questionIndex"));
        }

        if (questionIndex >= questions.size()) {
            resp.sendRedirect(req.getContextPath() + "/result");
            return;
        }

        Question currentQuestion = questions.get(questionIndex);
        req.setAttribute("question", currentQuestion);
        req.setAttribute("questionIndex", questionIndex);
        req.getRequestDispatcher("/question.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Handling POST request");

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        if (questions == null || questions.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        int questionIndex = Integer.parseInt(req.getParameter("questionIndex"));
        String answer = req.getParameter("answer");

        if (answer != null) {
            req.getSession().setAttribute("answer_" + questionIndex, answer);
            logger.info("Stored answer for question " + questionIndex + ": " + answer);
        }

        questionIndex++;
        if (questionIndex >= questions.size()) {
            resp.sendRedirect(req.getContextPath() + "/result");
        } else {
            resp.sendRedirect(req.getContextPath() + "/quiz?questionIndex=" + questionIndex);
        }
    }
}
