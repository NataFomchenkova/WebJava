<%--
  Created by IntelliJ IDEA.
  User: 1078918
  Date: 19.02.2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Введите имя пользователя</title>
</head>
<body>
<form METHOD=GET action="Processor">
    Введите имя пользователя <br>
    <INPUT TYPE=TEXT NAME="name"
        <%
            // Выбор всех Cookie
            Cookie [] cookies = request.getCookies();
            if(cookies != null)
                for(int i = 0; i < cookies.length; i++)
            if("user.name".equals(cookies[i].getName())) {
                // Запись значения в поле ввода, если найден Cookie
                out.print(" value='" + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "'");
                break;
            }
        %>
    > <br>
    Введите цвет для отображения<br>
    <INPUT TYPE=TEXT NAME="color"
        <%
            if(cookies != null)
                for(int i = 0; i < cookies.length; i++)
            if("user.color".equals(cookies[i].getName())) {
                // Запись значения в поле ввода, если найден Cookie
                out.print(" value='" + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "'");
                break;
            }
        %>
    > <br>
    <INPUT TYPE=SUBMIT value="Ввод">
</form>
</body>
</html>

