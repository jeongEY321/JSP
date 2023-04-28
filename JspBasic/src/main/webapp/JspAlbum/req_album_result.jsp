<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%
    	String chose = request.getParameter("chose");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		.top {
			text-align: center;
		}
	</style>
</head>
<body>


<% if(chose.equals("1")) { %>
	<div class="top">
		<h1>선택하신 방송인 정보</h1>
		<hr>
		<p>트위치에서 방송을하는 침착맨(이병건)을 선택하셨습니다.</p>
		<hr>
		<h2>침착맨의 유튭영상</h2>
		
		<iframe width="852" height="479" src="https://www.youtube.com/embed/j4q-Dr5mwDo?autoplay=1" title="아무튼 진실만을 보도하는 침착맨 뉴스" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
	</div>
	
	<% } else { %>
	
		<div class="top">
		<h1>선택하신 방송인 정보</h1>
		<hr>
		<p>아프리카티비에서 방송을하는 팡이요(차윤호)를 선택하셨습니다.</p>
		<hr>
		<h2>팡이요의 유튭영상</h2>
		<iframe width="852" height="479" src="https://www.youtube.com/embed/8_nf0bZnZ8U?autoplay=1" title="팡이요 리믹스 리액션 모음" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
		
	</div>
	
	<% } %>
</body>
</html>