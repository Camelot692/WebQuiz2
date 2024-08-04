package com.camelot692.webquiz2;

import com.camelot692.webquiz2.services.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteAllServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteAllServlet.class);
    private final QuizService quizService = new QuizService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Received POST request to delete all files");

        // Use service layer for deleting files
        quizService.deleteAllFiles(getServletContext());

        resp.sendRedirect("upload");
        logger.info("Redirected to upload page after deleting all files");
    }

}

