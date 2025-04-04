package ex01_lifecycle;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
Servlet implementation class HelloServlet
1. ex01_lifecycle 패키지생성
2. new > servelet
   servlet name : HelloServlet
   
1번째 실행 결과 ==
생성자 호출
init 메서드 호출
service 메서드 호출
doGet 메서드 호출

== 2번째 새로고침 실행 결과 ==
(객체가 어딘가에 저장이 되었으므로 앞에 2개 생략가능)
service 메서드 호출
doGet 메서드 호출
*/

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
/**
1. 생성자
1) 가장먼저 호출
2) 한번 생성된 객체는 재사용됨
3) 생성자 호출 후 init 메서드 호출
*/
    public HelloServlet() {
        super();
        System.out.println("생성자 호출");
    }
    
/**
2. init()
1) 서블릿 환경 설정 담당
2) init() 메서드 호출 후 service() 메서드 호출
*/
	@Override
	public void init(ServletConfig config) throws ServletException {
		// config 객체 : 서블릿의 환경 설정값을 저장하고 있는 객체
		System.out.println("init 메서드 호출");
		super.init(config);
	}

/**
3. service()
1) 클라이언트의 요청 시 호출되는 메서드
2) 클라이언트의 요청 처리
3) 요청 방식(Get,Post) 방식에 따라 doGet, doPost 메서드 호출함
4) service() 메서드를 구현하지 않으면, doGet, doPost 메서드가 호출됨
*/
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resq) throws ServletException, IOException {
		 /**
		 req : 요청객체. request  객체
		 resp: 응답객체. response 객체 
		 */
		 System.out.println("service 메서드 호출");
		 switch(req.getMethod()) {
		 case "GET" : doGet(req,resq); break;
		 case "POST" : doPost(req,resq); break;
		 }
	}

/**
4. doGet()
1) Get 방식 요청 처리하는 메서드
2) Get 방식 요청
2-1) <a href="http://localhost:8080/setvletStudy/HelloServlet">
2-2) <form action="http://localhost:8080/setvletStudy/HelloServlet"
2-3) location.href="http://localhost:8080/setvletStudy/HelloServlet"
2-4) open("http://localhost:8080/setvletStudy/HelloServlet","",op)
2-5) $.ajax({
	type : "GET",
	url  : "http://localhost:8080/setvletStudy/HelloServlet"
	})
*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request  : 요청객체. 클라이언트로 부터 전달 받은 정보 저장 
		// response : 응답객체. 클라이언트에 전달할 정보 저장
		System.out.println("doGet 메서드 호출");
		// PrintWriter getWriter() : 문자형 출력스트림
		// append(), write(), print(), println()
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
/**
5. doPost()
1) POST 방식의 요청을 처리하는 메서드
2) POST 방식의 요청 방법
2-1) <form action="http://localhost:8080/setvletStudy/HelloServlet"
2-2) $.ajax({
	 type : "GET",
	 url  : "http://localhost:8080/setvletStudy/HelloServlet"
	 })
*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost 메서드 호출");
		doGet(request, response);
	}
}
