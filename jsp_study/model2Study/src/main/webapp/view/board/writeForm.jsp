<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/view/board/writeForm.jsp
  1. boardid가 1인 경우, 관리자가 아니면 관리자만 공지사항 글쓰기가 가능합니다.
     공지사항 목록 페이지 이동 
--%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" 
value="${pageContext.request.contextPath }" scope="application"/>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
<form action="write" method="post" enctype="multipart/form-data" name="f">
<h2 class="text-center">게시판 글쓰기</h2>       
<table class="table">
 <tr><td>글쓴이</td>
     <td><input type="text" name="writer" class="form-control"></td></tr>
 <tr><td>비밀번호</td>
     <td><input type="password" name="pass"  class="form-control"></td></tr>
 <tr><td>제목</td>
     <td><input type="text" name="title"  class="form-control"></td></tr>
 <tr><td>내용</td>
 <td><textarea rows="15" name="content" class="form-control" id="summernote"></textarea></td></tr>
 <tr><td>첨부파일</td><td><input type="file" name="file1"></td></tr>
 <tr><td colspan="2" class="text-center">
   <a href="javascript:inputcheck()" class="btn btn-primary">게시물등록</a></td></tr>
</table></form>
<script type="text/javascript">
   function inputcheck() {
	   f = document.f;
	   if(f.writer.value=="") {
		   alert("글쓴이를 입력하세요");
		   f.name.focus();
		   return;
	   }
	   if(f.pass.value=="") {
		   alert("비밀번호를 입력하세요");
		   f.pass.focus();
		   return;
	   }
	   if(f.title.value=="") {
		   alert("제목을 입력하세요");
		   f.title.focus();
		   return;
	   }
	   f.submit(); //submit 발생=> form의 action 페이지로 요청
   }
</script>
<%-- summbernote 관련 구현 --%>
<script type="text/javascript">
   $(function() {
	   $("#summernote").summernote({
		   height:300,
		   callbacks : {
			   //onImageUpload : 이미지 업로드 이벤트 발생.
			   //files : 한개이상의 이미지 업로드 가능. 배열
			   onImageUpload : function(files) {
				   for(let i=0;i < files.length;i++) {
					   sendFile(files[i]); //하나씩 ajax 이용하여 서버로 파일 전송
				   }
			   }
		   }
	   })
   })
   function sendFile(file) {
	   let data = new FormData(); //폼데이터를 수집하고 전송가능한 객체. 파일업로드에 사용됨.
	   data.append("file",file); //file : 이미지 파일
	   $.ajax({
		   url : "${path}/board/uploadImage",
		   type : "post",
		   data : data,
		   processData : false,
		   contentType : false,
		   success : function(url) {
			   //url : 업로드된 이미지의 접근 url 정보
			   $("#summernote").summernote("insertImage",url);
		      // <img src="url" > 변경.
		   },
		   error : function(e) {
			   alert("이미지 업로드 실패 :" + e.status);
		   }
	   })
   }
</script>

</body>
</html>