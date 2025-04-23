<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	파이그래프로 게시글 작성자별 건수 출력하기 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>파이 그래프</title>
	</head>
	<body>
		<sql:setDataSource var="conn" driver="org.mariadb.jdbc.Driver"
		    url="jdbc:mariadb://localhost:3306/gdjdb"
		    user="gduser" password="1234"/>
		<sql:query var="rs" dataSource="${conn }">
			select writer, count(*) cnt from board
			group by writer
			order by 2 desc
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
		
		let config = {
			// 파이 : pie , 도우넛 : doughnut
			type : "pie",
			data : {
				datasets : [
					{
						data : [<c:forEach items="${rs.rows}" var="m">"${m.cnt}",</c:forEach>],
						backgroundColor : [<c:forEach items="${rs.rows}" var="m">randomColor(1),</c:forEach>],
						label : "건수"
					}],
				labels : [<c:forEach items="${rs.rows}" var="m">"${m.writer}",</c:forEach>]
			},
			options : {
				responsive : true,
				legend : {position:'right'},
				title : {display:true, text:'게시글 등록자'},
				animation : {animateScale : true, animateRotate: true}
			}
		}
		
		window.onload = function(){
			let ctx = document.querySelector("#canvas");
			new Chart(ctx, config);
		}
		</script>
	</body>
</html>