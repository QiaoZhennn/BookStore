<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="/static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <div>
        <a href="/pages/manager/book_manager.jsp">图书管理</a>
        <a href="/pages/manager/order_manager.jsp">订单管理</a>
        <a href="/index.jsp">返回商城</a>
    </div>
</div>

<div id="main">
    <c:if test="${not empty orders}">
        <table>
            <tr>
                <td>订单号</td>
                <td>日期</td>
                <td>数量</td>
                <td>金额</td>
                <td>状态</td>
                <td>详情</td>

            </tr>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>
                        <fmt:formatDate value="${order.orderTime}" type="both"/>
                    </td>
                    <td>${order.totalCount}</td>
                    <td>${order.totalAmount}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.state==0}">
                                <a href="OrderManagerServlet?method=updateOrderState&orderId=${order.id}&state=1">点击发货</a>
                            </c:when>
                            <c:when test="${order.state==1}">
                                等待确认收货
                            </c:when>
                            <c:otherwise>
                                交易完成
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><a href="OrderManagerServlet?method=getOrderItemsById&orderId=${order.id}">查看详情</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty orders}">
        没有订单，<a href="/index.jsp" style="color: #dd4422">去购物</a>
    </c:if>
</div>

<div id="bottom">
		<span>
		</span>
</div>
</body>
</html>