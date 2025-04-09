<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>EL에서 연산자 사용하기</title>
	</head>
	<body>
		<h3>
			\${5+7}=${5+7}<br>
			\${8-3}=${8-3}<br>
			\${8/3}=${8/3}<br>
			\${8 div 3}=${8 div 3}<br>
			\${8 % 3}=${8 % 3}<br>
			\${8 mod 3}=${8 mod 3}<br>
			\${10 == 9}=${10 == 9}<br>
			\${10 eq 9}=${10 eq 9}<br>
			\${10 != 9}=${10 != 9}<br>
			\${10 ne 9}=${10 ne 9}<br>
			\${10 >= 9}=${10 >= 9}<br>
			\${10 ge 9}=${10 ge 9}<br>
			\${10 > 9}=${10 > 9}<br>
			\${10 gt 9}=${10 gt 9}<br>
			\${10 <= 9}=${10 <= 9}<br>
			\${10 le 9}=${10 le 9}<br>
			\${10 < 9}=${10 < 9}<br>
			\${10 lt 9}=${10 lt 9}<br>
			\${5+4==8?8:10}=${5+4==8?8:10}<br>
			
			\${10}=${10}<br>
			\${'test'}=${'test'}<br>
			\${'EL의 상수 표현'}=${'EL의 상수 표현'}<br>
		</h3>
	</body>
</html>