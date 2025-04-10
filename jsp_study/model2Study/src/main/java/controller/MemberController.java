package controller;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.member.Member;
import model.member.MemberDao;

@WebServlet(urlPatterns = {"/member/*"},
initParams = {@WebInitParam(name="view",value="/view/")})

public class MemberController extends MskimRequestMapping{
	private MemberDao dao = new MemberDao();
	/*
	1. 파라미터 정보를 Member 객체에 저장. 인코딩필요(Filter 이용)
	2. Member 객체를 이용하여 db에 insert (member 테이블) 저장
	3.   
	*/
	
	@RequestMapping("join") // http://localhost:8080/model2Study/member/join
	public String join(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));
		
		if(dao.insert(mem)) {
			request.setAttribute("msg", mem.getName()+"님 회원 가입 되었습니다.");
			request.setAttribute("url", "loginForm");
		} else {
			request.setAttribute
			("msg", mem.getName()+"님 회원가입시 오류 발생했습니다.");
			request.setAttribute("url", "joinForm");
		}
		return "alert"; 
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
			
		// 파라미터 조회
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		
		// db 정보비교
		Member mem = dao.selectOne(id);
		
		if(mem == null){ // 아이디가 없는 경우
			request.setAttribute("msg", "아이디를 확인하세요");
		} else { 
			if(pass.equals(mem.getPass())) { // 비밀번호 비교. 정상적인 로그인
				session.setAttribute("login", id);
				request.setAttribute("msg", mem.getName()+"님 로그인 하셨습니다.");
				request.setAttribute("url", "main");
			}else{ // 비밀번호 오류
				request.setAttribute("msg", "비밀번호가 틀렸습니다.");
			}
		}
		return "alert";
	}
	
}
