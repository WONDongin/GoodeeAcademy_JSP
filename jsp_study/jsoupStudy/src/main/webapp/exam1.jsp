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
    
<%-- /webapp/exam1.jsp 
 수출입은행 환율 정보 중 EUR,JPY(100), CNH,USD 통화만 ex02_exchange.jsp 작성한 형태로 
 출력하기 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  table {border-collapse: collapse;}
  table,td,th {border:2px solid grey;}
</style>
</head>
<body>
<%
  String url = "https://www.koreaexim.go.kr/wg/HPHKWG057M01";
  Document doc = null;
  List<List<String>> trlist = new ArrayList<List<String>>();
  List<String> title = Arrays.asList
  ("전신환받으실때","전신환보내실때","매매기준율","장부가격","중개매매기준율","중개장부가격");
  try {
	   doc = Jsoup.connect(url).get(); 
	   Elements trs = doc.select("tr");
	   for(Element tr : trs) {
		   List<String> tdlist = new ArrayList<String>();
		   Elements tds = tr.select("td"); 
	       for(Element td : tds) {
	    	   tdlist.add(td.html());
	       }
		   if (tdlist.size() > 0) {
	  	     if(tdlist.get(0).equals("EUR")||tdlist.get(0).equals("JPY(100)") ||
			    tdlist.get(0).equals("CNH")||tdlist.get(0).equals("USD") )
	            trlist.add(tdlist);
		   }
	   }
   } catch(IOException e) {
	   e.printStackTrace();
   }
   pageContext.setAttribute("trlist",trlist);
   pageContext.setAttribute("title",title);
%>
<table>
  <c:forEach items="${trlist }" var="tdlist">
     <c:forEach items="${tdlist}" var="td" varStatus="stat">
     <c:choose>
       <c:when test="${stat.index % 8 == 0}">
          <tr><td rowspan="6">${td}</td>
       </c:when>
       <c:when test="${stat.index % 8 == 1}">
          <td  rowspan="6">${td}</td>
       </c:when>
       <c:when test="${stat.index % 8 == 2}">
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