<%@page import="java.io.IOException"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /webapp/ex02_exchange.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환율 정보</title>
<style type="text/css">
  table {border-collapse: collapse;}
  table,td,th {border : 2px solid grey;}
</style>
</head>
<body>
<%
String url = "https://www.koreaexim.go.kr/wg/HPHKWG057M01";
String line = "";
Document doc = null;  
List<List<String>> trlist = new ArrayList<List<String>>();
List<String> title = Arrays.asList("전신환받으실때","전신환보내실때",
		"매매기준율","장부가격","중개매매기준율","중개장부가격");
try {
	doc = Jsoup.connect(url).get();
	Elements trs = doc.select("tr"); //tr 태그들
	for(Element tr : trs) {
		//tr : tr 태그 한개
		List<String> tdlist = new ArrayList<String>();
		Elements tds = tr.select("td"); //td 태그들
		for(Element td : tds) {
		  tdlist.add(td.html()); //td.html() : td의 내용. 
		}
		trlist.add(tdlist);
	}
} catch(IOException e) {
	e.printStackTrace();
}
pageContext.setAttribute("trlist", trlist); //el,jstl 사용을 위해 속성 등록
pageContext.setAttribute("title", title);   //el,jstl 사용을 위해 속성 등록
%>
<table>
<c:forEach items="${trlist }" var="tdlist">
  <c:forEach items="${tdlist}" var="td" varStatus="stat">
    <c:choose>
      <c:when test="${stat.index % 8 == 0}"> <%-- 첫번째 td : 통화코드 --%>
         <tr><td rowspan="6">${td}</td>
      </c:when>
      <c:when test="${stat.index % 8 == 1}"> <%-- 두번째 td : 통화명 --%>
         <td rowspan="6">${td}</td>
      </c:when>
      <c:when test="${stat.index % 8 == 2}"> <%-- 세번째 td : 전신환받으실때 환율 --%>
         <td>${title[0]}</td><td>${td}</td></tr>
      </c:when>
      <c:otherwise>
        <tr><td>${title[stat.index - 2]}</td><td>${td}</td></tr>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</c:forEach>
</table>
</body>
</html>