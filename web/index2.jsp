<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="test.listenerLearning.Student" %>
<%@ page import="test.listenerLearning.Teacher" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: qiaoz
  Date: 2017/6/17
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/scripts/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                //使用jQuery发送Ajax的GET请求
                //jQuery.get(url,[data],[callback],[type])
                /*
                url：必须，用来设置请求的地址
                data：可选，用来设置请求参数
                callback：可选，响应成功后调用的回调函数
                type：可选，用来设置响应数据的类型，如text,json
                 */
                //设置请求地址
                var url="${pageContext.request.contextPath}/JQueryServlet";
                //设置请求参数
                var params={"username":"admin","password":"123456"};
                //声明回调函数
                var fun=function (data) {
                    alert(data);
                }
                //设置响应数据类型
                var type="text";
                $.get(url,params,fun,type); //type可省
            });

            $("#btn2").click(function () {
                var url="${pageContext.request.contextPath}/JQueryServlet";
                var params={"username":"admin2","password":"098765"};
                $.post(url,params,function (data) {
                    alert(data.email);
                },"json");
            });
        });
    </script>
</head>
<body>
<a href="success.jsp">Can you see me?</a>
<%--<jsp:forward page="success.jsp"/>--%>
<hr>
<%--<fmt:setLocale value="zh_CN"/>--%>
<fmt:setLocale value="${param.locale}"/>
<fmt:setBundle basename="i18n"/>
<a href="${pageContext.request.contextPath}index2.jsp?locale=zh_CN">中文 </a>|
<a href="${pageContext.request.contextPath}index2.jsp?locale=en_US"> English</a>
<hr>
<fmt:message key="info">
    <fmt:param>${param.username}</fmt:param><br>
    <fmt:param><fmt:formatDate value="<%=new Date()%>" dateStyle="full" timeStyle="full" type="both"/></fmt:param>
</fmt:message>
<hr>
<%--<form action="${pageContext.request.contextPath}index2.jsp?locale=${param.locale}" method="post">--%>
<%--<fmt:message key="username"/>: <input type="text" name="username"><br>--%>
<%--<fmt:message key="password"/>: <input type="password" name="password"><br>--%>
<%--<input type="submit" value="<fmt:message key="login"/>">--%>
<%--</form>--%>
<br>
<button id="btn">发送GET的Ajax请求</button>
<span style="color:red;" id="span"></span>
<br><br>
<button id="btn2">发送POST的Ajax请求</button>
</body>
</html>
