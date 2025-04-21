package controller;

import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.book.Book;
import model.book.BookDao;
// http://localhost:8080/model2Study/book/bookForm
@WebServlet(urlPatterns= {"/book/*"},
initParams= {@WebInitParam(name="view",value="/view/")})
public class BookController extends MskimRequestMapping{
	BookDao dao = new BookDao();
	
	@RequestMapping("bookwrite")
	public String test1(HttpServletRequest request ,
			HttpServletResponse response ) {
	    Book book = new Book();
	    book.setWriter(request.getParameter("writer"));
	    book.setTitle(request.getParameter("title"));
	    book.setContent(request.getParameter("content"));
	    if(dao.insert(book)) {
			return "redirect:bookList";
	    } else {
	    	request.setAttribute("msg", "방명록 등록시 오류 발생");
	    	request.setAttribute("url", "bookForm");
	    	return "alert";
	    }
	}
	@RequestMapping("bookList")
	public String bookList(HttpServletRequest request ,
			HttpServletResponse response ) {
	    List<Book> list = dao.list();  
	    request.setAttribute("list",list);
        return "book/bookList"; //view로 forward 함.
                                //view : /webapp/view/book/bookList.jsp
	}
	
	
}