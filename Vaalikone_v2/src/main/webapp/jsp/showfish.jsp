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

<link rel="stylesheet" type="text/css" href="mycssfilesomewhere.css">
<script src="myscriptfile.js"></script>

</head>
<body>
<h2>Fish application</h2>
<ol>
<c:forEach var="question" items="${requestScope.fishlist}" >
<li>${question.id}: ${question.question} <a href='/delete?id=${question.id}'>delete</a> <a href='/readtoupdate?id=${question.id}'>update</a>
</c:forEach>
</ol>

<%
ArrayList<Question> questionList=(ArrayList<Question>)request.getAttribute("questionlist");

for (int i=0;questionList!=null && i<questionList.size();i++){
	Question q=questionList.get(i);
	out.println(q.getId()+": "+q.getQuestion()+"<a href='/delete?id="+q.getId()+"'>delete</a> <a href='/readtoupdate?id="+q.getId()+"'>update</a>");
}
%>

<%@ include file="../html/somehtml.html" %>



</body>
</html>