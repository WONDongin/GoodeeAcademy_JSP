package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gdu.mskim.MSLogin;
import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.board.Board;
import model.board.BoardDao;

@WebServlet(urlPatterns= {"/board/*"},
    initParams= {@WebInitParam(name="view",value="/view/")})
public class BoardController extends MskimRequestMapping{
	private BoardDao dao = new BoardDao();
	
	public String noticecheck (HttpServletRequest request,
			HttpServletResponse response) {
		String boardid = (String)request.getSession().getAttribute("boardid");
		if(boardid == null) boardid = "1";
		String login = (String)request.getSession().getAttribute("login");
		if(boardid.equals("1")) { //공지사항
			if(login == null || !login.equals("admin")) {
				request.setAttribute("msg", "공지사항은 관리자만 가능합니다.");
				request.setAttribute("url", 
				request.getContextPath()+"/board/list?boardid=" + boardid);
				return "alert";
			}
		}
		return null;
	}
	@RequestMapping("writeForm")
	@MSLogin("noticecheck")
	public String writeForm(HttpServletRequest request,
			HttpServletResponse response) {
		return "board/writeForm";
	}
	
	@RequestMapping("write")
	@MSLogin("noticecheck")
	public String write(HttpServletRequest request,
			HttpServletResponse response) {
		//파일업로드 되는 폴더설정
	   String path=request.getServletContext().getRealPath("/") +"/upload/board/";
	   File f = new File(path);
	   if(!f.exists()) f.mkdirs(); //폴더 생성.
	   //mkdir()   : 한단계 폴더만 생성
	   //mkdirs()  : 여러단계 폴더만 생성
	   
	   int size=10*1024*1024; //10M. 업로드파일의 최대 크기
	   MultipartRequest multi = null;
	   try {
		   multi = new MultipartRequest(request,path,size,"UTF-8");
	   } catch(IOException e) {
		   e.printStackTrace();
	   }
	   //파라미터값 저장
	   Board board = new Board();
	   board.setWriter(multi.getParameter("writer"));
	   board.setPass(multi.getParameter("pass"));
	   board.setTitle(multi.getParameter("title"));	   
	   board.setContent(multi.getParameter("content"));
	   board.setFile1(multi.getFilesystemName("file1")); //업로드된 파일이름
	   //시스템에서 필요한 정보를 저장
	   String boardid = (String)request.getSession().getAttribute("boardid");
	   if(boardid==null) boardid="1"; //공지사항 기본 게시판 설정
	   board.setBoardid(boardid); //게시판 종류 : 1:공지사항, 2: 자유게시판
	   if(board.getFile1()==null) board.setFile1(""); //업로드 파일이 없는 경우
	   
	   int num = dao.maxnum();  //등록된 게시글의 최대 num값
	   board.setNum(++num); //게시글키값. 게시글번호.
	   board.setGrp(num);   //그룹번호. 원글인 경우 그룹번호와 게시글번호가 같다
	   String msg = "게시물 등록 실패";
	   String url = "writeForm";
	   if(dao.insert(board)) { //게시글 등록 성공
		   return "redirect:list?boardid="+boardid;
	   }
	   //게시글 등록 실패
	   request.setAttribute("msg", msg);
	   request.setAttribute("url", url);
	   return "alert";
	}
	/*
	 * 1. 한페이지당 10건의 게시물 출력하기. 
	 *    pageNum 파라미터값 => 없는 경우 1로 설정
	 *    boardid 파라미터값 => 있는 경우 session에 boardid값으로 등록
	 * 2. 최근 등록된 게시물이 가장 위쪽에 출력함
	 * 3. db에서 해당페이지에 출력될 내용만 조회하여 화면에 출력함
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response) {
		//pageNum 파라미터가 존재하면 파라미터값을 pageNum 변수 저장
		//pageNum 파라미터가 없으면 파라미터값을 pageNum 변수는 1로 저장
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		//boardid 파라미터값
		String boardid = request.getParameter("boardid");
		if(boardid == null || boardid.trim().equals("")) { 
			boardid = "1"; //boardid 파라미터가 없는 경우 "1" 설정
		} 
		//boardid값을 session에등록
		request.getSession().setAttribute("boardid", boardid);
		boardid = (String)request.getSession().getAttribute("boardid");
		
		int limit = 10; //페이지당 출력되는 게시물의 건수
		int boardcount = dao.boardCount(boardid); //등록된 게시물 건수
		//pageNum에 해당하는 게시물목록을 최대 10를 db에서 조회
		List<Board> list = dao.list(boardid,pageNum,limit);
	    int maxpage = (int)((double)boardcount/limit + 0.95);
	    /*  maxpage : 필요한 페이지의 갯수
	     *    게시물건수       maxpage   
	     *       3              1
	     *  3.0/10 => 0.3 + 0.95 => (int)(1.25) => 1
	     *      10              1
	     *  10.0/10 => 1.0 + 0.95 => (int)(1.95) => 1   
	     *      11              2
	     *  11.0/10 => 1.1 + 0.95 => (int)(2.05) => 2    
	     *     500              50
	     *  500.0/10 => 50.0 + 0.95 => (int)(50.95) => 50   
	     *     501              51
	     *  501.0/10 => 50.1 + 0.95 => (int)(51.05) => 51   
	     */
	    int startpage=((int)(pageNum/10.0 + 0.9) - 1) * 10 + 1;
	    /*
	     * startpage : 화면에 출력되는 시작 페이지
	     * 현재페이지(pageNum)    페이지의시작번호
	     *     1                    1
	     *     1/10.0 => 0.1 + 0.9 => (int)(1.0 - 1) * 10 => 0 + 1 => 1
	     *     10                   1
	     *     10/10.0 => 1.0 + 0.9 => (int)(1.9 - 1) * 10 => 0 + 1 => 1
	     *     11                   11
	     *     11/10.0 => 1.1 + 0.9 => (int)(2.0 - 1) * 10 => 10 + 1 => 11
	     *     502                  501 
	     *     502/10.0 => 50.2 + 0.9 => (int)(51.1 - 1) * 10 => 500 + 1 => 501
	     */
	    int endpage = startpage + 9; 
	    // endpage : 화면에 출력한 마지막 페이지번호. 한 화면에 10개의 페이지번호를 출력함
	    // endpage는 maxpage보다 작거나 같아야함	    
	    if(endpage > maxpage) endpage = maxpage; 
	    String boardName = "공지사항";
	    if (boardid.equals("2")) 
			boardName = "자유게시판";
	    request.setAttribute("boardName", boardName);  //게시판이름
	    request.setAttribute("boardcount", boardcount); //게시판 종류별 전체게시물건수
	    request.setAttribute("boardid", boardid);    //게시판코드
	    request.setAttribute("pageNum", pageNum);    //현재페이지
	    request.setAttribute("list", list);          //현재페이지에 출력할 게시물목록
	    request.setAttribute("startpage", startpage);//페이지시작번호
	    request.setAttribute("endpage", endpage);    //페이지마지막번호
	    request.setAttribute("maxpage", maxpage);    //페이지최대번호
	    int boardnum = boardcount - (pageNum - 1) * limit;
	    //boardnum : 보여주기 위한 번호
	    request.setAttribute("boardnum", boardnum);
	    request.setAttribute("today", new Date());
	    return "board/list";
	}
	/*
	 * 1. num 파라미터 저장
	 * 2. num의 게시물을 db에서 조회
	 *    Board b = BoardDao.selectOne(num)
	 * 3. 게시물의 조회수를 증가시키기
	 *    BoardDao.readcntAdd(num)
	 * 4. 조회된 게시물 화면에 전달       
	 */
	@RequestMapping("info")
	public String info(HttpServletRequest request ,HttpServletResponse response) {
	  int num = Integer.parseInt(request.getParameter("num"));
//	  String boardid = (String)request.getSession().getAttribute("boardid");
//	  if(boardid == null) boardid="1";
	  //b : num값의 게시물 데이터 저장
	  Board b = dao.selectOne(num);
      dao.readcntAdd(num);  //조회수 증가
      String boardid = b.getBoardid();
	  String boardName = "공지사항";
	  if(boardid.equals("2")) boardName = "자유게시판";
	  request.setAttribute("b",b);
	  request.setAttribute("boardName",boardName);
	  return "board/info";
	}
	@RequestMapping("replyForm")
	@MSLogin("noticecheck")
	public String replyForm(HttpServletRequest request ,HttpServletResponse response) {
	  int num = Integer.parseInt(request.getParameter("num"));
	  Board b = dao.selectOne(num);
	  request.setAttribute("board",b);
	  return "board/replyForm";
	}
	/*
	 * 1. 파라미터값을 Board 객체에 저장
	 *    원글정보 : num,grp,grplevel, grpstep,boardid 
	 *    답글정보 : writer,pass,title,content => 입력한 내용
	 * 2. 같은 grp에 속하는 게시물들의 grpstep 값을 1 증가시키기   
	 *    void BoardDao.grpStepAdd(grp,grpstep)
	 * 3. Board에 저장된 답글 정보를 db에 추가하기
	 *    num : maxnum + 1
	 *    grp : 원글과 동일
	 *    grplevel : 원글의 grplevel + 1   
	 *    grpstep : 원글의 grpstep + 1
	 *    boardid : 원글과 동일
	 * 4. 추가 성공 : list로 페이지 이동.
	 *    추가 실패 : replyForm 페이지 이동      
	 */
	@RequestMapping("reply")
	@MSLogin("noticecheck")
	public String reply(HttpServletRequest request ,HttpServletResponse response) {
		//1. 파라미터값을 Board 객체에 저장
		Board b = new Board();
		//원글정보
		int num = Integer.parseInt(request.getParameter("num"));
		int grp = Integer.parseInt(request.getParameter("grp"));
		int grplevel = Integer.parseInt(request.getParameter("grplevel"));
		int grpstep = Integer.parseInt(request.getParameter("grpstep"));
		String boardid = request.getParameter("boardid");
		//답글정보
		b.setGrp(grp);
		b.setBoardid(boardid);
		b.setWriter(request.getParameter("writer"));
		b.setPass(request.getParameter("pass"));
		b.setTitle(request.getParameter("title"));
		b.setContent(request.getParameter("content"));
		//2. 같은 grp에 속하는 게시물들의 grpstep 값을 1 증가시키기
		dao.grpStepAdd(grp,grpstep);
		//3. Board에 저장된 답글 정보를 db에 추가하기
		b.setNum(dao.maxnum() + 1);
		b.setGrplevel(grplevel + 1);
		b.setGrpstep(grpstep + 1);
		if(dao.insert(b)) {
			return "redirect:list?boardid=" + boardid;
		} else {
			request.setAttribute("msg", "답변 등록 실패");
			request.setAttribute("url", "replyForm?num=" +num );
			return "alert";
		}
	}
	@RequestMapping("updateForm")
	@MSLogin("noticecheck")
	public String updateForm(HttpServletRequest request ,HttpServletResponse response) {
	  int num = Integer.parseInt(request.getParameter("num"));
	  Board b = dao.selectOne(num);
	  request.setAttribute("b",b);
	  return "board/updateForm";
	}
	/*
	 * 1. 파라미터 정보를 Board 객체에 저장 => request 객체 사용 불가
	 * 2. 비밀번호 검증 : 비밀번호 오류시, 메세지 출력 후 updateForm 페이지 이동
	 * 3. 게시물 수정 boolean BoardDao.update(Board)
	 *   - 첨부파일이 없는 경우 file2의 내용을 다시 저장하기
	 * 4. 수정성공 : info 페이지로 이동
	 *    수정실패 : 수정실패 메시지 출력 후 updateForm 으로 페이지 이동  
	 */
	@RequestMapping("update")
	@MSLogin("noticecheck")
	public String update(HttpServletRequest request ,HttpServletResponse response) {
	   String path=request.getServletContext().getRealPath("/")+"upload/board/";
	   File f = new File(path);
	   if(!f.exists()) f.mkdirs();
	   MultipartRequest multi=null;
	   try {
		 multi = new MultipartRequest(request,path,10*1024*1024,"UTF-8");
	   } catch (IOException e) {
		 e.printStackTrace();
	   }
	   Board board = new Board();
	   board.setNum(Integer.parseInt(multi.getParameter("num")));
	   board.setWriter(multi.getParameter("writer"));
	   board.setPass(multi.getParameter("pass"));
	   board.setTitle(multi.getParameter("title"));
	   board.setContent(multi.getParameter("content"));
	   //첨부파일 수정. 
	   board.setFile1(multi.getFilesystemName("file1"));
	   //첨부파일 수정 안됨
	   if(board.getFile1()==null || board.getFile1().equals("")) {
		   //이전 첨부 파일을 유지
		   board.setFile1(multi.getParameter("file2"));
	   }
	 //2 비밀번호 검증
	   //db에 저장된 내용
	   Board dbBoard = dao.selectOne(board.getNum());
	   String msg = "비밀번호가 틀렸습니다.";
	   String url = "updateForm?num=" + board.getNum();
	   //board.getPass() : 입력된 비밀번호
	   //dbBoard.getPass() : db에 저장된 비밀번호
	   if(board.getPass().equals(dbBoard.getPass())) { //비밀번호 일치
		  if(dao.update(board)) { //db의 게시물 수정 성공
//			  url = "info?num=" + board.getNum();
//			  return "redirect:"+url;
			  return "redirect:info?num=" + board.getNum();
		  } else {                //수정 실패
			  msg = "게시물 수정 실패";
		  }
	   }
	   request.setAttribute("msg", msg);
	   request.setAttribute("url", url);
	   return "alert";
	}
	/*
	 * 1. num,pass 파라미터를 변수에 저장
	 * 2. 비밀번호 검증
	 *     틀린경우 : 메세지 출력, deleteForm 페이지로 이동
	 * 3. 답변글이 있는 원글인 경우 삭제 불가. 
	 *    답변글 삭제후 삭제가능 메세지 출력. list 페이지로 이동    
	 * 4. 게시물 삭제
	 *    boolean BoardDao.delete(num)
	 *    삭제성공 : list로 페이지 이동
	 *    삭제실패 : 삭제 실패 메세지 출력. deleteForm 페이지로 이동       
	 */
	@RequestMapping("delete")
	@MSLogin("noticecheck")
	public String delete(HttpServletRequest request,
			HttpServletResponse response) {
	    int num = Integer.parseInt(request.getParameter("num"));
	    String pass = request.getParameter("pass");
	    Board board = dao.selectOne(num);
	    String msg = "게시글의 비밀번호가 틀렸습니다";
	    String url = "deleteForm?num=" + num;
	    if (pass.equals(board.getPass())) { //비밀번호 일치
	       //답변글이 존재
	       if(board.getGrplevel() == 0) {  //원글
	    	   if (dao.replyCnt(board.getGrp()) > 0) { //답변글 존재
	    		    request.setAttribute("msg", "답변글 삭제 후 삭제 가능합니다."); 
	    			request.setAttribute
	    			          ("url", "list?boardid=" + board.getBoardid()); 
	   				return "alert";
	    	   }
	       }
	       if (dao.delete(num)) {
			    url = "list?boardid=" + board.getBoardid();
			    return "redirect:" + url;
		     } else {  //삭제 실패
			   msg = "게시글 삭제 실패";
		     }
	   }
	   request.setAttribute("msg", msg); 
	   request.setAttribute("url", url); 
		return "alert";
	}
}