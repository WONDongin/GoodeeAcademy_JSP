<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- /webapp/layout/layout.jsp --%>
<c:set var="path" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property="title" /></title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
 
 
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  /* Footer 관련 스타일 */
  .footer{
    display:flex;
    flex-direction:column;
   }
   .footer_link{height:15%; display:flex; align-items:center;}
   .footer_link a{text-decoration:none; color:black; font-weight:bold; margin:15px;}
   .footer_company{height:70%;}
   .footer_company>ul{list-style:"- "; padding-left:15px;}
   .footer_copyright{height:15%; text-align:center}     
   .footer>div{border-top:1px solid gray}
  </style>
  <sitemesh:write property="head" />  
</head>
<body>

<div class="jumbotron text-center" style="margin-bottom:0">
  <h1>관리자페이지</h1>
  <p>SSEUL V1.6</p> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#">SSEUL</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse d-flex justify-content-between" id="collapsibleNavbar">
    <ul class="navbar-nav ">
      <li class="nav-item">
        <a class="nav-link" href="${path}/member/main">마이페이지</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${path}/board/list?boardid=1">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${path}/board/list?boardid=2">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${path}/book/bookList">방명록</a>
      </li>
    </ul>
    <ul class="navbar-nav">
      <c:if test="${sessionScope.login == null }">
      <li class="text-end">
        <a class="nav-link" href="${path}/member/loginForm">로그인</a>
      </li>    
      <li class="text-end">
        <a class="nav-link" href="${path}/member/joinForm">회원가입</a>
      </li>    
      </c:if>
      <c:if test="${sessionScope.login != null }">
      <li>
        <a class="nav-link text-success" href="#">
        ${sessionScope.login}님 반갑습니다.</a>
      </li>    
      <li>
        <span><a class="nav-link" href="${path}/member/logout">로그아웃</a></span>
      </li>    
      </c:if>
    </ul>
  </div>  
</nav>

<div class="container" style="margin-top:30px">

<%-- 
1차트 파이(작성자별 게시물 등록 건수: 가장 많이 작성한 5명 작성자), 
2차트 막대(최근 7일간 작성일자별 게시물 등록 건수) 
--%>
<div class="row">
	<div class="col" style="border:1px solid #eeeeee">
		<canvas id="canvas1" style="width:100%"></canvas>
	</div>
	<div class="col" style="border:1px solid #eeeeee">
		<canvas id="canvas2" style="width:100%"></canvas>
	</div>
</div>

<sitemesh:write property="body" />  
</div>
<footer class="footer">
	<div>
	    <span id="si">
	        <select name="si" onchange="getText('si')">
	            <option value="">시도를 선택하세요</option>
	        </select>
	    </span>
	    <span id="gu">
	        <select name="gu" onchange="getText('gu')">
	            <option value="">구군을 선택하세요</option>
	        </select>
	    </span>
	    <span id="dong">
	        <select name="dong" onchange="getText('dong')">
	            <option value="">동리를 선택하세요</option>
	        </select>
	    </span>
	</div>
	<div class="footer_link">
	    <a href="">이용약관</a> |
	    <a href="">개인정보취급방침</a> |
	    <a href="">인재채용</a> |
	    <a href="">고객센터</a>
	</div>
  <div class="footer_company">
   <ul>
      <li>상호명 : GooDee Academy</li>
      <li>대표자 : 이승엽</li>
      <li>전화 : 02-818-7950</li>
      <li>개인정보책임자 : 주승재 / jsj@goodee.co.kr</li>
      <li>(08505) 서울특별시 금천구 가산디지털2로 95
           (가산동, km타워) 2층, 3층</li>
   </ul>
  </div>
  <div class="footer_copyright">
     Copyright ⓒ GooDee Academy. All rights reserved.
  </div>
 </footer>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
