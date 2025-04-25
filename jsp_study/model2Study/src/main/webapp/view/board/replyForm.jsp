<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/view/board/replyForm.jsp --%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" 
value="${pageContext.request.contextPath }" scope="application"/>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 게시글</title>
</head>
<body>
<form action="reply" method="post" name="f">
<input type="hidden" name="num" value="${board.num}">
<input type="hidden" name="grp" value="${board.grp}" >
<input type="hidden" name="grplevel" value="${board.grplevel}" >
<input type="hidden" name="grpstep" value="${board.grpstep}" >
<input type="hidden" name="boardid" value="${board.boardid}" >
<div class="container">
<h2>${(board.boardid=='1')?"공지사항":"자유게시판"}</h2>
<table class="table">
<tr><th>글쓴이</th>
    <td><input type="text" name="writer" class="form-control"></td></tr>
<tr><th>비밀번호</th>
    <td><input type="password" name="pass" class="form-control"></td></tr>
<tr><th>제목</th><td><input type="text" name="title"  class="form-control" 
      value="RE:${board.title}"></td></tr>
<tr><th>내용</th>
<td><textarea name="content" rows="15"  class="form-control" id="summernote"></textarea></td></tr>
<tr><td colspan="2" align="center">
 <a href="javascript:document.f.submit()">[답변글등록]</a></td></tr>    
</table></div></form>
<%-- summbernote 관련 구현 --%>
<script type="text/javascript">
   $(function() {
	   $("#summernote").summernote({
		   height:300,
		   callbacks : {
			   onImageUpload : function(files) {
				   for(let i=0;i < files.length;i++) {
					   sendFile(files[i]);
				   }
			   }
		   }
	   })
   })
   function sendFile(file) {
	   let data = new FormData();
	   data.append("file",file);
	   $.ajax({
		   url : "${path}/board/uploadImage",
		   type : "post",
		   data : data,
		   processData : false,
		   contentType : false,
		   success : function(url) {
			   $("#summernote").summernote("insertImage",url);
		   },
		   error : function(e) {
			   alert("이미지 업로드 실패 :" + e.status);
		   }
	   })
   }
</script>

</body></html>