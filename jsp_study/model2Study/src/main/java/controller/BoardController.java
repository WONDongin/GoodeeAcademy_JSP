package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.board.Board;
import model.board.BoardDao;
import model.member.Member;

@WebServlet(urlPatterns = {"/board/*"},
		initParams = {@WebInitParam(name = "view", value = "/view/")})

public class BoardController extends MskimRequestMapping {
	private BoardDao dao = new BoardDao();
	
	@RequestMapping("write")
	public String write(HttpServletRequest request, HttpServletResponse response) {
		// 파일업로드 되는 폴더 설정
		String path = request.getServletContext().getRealPath("/") + "/upload/board/";
		File f = new File(path);
		// 해당되는 폴더가 없을경우 폴더 생성
		if(!f.exists()) f.mkdirs();
		// mkdir() : 1dept 폴더만 생성
		// mkdirs() : 2dept 폴더만 생성
		
		int size = 10*1024*1024; // 10M. 업로드파일의 최대 크기
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request, path, size, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 파라미터값 저장
		Board board = new Board();
		board.setWriter(multi.getParameter("writer"));
		board.setPass(multi.getParameter("pass"));
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		board.setFile1(multi.getFilesystemName("file1")); // 업로드된 파일이름
		String boardid = 
				(String) request.getSession().getAttribute("boardid");
		if(boardid == null) boardid ="1"; // 1:공지사항 기본 게시판 설정
		board.setBoardid(boardid); // 게시판 종류 : 1:공지사항, 2:자유게시판
		
		if(board.getFile1() == null) board.setFile1(""); //업로드 파일이 없는경우
		int num = dao.maxnum(); // 등록된 게시글의 최대 num값
		board.setNum(++num);    // 게시글키값. 게시글번호.
		board.setGrp(num);      // 그룹번호. 원글인 경우 그룹번호와 게시글 번호가 같다.
		
		String msg = "게시글 등록 실패";
		String url = "writeForm";
		if(dao.insert(board)) { // 게시글 등록성공(화면입력된 정보 = board에 저장)
			return "redirect:list?boardid=" + boardid;
		}
		// 게시글 등록 실패
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		return "alert";
	}
	
	/* 게시글 리스트 ========================================================================
	1. 한 페이지장 10건의 게시글 출력하기.
	   pageNum 파라미터 값 => 없는 경우 1로 설정
	   boardid 파라미터 값 => 있는 경우 session에 boardid 값으로 등록(공지사항 or 자유게시판)
	2. 최근 등록된 게시물이 가장 위쪽에 출력함
	3. DB에서 해당 페이지에 출력될 내용만 조회하여 화면에 출력
	*/
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		// pageNum 파라미터가 존재하면 파라미터값을 pageNum 변수 저장
		// pageNum 파라미터가 없으면 파라미터값을 pageNum 변수는 1로 저장
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
			// boardid 파라미터값 
			String boardid = request.getParameter("boardid");
			if(boardid == null || boardid.trim().equals("")) {
				boardid ="1"; // boardid 파라미터가 없는 경우 "1" 설정
			} 
			// boardid 값을 session에 등록
			request.getSession().setAttribute("boardid", boardid);
			boardid = (String) request.getSession().getAttribute("boardid");
			
			int limit = 10; // 페이지당 10건의 게시글
			int boardcount = dao.boardCount(boardid); // 전체 등록된 게시글 건수
			// pageNum에 해당하는 게시물 목록을 최대 10건를 db에서 조회
			List<Board> list = dao.list(boardid,pageNum,limit); // list 안에 10건 등록
			/*
			maxpage : 필요한 페이지의 갯수
			- 게시물 건수:3  (3.0 / 10) => 0.5+0.95   => (int)(1.25) -> maxpage:1
			- 게시물 건수:10 (10.0 / 10) => 1+0.95    => (int)(1.95) -> maxpage:1
			- 게시물 건수:12 (12.0 / 10) => 1.2+0.95  => (int)(2.15) -> maxpage:2
			- 게시물 건수:500 (500.0 / 10) => 50+0.95 => (int)(50.95) -> maxpage:50
			*/
			int maxpage = (int)((double) boardcount / limit + 0.95);
			/*
			startpage : 화면에 출력되는 시작 페이지
			현재페이지(pageNum): 1 (1 /10.0) => 0.1 + 0.9 => (int)(1.0 - 1) * 10 => 0 + 1    => 페이지의 시작번호 : 1
			현재페이지(pageNum): 10 (10 /10.0) => 1.0 + 0.9 => (int)(1.9 - 1) * 10 => 0 + 1  => 페이지의 시작번호 : 1
			현재페이지(pageNum): 11 (11 /10.0) => 1.1 + 0.9 => (int)(2.0 - 1) * 10 => 10 + 1 => 페이지의 시작번호 : 11
			*/
			int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
			// endpage : 화면에 출력한 마지막 페이지번호. 한 화면에 10개의 페이지 번호를 출력함
			int endpage = startpage + 9;
			// endpage는 maxpage보다 작거나 같아야함
			if(endpage > maxpage) endpage = maxpage;
			// boardnum : 보여주기 위한 번호 
			int boardnum = boardcount - (pageNum - 1) * limit;
			// 페이지네이션 끝
			
			String boardName = "공지사항";
			if(boardid.equals("2")) boardName = "자유게시판";
			request.setAttribute("boardName", boardName);   // 게시판 이름
			request.setAttribute("boardcount", boardcount); // 게시판 종류별 전체 게시물 건수
			request.setAttribute("boardid", boardid);       // 게시판 코드 
			request.setAttribute("pageNum", pageNum);		// 현재 페이지
			request.setAttribute("list", list);				// 현재 페이지에 출력할 게시물 목록
			request.setAttribute("startpage", startpage);   // 페이지 시작 번호
			request.setAttribute("endpage", endpage);       // 페이지 마지막 번호
			request.setAttribute("maxpage", maxpage);       // 페이지 최대 번호
			request.setAttribute("boardnum", boardnum);     // 게시글 번호
			return "board/list"; // 데이터를 가지고 list 페이지로 이동
		
	}
	
	/*
	1. num 파라미터 저장
	2. num의 게시물을 db에서 조회
	- Board b = BoardDao.selectOne(num)
	3. 게시물의 조회수를 증가 시키기
	- BoardDao.readcntAdd(num)
	4. 조회된 게시물 화면에 전달
	*/
	@RequestMapping("info")
	public String info(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Board b = dao.selectOne(num);
		
		if(b != null) {
			dao.readcntAdd(num);
			request.setAttribute("b", b);
			return "board/info";
		}
		request.setAttribute("msg","게시글 조회 실패");
		request.setAttribute("url","redirect:board/list");
		return "alert";
	}
}
