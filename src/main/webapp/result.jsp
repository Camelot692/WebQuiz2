<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Quiz Result</h1>
    <p>Your score: ${score} out of ${totalQuestions} </p>
    <%-- <p>Your score: ${score} out of ${totalQuestions} (${fmt:formatNumber(value=percentage, type="number", maximumFractionDigits=2)}%)</p> --%>
    <%-- <p>Your score: ${score} out of ${totalQuestions} (<fmt:formatNumber value="${percentage}" type="number" maxFractionDigits="2" />%)</p> --%>
    <%-- <p>Your score: ${score} out of ${totalQuestions} (<fmt:formatNumber value="${percentage}" type="number" maxFractionDigits="2" />%)</p> --%>
    <%--  <p>Some text ${percentage}</p> --%>
    <p>Your result is <fmt:formatNumber value="${percentage}" type="number" maxFractionDigits="2"/>% correct answers.</p>

    <c:choose>
        <c:when test="${passed}">
            <p>Congratulations! You passed the quiz!</p>
        </c:when>
        <c:otherwise>
            <p>Unfortunately, you did not pass the quiz. Better luck next time!</p>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/">Back to Home</a>
</div>
</body>
</html>