<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidates" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage candidates</title>
</head>
<body>
<h2>Manage candidates</h2>
<li>
<c:forEach var="candidates" items="${requestScope.candidatelist}" >
<li>${candidates.id} ${candidates.surname} ${candidates.firstname} ${candidates.candNumb} ${candidates.age} ${candidates.hometown} ${candidates.party} ${candidates.profession} ${candidates.description}  
</c:forEach>
</li>
<form action='candupdate' method='post'> 
Candidate id: <input type='text' name='id' value='${requestScope.candidates.id}'><br> 
Surname: <input type='text' name='surname' value='${requestScope.candidates.surname}'><br>
First name: <input type='text' name='firstname' value='${requestScope.candidates.firstname}'><br>
Candidate number: <input type='text' name='candNumb' value='${requestScope.candidates.candNumb}'><br>
Age: <input type='text' name='age' value='${requestScope.candidates.age}'><br>
Home town: <input type='text' name='hometown' value='${requestScope.candidates.hometown}'><br>
Party: <input type='text' name='party' value='${requestScope.candidates.party}'><br>
Profession: <input type='text' name='profession' value='${requestScope.candidates.profession}'><br>
Description: <input type='text' name='description' value='${requestScope.candidates.description}'><br>
<input type='submit' name='ok' value='Send'> 
</form>
<br>
<h2>Add a candidate</h2>
<form action='candadd' method='post'>
Surname: <input type='text' name='surname' value='${requestScope.candidate.surname}'><br>
First name: <input type='text' name='firstname' value='${requestScope.candidate.firstname}'><br>
Candidate number: <input type='text' name='candNumb' value='${requestScope.candidate.candNumb}'><br>
Age: <input type='text' name='age' value='${requestScope.candidate.age}'><br>
Home town: <input type='text' name='hometown' value='${requestScope.candidate.hometown}'><br>
Party: <input type='text' name='party' value='${requestScope.candidate.party}'><br>
Profession: <input type='text' name='profession' value='${requestScope.candidate.profession}'><br>
Description: <input type='text' name='description' value='${requestScope.candidate.description}'><br>
<input type='submit' name='ok' value='Send'> 
</form>
</body>
</html>