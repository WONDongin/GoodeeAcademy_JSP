package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.board.BoardDao;

@WebServlet(urlPatterns= {"/ajax/*"},
initParams= {@WebInitParam(name="view",value="/view/")})

public class AjaxController extends MskimRequestMapping{
	@RequestMapping("select")
	public String select(HttpServletRequest request, HttpServletResponse response) {
	    String level = request.getParameter("level");
	    String si = request.getParameter("si");
	    String gu = request.getParameter("gu");

	    BufferedReader fr = null;
	    // path : sidi.txt 파일의 절대 경로
	    String path = request.getServletContext().getRealPath("/") + "file/sido.txt";
	    // LinkedHashSet : 중복불가. 순서유지. 인덱스 사용불가
	    Set<String> set = new LinkedHashSet<>();

	    try {
	        fr = new BufferedReader(new FileReader(path));
	        String data;
	        while ((data = fr.readLine()) != null) {
	        	// \\s+ : 정규식표현. 공백한개 이상
	            String[] arr = data.split("\\s+");
	            if (arr.length < 3) continue;

	            switch (level) {
	                case "si":
	                    set.add(arr[0].trim());
	                    break;
	                case "gu":
	                	// arr[0].equals(si) : 시도와 선택한 값이 같은 경우
	                    if (arr[0].equals(si)) set.add(arr[1].trim());
	                    break;
	                case "dong":
	                	if (arr[0].equals(si) && arr[1].equals(gu)) {
	                        String dong = arr[2].trim();
	                        // 구군과 이름이 같은 경우 제외
	                        if (!dong.equals(arr[1].trim())) {
	                            set.add(dong);
	                        }
	                    }
	                    break;
	            }
	        }
	        fr.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    request.setAttribute("list", new ArrayList<>(set));
	    request.setAttribute("len", set.size());

	    // select.jsp 호출 (문자열 배열 형태로 출력)
	    return "ajax/select";
	}
	
	// 차트 1
	// http://localhost:8080/model2Study/ajax/graph1
	@RequestMapping("graph1")
	public String graph1(HttpServletRequest request, HttpServletResponse response){
	    // [{"cnt":4,"writer":"원동인"},{"cnt":3,"writer":"테스트"},...]
		BoardDao dao = new BoardDao();
	    List<Map<String,Object>> list = dao.boardgraph1();
	    // 배열시작 점
	    StringBuilder json = new StringBuilder("[");
	    int i=0;
	    for(Map<String, Object> m : list) {
	        for(Map.Entry<String,Object> me : m.entrySet()) {
	        	// m : {cnt:4,writer:원동인}
	            if(me.getKey().equals("cnt"))
	            	// "cnt":4,
	                json.append("{\"cnt\":"+me.getValue()+",");
	            if(me.getKey().equals("writer"))
	            	// "writer":"원동인"}
	                json.append("\"writer\":\""+me.getValue()+"\"}");
	        }
	        // 마지막 요소 , 삭제처리
	        i++;
	        if(i < list.size()) json.append(",");
	    }
	    json.append("]");
	    request.setAttribute("json", json.toString().trim());
	    return "ajax/graph";
	}
	
	// 차트 2
	// http://localhost:8080/model2Study/ajax/graph2
	@RequestMapping("graph2")
	public String graph2(HttpServletRequest request, HttpServletResponse response){
		BoardDao dao = new BoardDao();
	    List<Map<String,Object>> list = dao.boardgraph2();
	    // 배열시작 점
	    StringBuilder json = new StringBuilder("[");
	    int i=0;
	    for(Map<String, Object> m : list) {
	        for(Map.Entry<String,Object> me : m.entrySet()) {

	            if(me.getKey().equals("today"))
	                json.append("{\"today\":\""+me.getValue()+"\",");
	            if(me.getKey().equals("cnt"))

	                json.append("\"cnt\": " +me.getValue()+"}");
	        }
	        // 마지막 요소 , 삭제처리
	        i++;
	        if(i < list.size()) json.append(",");
	    }
	    json.append("]");
	    request.setAttribute("json", json.toString().trim());
	    return "ajax/graph2";
	}
	
	

	
	
	
	
}