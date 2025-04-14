<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 전 화면 조회</title>
</head>
<body>
<form action="update" method="post" onsubmit="return input_check(this)" name="f">
<input type="hidden" name = "picture" value="${mem.picture}">
<table>
<tr><td rowspan="4" valign="bottom">
  <img src="../picture/${mem.picture}" width="100" height="120" id="pic"><br>
  <font size="1"><a href="javascript:win_upload()">사진수정</a></font>
</td><th>아이디</th>
<%-- 
disabled="disabled" : 파라미터로 전송 안됨
readonly : 값 수정 불가. 파라미터 전송 가능 
--%>
     <td><input type="text" value="${mem.id}" name="id" readonly></td></tr>
<tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
<tr><th>이름</th><td><input type="text" name="name" value="${mem.name}"></td></tr>
<tr><th>성별</th><td>
<input type="radio" name="gender" value="1" ${mem.gender == 1?"checked":""}>남
<input type="radio" name="gender" value="2" ${mem.gender == 2?"checked":""}>여
</td></tr>
<tr><th>전화번호</th><td colspan="2">
<input type="text" name="tel" value="${mem.tel}"></td></tr>
<tr><th>이메일</th><td colspan="2">
<input type="text" name="email" value="${mem.email}"></td></tr>
<tr><td colspan="3"><button>회원수정</button>

<c:if test="${param.id == sessionScope.login}">
	<button type="button" onclick="win_passchg()">비밀번호수정</button>
</c:if>
</td></tr>
</table></form>

<script type="text/javascript">
   function  inputcheck(f) {
       if(f.pass.value == "") {
		   alert("비밀번호를 입력하세요");
		   f.pass.focus();
		   return false;
	   }
       return true;
   }   
   function win_passchg() {
	  var op = "width=500, height=250, left=50,top=150";
	  open("passwordForm","",op);
   }
   function win_upload(){
	   var op = "width=500,height=150,left=50, top=150";
	   open("pictureForm","",op);
   }
</script>
</body></html>