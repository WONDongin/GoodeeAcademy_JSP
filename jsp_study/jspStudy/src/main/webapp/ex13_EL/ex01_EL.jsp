<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 

--%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>EL</title>
	</head>
	<body>
	<%
		String tel = "010-1111-2222";
		pageContext.setAttribute("tel", tel); // 속성 등록
		// pageContext.setAttribute("test", "pageContext의 test 속성");
		// request.setAttribute("test", "request의 test 속성");
		// session.setAttribute("test", "session의 test 속성");
		application.setAttribute("test", "application의 test 속성");
		
		String name = "홍길동";
	%>
	<h3>JSP의 스크립트를 이용하여 파라미터와 속성값 출력</h3>
	pageContext tel 속성값 : <%= pageContext.getAttribute("tel") %><br/>
	pageContext test 속성값 : <%= pageContext.getAttribute("test") %><br/>
	request test 속성값 : <%= request.getAttribute("test") %><br/>
	session test 속성값 : <%= session.getAttribute("test") %><br/>
	application test 속성값 : <%= application.getAttribute("test") %><br/>
	
	name 변수값 : <%= name %><br/>
	id 파라미터 : <%= request.getParameter("id") %><br/>
	없는 속성 : <%= request.getAttribute("noattr") %><br/>
	없는 파라미터 : <%= request.getParameter("noparam") %><br/>
	
	
	<h3>JSP의 EL를 이용하여 파라미터와 속성값 출력</h3>
	pageContext tel 속성값 :  ${pageScope.tel}<br/>
	pageContext test 속성값 : ${pageScope.test}<br/>
	request test 속성값 : ${requestScope.test}<br/>
	session test 속성값 : ${sessionScope.test}<br/>
	application test 속성값 : ${applicationScope.test}<br/>
	
	name 변수값 : EL 방식으로 출력 방법 없음<br/>
	id 파라미터 : ${param.id}<br/>
	없는 속성 : ${requestScope.noattr}<br/>
	없는 파라미터 : ${param.noparam}<br/>
	
	<h3>영역을 표시하여 속성 출력</h3>
	\${test} : ${test}<br/>
	\${pageScope.test} : ${pageScope.test}<br/>
	\${requestScope.test} : ${requestScope.test}<br/>
	\${sessionScope.test} : ${sessionScope.test}<br/>
	\${applicationScope.test} : ${applicationScope.test}<br/>
	<%-- 
	${test} : 영역담당 객체에 등록된 속성 중 이름이 test인 속성값을 출력
	우선순위
	1. page 영역에 등록된 pageScope.test 속성값 출력 
	2. 1번이 없으면 request 영역에 등록된 requestScope.test 속성값 출력 
	3. 2번이 없으면 session 영역에 등록된 sessionScope.test 속성값 출력 
	4. 3번이 없으면 application 영역에 등록된 applicationScope.test 속성값 출력
	5. 1~4번의 내용이 없으면, 공란을 출력
	
	${영역객체.test} : 영역객체에 등록된 속성 중 이름이 test인 속성값을 출력
	1. 해당 영역에 등록된 속성이 없으면 공란을 출력 
	--%>
	
	
	</body>
</html>