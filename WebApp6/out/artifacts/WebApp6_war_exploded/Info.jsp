<%--
  Created by IntelliJ IDEA.
  User: 1078918
  Date: 19.02.2021
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Информация о пользователе</title>
    </head>
    <body>
    <%
        //получим цвет текста и имя пользователя
        String color = null;
        String name = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
            for (Cookie cookie : cookies) {
                if ("user.color".equals(cookie.getName())) {
                    color = cookie.getValue();
                } else if ("user.name".equals(cookie.getName())) {
                    name = cookie.getValue();
                }
            }
        //получим дату и количество запросов
        Date lastReq = (Date) request.getSession().getAttribute("lastDate");
        Integer reqNum = (Integer) request.getSession().getAttribute("requestNum");
    %>
        <span style="color: <%=color%>; ">
            <h1>Информация о пользователе</h1>
            <br><b>Имя пользователя: </b>
            <%= URLDecoder.decode(name, "UTF-8")%>
            <br><b>Количество запросов: </b>
            <%= reqNum%>
            <br><b>Дата последнего запроса: </b>
            <%= lastReq%>
            <br><b>Текущий цвет: </b>
            <%= URLDecoder.decode(color, "UTF-8")%>
        </span>
    </body>
</html>