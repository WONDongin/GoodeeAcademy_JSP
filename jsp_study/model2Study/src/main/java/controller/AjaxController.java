package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.board.BoardDao;

@WebServlet(urlPatterns= {"/ajax/*"},
 initParams= {@WebInitParam(name="view",value="/view/")})
public class AjaxController extends MskimRequestMapping{
  //@RequestMapping("select")
  public String select (HttpServletRequest request,HttpServletResponse response) {
	BufferedReader fr = null;
	//path : sido.txt 파일의 절대 경로
	String path =request.getServletContext().getRealPath("/")+ "file/sido.txt";
	try {
		fr = new BufferedReader(new FileReader(path));
	}catch (IOException e) {
		e.printStackTrace();
	}
	//LinkedHashSet : 중복불가. 순서유지. 인덱스사용불가 
	Set<String> set = new LinkedHashSet<>();
	String data = null;
	String si = request.getParameter("si"); //구군설정
	String gu = request.getParameter("gu"); //동리설정
	if(si == null && gu==null) { //시도 설정. 초기상태
		try {
		  while((data=fr.readLine()) != null) {
			  // \\s+ : 정규식표현. 공백한개이상
			  String[] arr = data.split("\\s+");
			  if(arr.length >=3) set.add(arr[0].trim());
		  }
		} catch(IOException e) {
			e.printStackTrace();
		}
	} else if (gu == null) { //si파라미터값은 null이 아님. 시도선택=>구군설정
		si = si.trim();
		try {
			while((data=fr.readLine()) != null) {
				String[] arr = data.split("\\s+");
				//arr[0].equals(si) : 시도와 선택한값이 같은 경우
				if(arr.length >=3 && arr[0].equals(si) &&
						!arr[1].contains(arr[0]))
					set.add(arr[1].trim()); //구군 정보 설정
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	} else if (gu != null && si!=null) {
		si = si.trim();
		gu = gu.trim();
		try {
			while((data=fr.readLine()) != null) {
				String[] arr = data.split("\\s+");
				if(arr.length >=3 && arr[0].equals(si) &&
					arr[1].equals(gu) &&
					!arr[1].contains(arr[0]) &&
					!arr[2].contains(arr[1])) {
					if(arr.length > 3) {
						if(arr[3].contains(arr[1])) continue;
						arr[2] += " " + arr[3];
					}								
					set.add(arr[2].trim()); //동리 정보 설정
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	request.setAttribute("list", new ArrayList<String>(set));
	request.setAttribute("len", set.size());
	return "ajax/select";
  }
  @RequestMapping("select")
  public String select2 (HttpServletRequest request,HttpServletResponse response) {
	BufferedReader fr = null;
	String path =request.getServletContext().getRealPath("/")+ "file/sido.txt";
	try {
		fr = new BufferedReader(new FileReader(path));
	}catch (IOException e) {
		e.printStackTrace();
	}
	final String si = request.getParameter("si"); //구군설정
	final String gu = request.getParameter("gu"); //동리설정
	List<String> list = null;
	if(si == null && gu==null) { //시도 설정	
	  list = fr.lines().filter(s->s.split("\\s+").length >=3)
		.map(s->{
			String[] arr = s.split("\\s+");
			return arr[0];
		}).distinct().collect(Collectors.toList());
	} else if (gu == null) { //si파라미터값은 null이 아님
	    list = fr.lines().filter(s->{
	    	String arr[] = s.split("\\s+");
	    	return arr.length >=3 && arr[0].equals(si.trim()) &&
					!arr[1].contains(arr[0]);  
	         })	
	    	.map(s->{
   			   String[] arr = s.split("\\s+");
	    			return arr[1].trim();
	    	 })
	    	.distinct().collect(Collectors.toList());
	} else if (gu != null && si!=null) {
	    list = fr.lines().filter(s->{
	    	String arr[] = s.split("\\s+");
	    	return  (arr.length >=3 && arr[0].equals(si.trim()) &&
	    			arr[1].equals(gu) &&
	    			!arr[1].contains(arr[0]) &&
					!arr[2].contains(arr[1])) ;  
	         })	
	    	.map(s->{
   			   String[] arr = s.split("\\s+");
   			   if(arr.length > 3) {
				  arr[2] += " " + arr[3];
   			   }
	    		return arr[2].trim();
	    	 })
	    	.distinct().collect(Collectors.toList());
		
	}
	request.setAttribute("list", list);
	request.setAttribute("len", list.size());
	return "ajax/select";
  }
  // http://localhost:8080/model2Study/ajax/graph1
  @RequestMapping("graph1")
  public String graph1(HttpServletRequest request, HttpServletResponse response) {
	  //[{"cnt":9,"writer":"홍길동"},{"cnt":3,"writer":"홍길순"}, ...]
	  BoardDao dao = new BoardDao();
	  List<Map<String,Object>> list = dao.boardgraph1();
//	  for(Map<String,Object> m : list) 
//		  System.out.println(m);
	  StringBuilder json = new StringBuilder("[");
	  int i=0;
	  for(Map<String, Object> m : list) {
		  for(Map.Entry<String,Object> me : m.entrySet()) {
			  // m : {cnt=9, writer=홍길동}
			  if(me.getKey().equals("cnt"))
				  json.append("{\"cnt\":"+me.getValue()+ ",");
			  if(me.getKey().equals("writer"))
				  json.append("\"writer\":\""+me.getValue()+ "\"}");
		  }
		  i++;
		  if(i < list.size()) json.append(",");
	  }
	  json.append("]");
	  request.setAttribute("json", json.toString().trim());
	  return "ajax/graph";
  }
  @RequestMapping("graph2") //작성일자별 게시물등록건수 그래프를 위한 데이터 전송
	public String graph2(HttpServletRequest request, 
			             HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		List<Map<String, Object>> list = dao.boardgraph2();
		StringBuilder json = new StringBuilder("[");
		int i = 0;
		for (Map<String, Object> m : list) {
		  for (Map.Entry<String, Object> me : m.entrySet()) {
			if (me.getKey().equals("regdate"))
				json.append("{\"regdate\":\"" + me.getValue() + "\",");
			if (me.getKey().equals("cnt"))
				json.append("\"cnt\":" + me.getValue() + "}");
		  }
		  i++;
		  if (i < list.size())	json.append(",");
		}
		json.append("]");
		request.setAttribute("json", json.toString().trim());
		return "ajax/graph"; 
	}
  
  	@RequestMapping("exchange")
  	public String exchange(HttpServletRequest request, HttpServletResponse response) {
  		Document doc = null;
  		List<List<String>> trlist = new ArrayList<>();
  		String url = "https://www.koreaexim.go.kr/wg/HPHKWG057M01";
  		String exdate = null;
  		try {
  		    doc = Jsoup.connect(url).get();
  		    // tr 요소만 가져온다.
  		    Elements trs = doc.select("tr");
  		    // 조회 기준일 정보
  		    exdate = doc.select("p.table-unit").html();
  		    // 환율정보
  		    for(Element tr : trs) {
  		        List<String> tdlist = new ArrayList<>();
  		        Elements tds = tr.select("td");
  		        for(Element td : tds) {
  		            tdlist.add(td.html());
  		        }
  		        // 0:통화코드, 1:통화명, 2:받으실때, 3:보내실때
  		        if (tdlist.size() > 0) { // 4개의 통화만 선택
  		            if(tdlist.get(0).equals("USD") || tdlist.get(0).equals("CNH")
  		            || tdlist.get(0).equals("JPY(100)")
  		            || tdlist.get(0).equals("EUR")) {
  		               trlist.add(tdlist); 
  		            }
  		        }
  		    }
  		} catch (IOException e) {
			e.printStackTrace();
		}
  		request.setAttribute("date", exdate);
  		request.setAttribute("list", trlist);
  		return "ajax/exchange";
  	}
  	
  	@RequestMapping("exchange_n")
  	public String exchange_n(HttpServletRequest request, HttpServletResponse response) {
  		
  		
  		
  	}
}