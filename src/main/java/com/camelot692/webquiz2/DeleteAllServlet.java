package com.camelot692.webquiz2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteAllServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteAllServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Handling deletion of all questions");
        File uploadDir = new File(getServletContext().getRealPath("/uploads"));
        if (uploadDir.exists()) {
            File[] files = uploadDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
        logger.info("All questions deleted");
        resp.sendRedirect("upload");
    }

}

