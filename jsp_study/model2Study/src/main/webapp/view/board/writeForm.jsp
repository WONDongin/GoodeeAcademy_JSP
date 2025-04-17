<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>게시글 등록</title>
	</head>
	<body>
		<form action="write" method="post" enctype="multipart/form-data" name="f">
			<h2 class="text-center">게시글 글쓰기</h2>
			<table class="table">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writer" class="form-control"></td>
				</tr>
				
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pass" class="form-control"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" class="form-control"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" name="content" class="form-control" id="content"></textarea></td>
				</tr>
				<tr><td>첨부파일</td><td><input type="file" name="file1"></td></tr>
				<tr>
					<td colspan="2"  align="center" >
						<a href="javascript:inputcheck()" class="btn btn-dark">게시물 등록</a>
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			function inputcheck(){
				f = document.f;
				if(f.writer.value == ""){
					alert("글쓴이를 입력하세요");
					f.writer.focus();
					return;
				}
				if(f.pass.value == ""){
					alert("비밀번호를 입력하세요");
					f.pass.focus();
					return;
				}
				if(f.title.value== ""){
					alert("제목을 입력하세요");
					f.title.focus();
					return;
				}
				f.submit(); // submit 발생 => fromd의 action 페이지로 요청
			}
		</script>
	</body>
</html>