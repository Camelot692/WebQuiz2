<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload Quiz Files</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Upload Quiz Files</h1>
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" multiple />
        <button type="submit">Upload</button>
    </form>

    <h2>Available Quizzes</h2>
    <c:if test="${not empty quizFiles}">
        <form action="startQuiz" method="post">
            <select name="selectedQuiz">
                <c:forEach var="fileName" items="${quizFiles}">
                    <option value="${fileName}">${fileName}</option>
                </c:forEach>
            </select>
            <button type="submit">Start Quiz</button>
        </form>
        <form action="deleteAll" method="post">
            <button type="submit">Delete All Files</button>
        </form>
    </c:if>
    <c:if test="${empty quizFiles}">
        <p>No quizzes available. Please upload files.</p>
    </c:if>
</div>
</body>
</html>
