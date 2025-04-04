/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.102
 * Generated at: 2025-04-02 02:05:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.ex04_005frequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Enumeration;

public final class ex01_005frequest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(2);
    _jspx_imports_classes.add("java.util.Enumeration");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("	<head>\r\n");
      out.write("	<meta charset=\"UTF-8\">\r\n");
      out.write("	<title>전송된 파라미터 값 출력</title>\r\n");
      out.write("	</head>\r\n");
      out.write("	<body>\r\n");
      out.write("		");

		// 파라미터 데이터의 인코딩 설정. 처음에 실행해야 함
		request.setCharacterEncoding("UTF-8");
		// <input type="text" name="name" value="홍길동">
		// name : 홍길동 => 입력된 값
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		// gender : 1, 2
		String gender = request.getParameter("gender");
		// getParameter("파라미터이름") : 한개의 값만 조회
		String hobby = request.getParameter("hobby");
		// select 태그 : option 태그의 value 값
		String year = request.getParameter("year");
		
      out.write("\r\n");
      out.write("		\r\n");
      out.write("		이름 : ");
      out.print( name );
      out.write("<br/>\r\n");
      out.write("		나이 : ");
      out.print( age );
      out.write("<br/>\r\n");
      out.write("		성별 : ");
      out.print( gender.equals("1") ? "남" : "여" );
      out.write("<br/>\r\n");
      out.write("		취미 : ");
      out.print( hobby );
      out.write("<br/>\r\n");
      out.write("		출생년도 : ");
      out.print( year );
      out.write("<br/>\r\n");
      out.write("		<hr/>\r\n");
      out.write("		\r\n");
      out.write("		<h3>모든 취미 정보 조회하기</h3>\r\n");
      out.write("		");
 
		String[] hobbies = request.getParameterValues("hobby");
		for(String h : hobbies){
		
      out.write("\r\n");
      out.write("		");
      out.print( h );
      out.write(" &nbsp;&nbsp;&nbsp;&nbsp; ");
      out.write("\r\n");
      out.write("		");
 } 
      out.write("\r\n");
      out.write("		<hr/>\r\n");
      out.write("		\r\n");
      out.write("		<h3>모든 파라미터 정보 조회하기</h3>\r\n");
      out.write("		<table border=\"1\">\r\n");
      out.write("        <tr><th>파라미터이름</th><th>파라미터값</th></tr>\r\n");
      out.write("        ");
 
        Enumeration e = request.getParameterNames();
        while(e.hasMoreElements()){
            String pname = (String)e.nextElement();
            String[] values = request.getParameterValues(pname);
        
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>");
      out.print( pname );
      out.write("</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
 if (values != null) {
                    for (String s : values){ 
      out.write("\r\n");
      out.write("                        ");
      out.print( s );
      out.write("&nbsp;&nbsp;\r\n");
      out.write("                ");
 } 
                } 
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("	</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
