<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>尚硅谷会员注册页面</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
    <script src="scripts/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#imgCode").click(function () {
                //img标签中src的值一改变，浏览器就会重新向src地址发送请求。
                //要保证code.jpg不变，又要使src改变，则在后面附上一个请求参数。
                $(this).attr("src","code.jpg?t="+Math.random());
            });
            $("#sub_btn").click(function () {

                var username = document.getElementById("username");
                username.oninvalid = function () {
                    username.setCustomValidity("UserName Format:^[a-zA-Z0-9_-]{3,18}$");
                };
                var password = document.getElementById("password");
                password.oninvalid = function () {
                    password.setCustomValidity("Password Format:^[a-zA-Z0-9_-]{3,18}$");
                };
                var email = document.getElementById("email");
                email.oninvalid = function () {
                    email.setCustomValidity("Illegal email address");
                };

                var $pwd = $("#password").val();
                var $repwd = $("#repwd").val();
                if ($repwd != $pwd) {
                    alert("Passwords don't match");
                    return
                }
            });

            //给输入用户名绑定change时间
            $("#username").change(function () {
                var username=$(this).val();
                //发送Ajax请求
                //设置请求地址
                var url="UserServlet?method=checkUserName";
                var params={"username":username};
                var spanEle=$("#userNameSpan");
                $.post(url,params,function (data) {
                    $("#userNameSpan").html(data);
                },"json");
            })
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册书城会员</h1>
                    <%--<span class="errorMsg"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%></span>--%>
                    <span class="errorMsg">${requestScope.msg}</span>
                </div>
                <div class="form">
                    <form action="UserServlet">
                        <input type="hidden" name="method" value="regist">
                        <label>用户名称：</label>
                        <%--<input value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>" class="itxt" pattern="^[a-zA-Z0-9_-]{3,16}$" required type="text" placeholder="请输入用户名"--%>
                               <%--autocomplete="off" tabindex="1" name="username" id="username"/>--%>
                        <input value="${param.username}" class="itxt" pattern="^[a-zA-Z0-9_-]{3,16}$" required type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"/><span id="userNameSpan" style="color: #dd4422"></span>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" pattern="^[a-zA-Z0-9_-]{3,16}$" required type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" pattern="^[a-zA-Z0-9_-]{3,16}$" required type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input value="${param.email}" class="itxt" pattern="^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$"
                               required type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email"
                               id="email"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 150px;" id="code" name="inputCode" required/>
                        <img id="imgCode" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 30px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<footer id="bottom">
			<span>
			</span>
</footer>
</body>
</html>