<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Question Page</title>
<style>

body {
background-color: tomato;
}

</style>

</head>
<body>
<h2>Fish application</h2>
<li>
<c:forEach var="question" items="${requestScope.questionlist}" >
<li>${question.id}: ${question.question} <a href='/delete?id=${question.id}'>delete</a> <a href='/readtoupdate?id=${question.id}'>update</a>
</c:forEach>
</li>


<form action='update' method='post'> 
Question id: <input type='text' name='id' value='${requestScope.question.id}'><br> 
Question: <input type='text' name='question' value='${requestScope.question.question}'><br>
<input type='submit' name='ok' value='Send'> 
</form>
<h2>Add a question</h2>
<form action='addquestion' method='post'>
Question: <input type='text' name='question' value='${requestScope.question.question}'><br> 
<input type='submit' name='ok' value='Send'> 
</form>



</body>
</html>