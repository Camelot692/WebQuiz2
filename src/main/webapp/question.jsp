<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Question</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Question ${question.questionNumber}</h1>
    <p>${question.questionText}</p>
    <form action="quiz" method="post">
        <input type="hidden" name="questionIndex" value="${questionIndex}" />
        <c:forEach var="answer" items="${question.answers}" varStatus="status">
            <div>
                <input type="radio" name="answer" value="${status.index}" id="answer_${status.index}" />
                <label for="answer_${status.index}">${answer}</label>
            </div>
        </c:forEach>
        <button type="submit">Next</button>
    </form>
</div>
</body>
</html>

