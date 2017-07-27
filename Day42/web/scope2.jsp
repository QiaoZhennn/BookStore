<%@ page import="java.util.List" %>
<%@ page import="Bean.StudentBean" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: qiaoz
  Date: 2017/6/17
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scope2</title>
    <script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<h1>Scope 2</h1>
page domain:<%=pageContext.getAttribute("pageKey")%><br><br>
request domain:<%=request.getAttribute("requestKey")%><br><br>
session domain:<%=session.getAttribute("sessionKey")%><br><br>
application domain:<%=application.getAttribute("appKey")%><br><br>

<%
    List<StudentBean> list = (List<StudentBean>) request.getAttribute("students");
    if (list == null) {%>
<h2>没有任何学生信息</h2>
<%} else {%>

<table class="table">
    <thead>
        <th>id</th>
        <th>name</th>
        <th>gender</th>
        <th>age</th>
        <th>address</th>
    </thead>
    <tbody>
    <% for (int i = 0; i < list.size(); i++) {
        StudentBean student = list.get(i); %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getGender()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getAddress()%>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>

</table>
</body>
</html>
