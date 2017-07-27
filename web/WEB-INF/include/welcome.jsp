<%--
  Created by IntelliJ IDEA.
  User: qiaoz
  Date: 2017/7/4
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty user}">
    <div>
        <span>欢迎<span class="um_span">${user.username}</span>光临尚硅谷书城</span>
        <a href="/pages/cart/cart.jsp">购物车</a>
        <a href="OrderManagerServlet?method=getOrdersByUserId&userId=${user.id}">我的订单</a>
        <a href="UserServlet?method=logout">注销</a>&nbsp;&nbsp;
        <a href="/index.jsp">返回商城</a>
    </div>
</c:if>

<c:if test="${empty user}">
    <div>
        <a href="/pages/user/login.jsp">登录</a> |
        <a href="/pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="/pages/cart/cart.jsp">购物车</a>
        <a href="/pages/manager/manager.jsp">后台管理</a>
    </div>
</c:if>