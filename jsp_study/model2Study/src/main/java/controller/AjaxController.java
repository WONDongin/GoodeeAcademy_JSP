package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import java.util.Set;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;

@WebServlet(urlPatterns= {"/ajax/*"},
 initParams= {@WebInitParam(name="view",value="/view/")})
public class AjaxController extends MskimRequestMapping{
	@RequestMapping("select")
	public String select(HttpServletRequest request, HttpServletResponse response) {
	    String level = request.getParameter("level");
	    String si = request.getParameter("si");
	    String gu = request.getParameter("gu");

	    BufferedReader fr = null;
	    String path = request.getServletContext().getRealPath("/") + "file/sido.txt";

	    Set<String> set = new LinkedHashSet<>();

	    try {
	        fr = new BufferedReader(new FileReader(path));
	        String data;
	        while ((data = fr.readLine()) != null) {
	            String[] arr = data.split("\\s+");
	            if (arr.length < 3) continue;

	            switch (level) {
	                case "si":
	                    set.add(arr[0].trim());
	                    break;
	                case "gu":
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
}