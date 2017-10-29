<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
    <%@include file="/WEB-INF/include/base.jsp" %>
    <script>
        $(function () {
            $(".deleteLink").click(function () {
                var $title = $(this).parents("tr").children().first().text();
                var flag = confirm("Do you want to delete《" + $title + "》");
                if (flag) {
                    return true;
                }
                return false;
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <div>
        <%@include file="/WEB-INF/include/header.jsp" %>
    </div>
</div>

<div id="main">
    <c:if test="${empty requestScope.page.list}">
        <h1>No records.</h1>
    </c:if>
    <table>
        <c:if test="${not empty requestScope.page.list}">
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <c:forEach items="${requestScope.page.list}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.price}</td>
                    <td>${book.author}</td>
                    <td>${book.sales}</td>
                    <td>${book.stock}</td>
                    <td><a href="/BookManagerServlet?method=getBookById&bookId=${book.id}">修改</a></td>
                    <td><a href="/BookManagerServlet?method=deleteBookById&bookId=${book.id}" class="deleteLink">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="/pages/manager/book_edit.jsp">添加图书</a></td>
        </tr>
    </table>
    <div id="page_nav">
        <c:if test="${page.pageNo>1}">
        <a href="/BookManagerServlet?method=getPageBooks">首页</a>
        <a href="/BookManagerServlet?method=getPageBooks&pageNo=${page.pageNo-1}">上一页</a>
        </c:if>
        【${page.pageNo}】
        <c:if test="${page.pageNo<page.totalPageNo}">
        <a href="/BookManagerServlet?method=getPageBooks&pageNo=${page.pageNo+1}">下一页</a>
        <a href="/BookManagerServlet?method=getPageBooks&pageNo=${page.totalPageNo}">末页</a>
        </c:if>
        共${page.totalPageNo}页，${page.totalRecord}条记录 到第<input value="4" name="pn" id="pn_input"/>页
        <input type="button" value="确定" id="ok">
        <script type="text/javascript">
            $("#ok").click(function () {
               var $pageNO=$("#pn_input").val();
               window.location.href="/BookManagerServlet?method=getPageBooks&pageNo="+$pageNO;
            });
        </script>
    </div>
</div>

<div id="bottom">
		<span>
		</span>
</div>
</body>
</html>