<%--
  Created by IntelliJ IDEA.
  User: 1078918
  Date: 12.02.2021
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/ErrorManager.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Список врачей</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if (name == null){
            throw new IllegalArgumentException("Name expected");
        }
    %>
    <h1> Список врачей, которых должен посетить пациент: <%=name%></h1>
    <%@include file="ListData.jsp"%>
</body>
</html>
