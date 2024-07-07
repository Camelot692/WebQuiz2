package com.camelot692.webquiz2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
//import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Received GET request for file upload page");

        File uploadDir = new File(getServletContext().getRealPath("/uploads"));
        if (!uploadDir.exists()) {
            boolean dirCreated = uploadDir.mkdir();
            if (dirCreated) {
                logger.info("Created upload directory: {}", uploadDir.getAbsolutePath());
            } else {
                logger.error("Failed to create upload directory: {}", uploadDir.getAbsolutePath());
            }
        }

        File[] files = uploadDir.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }

        req.setAttribute("quizFiles", fileNames);
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
        logger.info("Forwarded to upload.jsp with {} files", fileNames.size());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Received POST request for file upload");

        File uploadDir = new File(getServletContext().getRealPath("/uploads"));
        if (!uploadDir.exists()) {
            boolean dirCreated = uploadDir.mkdir();
            if (dirCreated) {
                logger.info("Created upload directory: {}", uploadDir.getAbsolutePath());
            } else {
                logger.error("Failed to create upload directory: {}", uploadDir.getAbsolutePath());
            }
        }

        for (Part part : req.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {
                part.write(new File(uploadDir, fileName).getAbsolutePath());
                logger.info("Uploaded file: {}", fileName);
            } else {
                logger.warn("Part with no file name encountered");
            }
        }

        resp.sendRedirect("upload");
        logger.info("Redirected to upload page after POST");
    }
}

