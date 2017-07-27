<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="/static/img/logo.gif">
    <span class="wel_word">订单详情</span>
    <div>
        <c:if test="${empty user}">
            <a href="/pages/manager/book_manager.jsp">图书管理</a>
            <a href="/pages/manager/order_manager.jsp">订单管理</a>
            <a href="/index.jsp">返回商城</a>
        </c:if>
        <c:if test="${not empty user}">
            <a href="/pages/cart/cart.jsp">购物车</a>
            <a href="OrderManagerServlet?method=getOrdersByUserId&userId=${user.id}">我的订单</a>
            <a href="UserServlet?method=logout">注销</a>&nbsp;&nbsp;
            <a href="/index.jsp">返回商城</a>
        </c:if>
    </div>
</div>

<div id="main">
    <table>
        <tr>
            <td>封面</td>
            <td>书名</td>
            <td>作者</td>
            <td>价格</td>
            <td>数量</td>
            <td>总计</td>

        </tr>
        <c:forEach items="${orderItems}" var="orderItem">
            <tr>
                <td>
                    <img class="book_img" alt="" src="${orderItem.imgPath}">
                </td>
                <td>${orderItem.title}</td>
                <td>${orderItem.author}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.amount}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>