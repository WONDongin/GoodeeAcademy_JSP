<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%--
최근 7일간 등록된 게시물 건수를 막대, 선 그래프로 출력하기
--%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>7일간 등록된 게시물</title>
	</head>
	<body>
		<sql:setDataSource var="conn" driver="org.mariadb.jdbc.Driver"
		    url="jdbc:mariadb://localhost:3306/gdjdb"
		    user="gduser" password="1234"/>
		    
		<sql:query var="rs" dataSource="${conn }">
			select DATE_FORMAT(regdate, "%Y-%m-%d") today, count(*) cnt from board
			group by today
			order by 1 desc
			LIMIT 0,7
		</sql:query>
		
		<div style="width:75%">
			<canvas id="canvas"></canvas>
		</div>
		
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
		<script type="text/javascript">
		// 색상 랜덤(0~255)
		let randomColorFactor = function(){
		  return Math.round(Math.random() * 255)
		}
		
		// rgba(100,200,300,1)
		// 투명도 : 1 or 0.3(0:투명)
		let randomColor = function(opacity) {
		  return "rgba(" + randomColorFactor() + "," 
		         + randomColorFactor() + "," 
		         + randomColorFactor() + "," 
		         + (opacity || ".3") + ")"
		}
		
		let chartData = {
			// x축 표시(작성자)
			labels : [<c:forEach items="${rs.rows}" var="m">"${m.today}",</c:forEach>],
			// 차트속성
			datasets : [
				{
					type : 'line',
					borderWidth : 2,
					borderColor : [<c:forEach items="${rs.rows}" var="m">randomColor(1),</c:forEach>],
					label : "건수",
					fill : false,
					data : [<c:forEach items="${rs.rows}" var="m">"${m.cnt}",</c:forEach>],
				},
				{
					type : 'bar',
					label : "건수",
					backgroundColor : [<c:forEach items="${rs.rows}" var="m">randomColor(1),</c:forEach>],
					data : [<c:forEach items="${rs.rows}" var="m">"${m.cnt}",</c:forEach>],
					borderWidth : 2
				}
			]
		}
		
		window.onload = function(){
			let ctx = document.querySelector("#canvas");
			new Chart(ctx,{
				type : 'bar',
				data : chartData,
				options : {
					responsive : true,
					title : {display:true, text:"최근 1주일 게시판 등록건수"},
					legend : {display:false},
					// 축(x,y) 설정
					scales : {
						xAxes : [{
								display :true,
								scaleLabel : {
									display : true,
									labelString : "등록일자"}
						}],
							
						yAxes : [{
							display :true,
							// 차트 0부터 시작
							ticks: { 
								beginAtZero: true,
								precision: 0
							},
							scaleLabel : {
								display : true,
								labelString : "게시물 작성 건수"}
						
						}]				
					}
				}
			})
		}
		</script>
	</body>
</html>