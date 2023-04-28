<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
	
		.one {
			width: 80%;
			margin-left: 10%;
			text-align: center;
		}
	
		
		.one .name {
			width: 23%;
			padding: 0, 10%;
		} 
		
		.tr4 {
			width: 100%
		}
		
		a {
			text-decoration: none;
		}
	
	</style>
</head>
<body>

	<section>
			
			<table class="one" border=1>
			
				<tr class="tr1">
					<td>방송인 이미지</td>
					<td class="name">방송인 이름</td>
					<td class="tv">방송 플랫폼</td>
					<td class="youtube">유튜브 구독자수(2023/04/28)</td>
				</tr>

				<tr class="tr2">
					<td>
						<img alt="c1" src="침착.jpg" width="100px" height="100px">
					</td>
					<td class="name">침착맨(이병건)</td>
					<td class="tv">트위치</td>
					<td class="youtube">
							<a href="req_album_result.jsp?chose=1">213만</a>
					</td>
				</tr>
				
				<tr class="tr3">
					<td>
						<img alt="c2" src="팡~이.jpg" width="100px" height="100px">
					</td>
					<td class="name">팡이요(차윤호)</td>
					<td class="tv">아프리카</td>
					<td class="youtube">
							<a href="req_album_result.jsp?chose=2">50.5만</a>
					</td>
				</tr>		
			
			
			</table>

	</section>

</body>
</html>