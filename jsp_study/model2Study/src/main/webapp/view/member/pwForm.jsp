<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- /webapp/member/pwForm.jsp
1. 비밀번호 찾기 버튼 클릭시
   3개의 값이 모두 입력 되어야만 pw.jsp 페이지를 요청하도록 자바스크립트 추가하기
--%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
	<h3 align="center">비밀번호찾기</h3>
	<form action="pw" method="post"onsubmit="return input_check(this)">
 		<table class="table">
     		<tr><th>아이디</th><td><input type="text" name="id"  class="form-control"></td></tr>
     		<tr><th>이메일</th><td><input type="text" name="email"  class="form-control"></td></tr>
     		<tr><th>전화번호</th><td><input type="text" name="tel"  class="form-control"></td></tr>
     		<tr><td colspan="2" align="center"><input type="submit" value="비밀번호 찾기"  class="btn btn-primary"></td></tr>
  		</table>	
	</form>

<script type="text/javascript">

	function input_check(f) {
		if(f.id.value.trim() == ""){
			alert("아이디를 입력하세요");
			f.id.focus();
			return false;
		}
		
		if(f.email.value.trim() == ""){
			alert("이메일을 입력하세요");
			f.email.focus();
			return false;
		}
		
		
		if (f.tel.value.trim() == ""){
			alert("전화번호를 입력하세요");
			f.tel.focus();
			return false;
		}
		
		if(!isValidEmail(f.email.value.trim())){
			alert("이메일 형식이 아닙니다.");
			f.email.focus();
			return false;
		}
		
		if (!isValidTel(f.tel.value.trim())){
			alert("전화번호 형식이 아닙니다.");
			f.tel.focus();
			return false;
		}
	}
	
	// 정규식을 이용한 이메일, 전화번호 형식 검증
	function isValidEmail(email){
		const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		/*
			/^ ... $/         => 정규표현식 시작과 끝
			[a-zA-Z0-9._%+-]+ => 소문자 대문자 숫자_% + - 중 문자가 한개 이상
			@                 => @ 문자(무조건)
			[a-zA-Z0-9.-]+    => 소문자 대문자 숫자 . - 중 문자 한개 이상
			\.                => . 문자(무조건)
			[a-zA-Z]{2,}      => 소문자 대문자 2자 이상
		*/
		return regex.test(email); // 정규식표현식 일치 : true 리턴, 불일치 : false 리턴
	}
	function isValidTel(tel){
		const regex = /^(02|01[016789])-\d{3,4}-\d{4}$/;
		/*
			/^ ... $/         => 정규표현식 시작과 끝
			(02|01[016789])   => 02 or 01[016789]0
			-?                => - 있거나/없거나
			\d{3,4}           => 숫자 3자리 또는 4자리
			\d{4}             => 숫자 4자리
		*/
		return regex.test(tel);
	}
</script>

</body>
</html>

