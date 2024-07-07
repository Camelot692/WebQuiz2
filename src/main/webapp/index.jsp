<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>WebQuiz2</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Welcome to the Quiz Application</h1>
    <p>Here are the rules of the quiz:</p>
    <ul>
        <li>Each question has one answer option.</li>
        <li>You can upload multiple quizzes.</li>
        <li>Quiz CSV file format: question_number; question; answer1; answer2; answer3; answer4; correct_answer_number;</li>
        <li>After selecting the file, click the "Upload" button.</li>
        <li>Then select the quiz you want from the list.</li>
        <li>The quiz starts after pressing the "Start Quiz" button.</li>
        <li>You can clear quizzes by clicking the "Delete All Files" button.</li>
        <li>Select the correct answer and move to the next question.</li>
        <li>You need to answer at least 80% of the questions correctly to pass the quiz.</li>
    </ul>
    <form action="upload" method="get">
        <button type="submit">Start</button>
    </form>
</div>
</body>
</html>
