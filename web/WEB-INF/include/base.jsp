<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<%--<base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">--%>
<%--用EL表达式修改上面的JSP语句--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="/scripts/jquery-3.2.1.min.js"></script>
