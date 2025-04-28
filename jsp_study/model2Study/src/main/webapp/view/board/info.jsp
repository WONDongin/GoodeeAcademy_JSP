<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>게시글 상세 보기</title>
	</head>
	<body>
		<h2 class="">${boardName}</h2>
		<table class="table">
			<tr>
				<th width="20%">글쓴이</th>
				<td width="80%" style="text-align: left">${b.writer }</td>
			</tr>
			<tr>
				<th>제목</th><td style="text-align: left">${b.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<table style="width:100%; height:250px;">
						<tr>
							<td style="border-width:0px; vertical-align:top; text-align:left; margin:0px;padding:0px">
								${b.content}
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${empty b.file1 }">&nbsp;</c:if>
					<c:if test="${!empty b.file1 }">
						<a href="../upload/board/${b.file1}">${b.file1}</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a class="btn btn-dark" href="replyForm?num=${b.num }">답변</a>
					<a class="btn btn-dark" href="updateForm?num=${b.num }">수정</a>
					<a class="btn btn-dark" href="deleteForm?num=${b.num }">삭제</a>
					<a class="btn btn-dark" href="list?boardid=${b.boardid}">목록</a>
				</td>
			</tr>
		</table>
		<%-- 댓글 등록,조회,삭제 --%>
		<span id="comment"></span>
		<form action="comment" method="post">
			<input type="hidden" name="num" value="${b.num}"><%-- 게시물번호 --%>
			<div class="row">
				<div class="col-3 text-center">
					<p>작성자:<input type="text" name="writer" class="form-control"></p>
				</div>
				<div class="col-7 text-center">
					<p>내용:<input type="text" name="content" class="form-control"></p>
				</div>
				<div class="col-2 text-center">
					<p><button type="submit" class="btn btn-primary">댓글등록</button></p>
				</div>
			</div>
		</form>
		<div class="container">
			<table class="table">
				<c:forEach var="c" items="${commlist}">
					<tr>
						<td>${c.seq}</td>
						<td>${c.writer}</td>
						<td>${c.content}</td>
						<td><fmt:formatDate value="${c.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td align="right">
							<a class="btn btn-danger" href="commdel?num=${param.num}&seq=${c.seq}">삭제</a>
						</td>
					</tr> 
				</c:forEach>
			</table>
		</div> 
	</body>
</html>