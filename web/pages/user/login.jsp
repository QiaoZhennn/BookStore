<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>尚硅谷会员登录页面</title>
    <%@include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="找不到图片" src="static/img/logo.gif">
</div>

<div class="login_banner">
    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <%--<span class="errorMsg"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%></span>--%>
                    <span class="errorMsg">${empty msg?"请输入用户名和密码":msg}</span>
                </div>
                <div class="form">
                    <form action="UserServlet">
                        <input type="hidden" name="method" value="login">
                        <label>用户名称：</label>
                        <%--<input value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>" class="itxt" required="required" type="text" placeholder="请输入用户名" autocomplete="off"--%>
                                                                    <%--tabindex="1" name="username"/>--%>
                        <%--用EL表达式来改写JSP，param.属性名,用来获取属性值。EL为null是，默认显示空串，而非null--%>
                        <input value="${param.username}" class="itxt" required="required" type="text" placeholder="请输入用户名" autocomplete="off"
                               tabindex="1" name="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" required="required" type="password" placeholder="请输入密码" autocomplete="off"
                               tabindex="1" name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<div id="bottom">
			<span>
			</span>
</div>
</body>
</html>