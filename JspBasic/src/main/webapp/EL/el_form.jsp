<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action="el_con.jsp">
		# 이름: <input type = "text" name="name"> <br>
		# 별명: <input type = "text" name="nick"> <br>
		<input type="submit" value="확인">
	</form>

	<%
		session.setAttribute("data1", "hello~!");
		application.setAttribute("data2", "bye");
		session.setAttribute("data2", "이름은 같지만 다른 데이터");
		
	%>
	
	<a href="el_result.jsp">세션, 어플리케이션 데이터를 화면에 출력하기</a>


</body>
</html>