<script type="text/javascript">
    $(function() {
		piegraph(); // 작성자별 게시물 등록 건수 파이 그래프로 구현
		bargraph();
		
        // ajax을 이용하여 시도 데이터 조회
        $.ajax({
            url: "${path}/ajax/select?level=si",
            success: function(data) {
                let arr = JSON.parse(data);
                $.each(arr, function(i, item) {
                    $("select[name=si]").append("<option>" + item + "</option>");
                });
            },
            error: function(e) {
                alert("서버 오류: " + e.status);
            }
        });
    });

    function getText(divid) {
        let selectedSi = $("select[name=si]").val();
        let selectedGu = $("select[name=gu]").val();

        let level = "";
        let param = "";

        if (divid === "si") {
            level = "gu";
           	// encodeURIComponent 생략가능
            param = "si=" + encodeURIComponent(selectedSi);
        } else if (divid === "gu") {
            level = "dong";
            param = "si=" + encodeURIComponent(selectedSi) + "&gu=" + encodeURIComponent(selectedGu);
        } else {
            return;
        }

        $.ajax({
            url: "${path}/ajax/select?level=" + level + "&" + param,
            success: function(data) {
				// 데이터 받기
                let arr = JSON.parse(data);
                let target = "select[name=" + level + "]";
                $(target).empty().append("<option value=''>" + (level === "gu" ? "구군" : "동리") + "을 선택하세요</option>");
                $.each(arr, function(i, item) {
                    $(target).append("<option>" + item + "</option>");
                });
            },
            error: function(e) {
                alert("서버 오류: " + e.status);
            }
        });
    }
    
    function piegraph(){
		$.ajax("${path}/ajax/graph1",{
			success : function(data){
				// data :  [{"cnt":4,"writer":"원동인"},{"cnt":3,"writer":"테스트"},...]
				pieGraphPrint(data);
			},
			error : function(e){
				alert("서버오류:" + e.status)
			}
		})
		
    }
    
    function bargraph(){
		$.ajax("${path}/ajax/graph2",{
			success : function(data){
				barGraphPrint(data);
			},
			error : function(e){
				alert("서버오류:" + e.status)
			}
		})
		
    }
    
    
    function pieGraphPrint(data){
		let rows = JSON.parse(data); // 서버에서 JSON 형태로 데이터 전송
		let writers = [] // 작성자 목록. 라벨값
		let datas = []   // 파이 데이터 값
		let colors = []  // 색상값
		// rows : data 배열, item : {"cnt":4,"writer":"원동인"}
		$.each(rows,function(i,item){
			writers[i] = item.writer; //[원동인,테스트...]
			datas[i] = item.cnt       //[4,3...]
			colors[i] = randomColor(1);
		})
		
		let config = {
			type: 'pie',
			data : {
				datasets : [{
					data : datas,
					backgroundColor : colors
				}],
				labels : writers
			},
			
			options : {
				// 반응형
				responsive : true,
				legend : {position:"bottom"},
				title : {
					display:true,
					text : '최근 7일간 작성일자별 게시물 등록 건수',
					position:"bottom"
				}
			}
		}
		let ctx = document.querySelector("#canvas1");
		new Chart(ctx,config)
    }
    
    
    // 차트 2
    function barGraphPrint(data){
		let rows = JSON.parse(data); // 서버에서 JSON 형태로 데이터 전송
		let todays = [] // 작성자 목록. 라벨값
		let datas = []   // 파이 데이터 값
		let colors = []  // 색상값

		$.each(rows,function(i,item){
			todays[i] = item.today; //[원동인,테스트...]
			datas[i] = item.cnt       //[4,3...]
			colors[i] = randomColor(1);
		})
		
		let config = {
			type: 'bar',
			data : {
				datasets : [{
					data : datas,
					backgroundColor : colors
				}],
				labels : todays
			},
			
			options : {
				// 반응형
				responsive : true,
				legend : {position:"bottom", display:false},
				title : {
					display:true,
					text : '게시글 작성자별 등록 건수(최대 5명)',
					position:"bottom"
				}
			}
		}
		let ctx = document.querySelector("#canvas2");
		new Chart(ctx,config)
		console.log(todays)
    }
    
    
 	// 색상 랜덤(0~255)
	function randomColorFactor(){
	  	return Math.round(Math.random() * 255)
	}
	
	// rgba(100,200,300,1)
	function randomColor (opa) {
	  return "rgba(" + randomColorFactor() + "," 
	         + randomColorFactor() + "," 
	         + randomColorFactor() + "," 
	         + (opa || ".3") + ")";
	}
    
    
</script>

</body>
</html>