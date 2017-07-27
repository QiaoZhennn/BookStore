<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城首页</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
    <script type="text/javascript">
        $(function () {
            $(".addBook").click(function () {
                var bookId = $(this).attr("id");
                //发送请求
                location = "CartServlet?method=addBook&bookId=" + bookId;
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <%@include file="/WEB-INF/include/welcome.jsp" %>

</div>

<div id="main" style="height: 560px;overflow: hidden">

    <div id="book">
        <div class="book_cond">
            <form action="/BookClientServlet?method=getPageBooksByPrice" method="post">
                价格：<input type="text" name="min" value="${param.min}"> 元 - <input type="text" name="max"
                                                                                  value="${param.max}"> 元
                <input type="submit" value="确定">
            </form>
        </div>

        <div style="text-align: center">
            <c:if test="${cart.totalCount!=0}">
                <span>您的购物车中有${cart.totalCount}件商品</span>
            </c:if>
        </div>

        <div style="text-align: center">
            <c:if test="${empty sessionScope.bookTitle}">
                <br>
            </c:if>
            <c:if test="${not empty sessionScope.bookTitle}">
                您刚刚将<span style="color: red">${sessionScope.bookTitle}</span>加入到了购物车中
                <c:remove var="bookTitle"/>
            </c:if>
        </div>
        <div style="text-align: center">
            <span style="color: red">${msg}</span>
            <c:remove var="msg"/>
        </div>
        <c:if test="${cart.totalCount==0}">
            <div>
                购物车中还没有商品
            </div>
        </c:if>

        <c:if test="${empty requestScope.page.list}">
            <h2>No Record</h2>
        </c:if>

        <c:if test="${!empty requestScope.page.list}">
            <c:forEach items="${requestScope.page.list}" var="thisPage">
                <div class="b_list">
                    <div class="img_div">
                        <img class="book_img" alt="" src="${thisPage.imgPath}"/>
                    </div>
                    <div class="book_info">
                        <div class="book_name">
                            <span class="sp1">书名:</span>
                            <span class="sp2">${thisPage.title}</span>
                        </div>
                        <div class="book_author">
                            <span class="sp1">作者:</span>
                            <span class="sp2">${thisPage.author}</span>
                        </div>
                        <div class="book_price">
                            <span class="sp1">价格:</span>
                            <span class="sp2">${thisPage.price}</span>
                        </div>
                        <div class="book_sales">
                            <span class="sp1">销量:</span>
                            <span class="sp2">${thisPage.sales}</span>
                        </div>
                        <div class="book_amount">
                            <span class="sp1">库存:</span>
                            <span class="sp2">${thisPage.stock}</span>
                        </div>
                        <c:if test="${thisPage.stock>0}">
                            <div class="book_add">
                                <button class="addBook" id="${thisPage.id}">加入购物车</button>
                            </div>
                        </c:if>
                        <c:if test="${thisPage.stock<=0}">
                            <div class="book_add">
                                <span style="color: coral">缺货</span>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <div id="page_nav">
        <%--当总页数大于5时--%>
        <c:choose>
            <c:when test="${page.totalPageNo<5}">
                <c:set var="begin" value="1"></c:set>
                <c:set var="end" value="${page.totalPageNo}"></c:set>
            </c:when>
            <c:when test="${page.pageNo<=3}">
                <c:set var="begin" value="1"></c:set>
                <c:set var="end" value="5"></c:set>
            </c:when>
            <c:when test="${page.pageNo>=page.totalPageNo-2}">
                <c:set var="begin" value="${page.totalPageNo-4}"></c:set>
                <c:set var="end" value="${page.totalPageNo}"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${page.pageNo-2}"></c:set>
                <c:set var="end" value="${page.pageNo+2}"></c:set>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}" var="index">
            <c:if test="${page.pageNo==index}">
                [<a href="/BookClientServlet?method=getPageBooksByPrice&pageNo=${index}&min=${param.min}&max=${param.max}">${index}</a>]
            </c:if>
            <c:if test="${page.pageNo!=index}">
                <a href="/BookClientServlet?method=getPageBooksByPrice&pageNo=${index}&min=${param.min}&max=${param.max}">${index}</a>
            </c:if>
        </c:forEach>
    </div>
    <div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
    </div>
</div>
</body>
</html>
