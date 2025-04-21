<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/view/book/bookForm.jsp
    모델2로 http://localhost:8080/model2Study/book/bookForm 요청시 화면 출력     
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 글쓰기 화면</title>
<script type="text/javascript">
   function inputcheck(f) {
       if(f.writer.value == '') {
		   alert("방문자를 입력하세요");
		   f.writer.focus();
		   return false;
       }
       if(f.title.value == '') {
		   alert("제목 입력하세요");
		   f.title.focus();
		   return false;
       }
       if(f.content.value == '') {
		   alert("내용 입력하세요");
		   f.content.focus();
		   return false;
       }
       return true;
   }
</script>
</head>
<body>
<form action="bookwrite" method="post" 
      onsubmit="return inputcheck(this)">
<h2>방명록쓰기</h2>
<table class="table">
<tr><td>방문자</td><td><input type="text" name="writer" class="form-control"></td></tr>
<tr><td>제목</td><td><input type="text" name="title" class="form-control"></td></tr>
<tr><td>내용</td>
    <td><textarea rows="10" cols="60" name="content" class="form-control"></textarea></td></tr>
<tr><td colspan="2" align="center">
     <input type="submit" value="글쓰기"class="btn btn-dark"></td></tr>
</table></form></body></html>