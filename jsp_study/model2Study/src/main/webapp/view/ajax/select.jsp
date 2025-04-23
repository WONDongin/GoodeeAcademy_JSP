<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/view/ajax/select.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="s" items="${list}" varStatus="stat">
	"${s}" <c:if test="${stat.count < len}">,</c:if>
</c:forEach>
]