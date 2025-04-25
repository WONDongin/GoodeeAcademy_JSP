package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadImageServlet
 */
@WebServlet("/board/uploadImage")
@MultipartConfig   //업로드된 파일을 처리 
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadImageServlet() {
        super();
    }
    private static final String UPLOAD_DIR = "uploaded_images"; 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//filePart : 파일의 내용
		Part filePart = request.getPart("file");
		String fileName = getFileName(filePart); //파일의 이름 추출
		//File.separator : window : \
		//                 리눅스  : /
		String uploadPath =  //파일업로드 되는 폴더
			getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) uploadDir.mkdirs(); //폴더 생성
		//이미지 파일이 업로드된 절대 경로 
		String filePath = uploadPath + File.separator + fileName;
		filePart.write(filePath); //파일 업로드. @MultipartConfig 어노테이션 필수
		//request.getContextPath() : 프로젝트명.
		//fileUrl : 톰캣이 접근할 수 있는 url 정보.
        //          /model2Study/uploaded_images/filename 		
		String fileUrl = request.getContextPath() 
				+ "/" + UPLOAD_DIR + "/" + fileName;
		response.setContentType("text/plain"); //순수문자데이터. 
		response.getWriter().write(fileUrl);
	}
	private String getFileName(Part part) {
		System.out.println
		("content-disposition=>"+part.getHeader("content-disposition"));
		//form-data; name="file"; filename="amazon1.jpg"
		for(String content : part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				String filename = content.substring
						(content.indexOf("=") + 1).trim().replace("\"", "");
				System.out.println("원래 이미지 이름 :" + filename);
				String ext = filename.substring(filename.lastIndexOf("."));	//파일 확장자
				//UUID : Universally Unique Identifier 
				//       고유한 ID를 생성하는 표준 형식
				//  32자리 + (-) 4개
				// 8자리 : 시간-기반 무작위 값
				// 4자리 : UUID 버전, 랜덤 비트,시간 등
				// 12자리 : 랜텀비트 또는 노드 정보
				filename = UUID.randomUUID() + ext;	//저장될 파일 명
				System.out.println("변경된 이미지 이름 :" + filename);
				return filename;
			}
		}
		return "unknown.jpg";
	}
}