<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/ex03_comment/ex01_comment.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP에서 사용되는 주석</title>
<script src="aaa.js"></script>
</head>
<body>
<h1>JSP에서 사용되는 주석 : 3가지</h1>
<ol>
  <li>JSP 주석 : &lt;%-- JSP 주석 --%&gt;</li>
  <%-- JSP 주석입니다. JSP 페이지가 서블릿으로 변경될때 제외 됩니다.
       서블릿소스에서 볼수 없습니다. --%>
  <li>JAVA 주석 : &lt;% //JAVA 주석 , /*자바 주석 */  %&gt;<br>
    자바에서 사용되는 주석. 스크립트 내부에서 사용됩니다. <br>
    자바 주석은 JSP, 서블릿 페이지에서 볼수 있습니다. <br>
    서블릿 페이지가 컴파일 될때 주석으로 처리 됩니다.
  </li>
  <li>HTML 주석 : &lt;!-- HTML 주석 --&gt;<br>
     JSP나 서블릿에서는 주석이 아닙니다. 브라우저에서 화면 출력시 주석으로 인식합니다.
<% String msg = "JSP 메세지"; %>     
     <!-- <%= msg %> : 이곳은 HTML 주석입니다. 서블릿까지 주석이 아닙니다.<br>
      HTML 주석은 부라우저의 소스보기에 보여 집니다. -->
  </li>
</ol>
</body>
</html>