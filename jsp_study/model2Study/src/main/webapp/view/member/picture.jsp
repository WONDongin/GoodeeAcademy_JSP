<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
/*
opener : 현재 창을 open한 window 객체 => joinForm.jsp 페이지의 window 객체  
*/
	img = opener.document.getElementById("pic"); // <img id="pic"...> 이미지 객체
	// opener 페이지의 이미지 보임
	img.src = "../picture/${fname}"; 
	opener.document.f.picture.value = "${fname}";
	// self : 현재 페이지의 window 객체
	self.close();
</script>