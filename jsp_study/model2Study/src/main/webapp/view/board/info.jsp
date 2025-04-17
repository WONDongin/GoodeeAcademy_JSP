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
					<a href="replyForm?num=${b.num }">[답변]</a>
					<a href="updateForm?num=${b.num }">[수정]</a>
					<a href="deleteForm?num=${b.num }">[삭제]</a>
				</td>
			</tr>
		</table>
	</body>
</html>