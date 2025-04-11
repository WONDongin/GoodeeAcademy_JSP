<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴화면</title>
</head>
<body>
<form action="delete" method="post" onsubmit="return input_check(this)">
  <input type="hidden" name="id" value="${param.id}" >
  <table><caption>회원비밀번호입력</caption>
     <tr><th>비밀번호</th>
     <td><input type="password" name="pass" ></td></tr>
     <tr><td colspan="2"><button>탈퇴하기</button>
     </td></tr>
  </table></form></body></html>