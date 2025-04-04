package ex03_parameter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String model = request.getParameter("model");
		String price = request.getParameter("price");
		PrintWriter pw = response.getWriter();
		// pw.print("모델명: " + model + ", 가격: " + price);
		pw.print("<html><head><title>파라미터연습</title><body>");
		pw.print("<table border='1'><tr><th>모델</th><th>가격</th></tr>");
		pw.print("<tr><td>"+model+"</td><td>"+price+"</td></tr>");
		pw.print("</table></body></html>");
	}

}
