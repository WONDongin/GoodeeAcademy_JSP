package ex15_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action {
	public ActionForward hello(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("hello", "Hello World");
		return new ActionForward(false,"ex15_model2/hello.jsp");
	}
	
	public ActionForward loginForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("id", "admin"); // id 속성값 : admin
		return new ActionForward(false,"ex15_model2/loginForm.jsp");
	}
}
