<%-- 为兼容架构返回的ServletExtend --%>
<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
String contentType = (String)request.getAttribute("contentType");
if( contentType!=null ){
	response.setContentType(contentType);
}
%>
<%=request.getAttribute("re")%>