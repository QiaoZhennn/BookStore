<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="/static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/WEB-INF/include/welcome.jsp" %>

</div>

<div id="main">
    <c:if test="${empty ordersByUser}">
        <h1>没有任何订单！</h1>
    </c:if>
    <c:if test="${not empty ordersByUser}">
        <table>
            <tr>
                <td>订单号</td>
                <td>日期</td>
                <td>数量</td>
                <td>金额</td>
                <td>状态</td>
                <td>详情</td>
            </tr>
            <c:forEach items="${ordersByUser}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td><fmt:formatDate value="${order.orderTime}" type="both"/></td>
                    <td>${order.totalCount}</td>
                    <td>${order.totalAmount}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.state==0}">
                                等待发货
                            </c:when>
                            <c:when test="${order.state==1}">
                                <a href="OrderManagerServlet?method=updateOrderState&orderId=${order.id}&state=2">确认收货</a>
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

</div>

<div id="bottom">
		<span>
		</span>
</div>
</body>
</html>