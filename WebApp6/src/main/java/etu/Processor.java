package etu;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Processor")
public class Processor extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Processor() {
        super();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        // Получение параметрв
        String name = request.getParameter("name");
        String color = request.getParameter("color");
        //System.out.println(doctor);

        // Сообщение об ошибке, если сервлет вызван без параметров
        if(name == null || name.equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Имя не задано");
            return;
        }
        if(color == null|| color.equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Цвет не задан");
            return;
        }

        //Получим из сессии объект с ключом requestNum
        Integer reqNum = (Integer) request.getSession().getAttribute("requestNum");
        if (reqNum == null) {
            reqNum = 1;
        } else {
            reqNum++;
        }
        request.getSession().setAttribute("requestNum", reqNum);
        //Получим дату последнего обращения
        Date lastDate = new Date(System.currentTimeMillis());
        request.getSession().setAttribute("lastDate", lastDate);

        // Сохранение имени и цвета в сессии
        //request.getSession().setAttribute("name", name);

        // Сохранение имени и цвета в Cookie
        Cookie cookieName = new Cookie("user.name", URLEncoder.encode(name, "UTF-8"));
        Cookie cookieColor = new Cookie("user.color", URLEncoder.encode(color, "UTF-8"));


        // Установка времени жизни Cookie в секундах
        cookieName.setMaxAge(100);
        cookieColor.setMaxAge(100);

        //Вставка Cookie в заголовок HTTP-ответа
        response.addCookie(cookieName);
        response.addCookie(cookieColor);

        // Перенаправление
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +
                "/Info.jsp"));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}