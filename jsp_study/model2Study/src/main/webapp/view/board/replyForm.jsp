<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/view/board/replyForm.jsp --%>    
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
					<tr>
						<th>글쓴이</th>
				    	<td><input type="text" name="writer" class="form-control"></td>
				    </tr>
					<tr>
						<th>비밀번호</th>
				    	<td><input type="password" name="pass" class="form-control"></td>
				    </tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title"  class="form-control" value="RE:${board.title}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" rows="15"  class="form-control" id="content"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
				 			<a href="javascript:document.f.submit()">[답변글등록]</a>
				 		</td>
				 	</tr>    
				</table>
			</div>
		</form>
	</body>
</html>