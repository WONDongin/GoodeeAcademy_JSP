<%@page import="model.test0404.BookDao"%>
<%@page import="model.test0404.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  /webapp/test0404/test1.jsp
    1. book table 생성
    2. testForm.jsp 페이지에서 전달한 파라미터를 Book Bean 클래스를 이용하여 DB에 저장하기
       TestDao.java클래스를 생성하여 db에 등록하기
    3. 등록된 내용을 db에서 읽어 화면에 출력하기     
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<%
    request.setCharacterEncoding("UTF-8");
    Book book = new Book();
    book.setWriter(request.getParameter("writer"));
    book.setTitle(request.getParameter("title"));
    book.setContent(request.getParameter("content"));
    BookDao dao = new BookDao();
    String msg = null;
    if(dao.insert(book)) {
    	Book dbBook = dao.selectOne(book.getWriter());
%>    	
</head>
<body><table>
     <tr><td>이름</td><td><%=dbBook.getWriter() %></td></tr>
     <tr><td>제목</td><td><%=dbBook.getTitle() %></td></tr>
     <tr><td>내용</td><td><%=dbBook.getContent() %></td></tr></table>
</body></html>
<%  }%>
