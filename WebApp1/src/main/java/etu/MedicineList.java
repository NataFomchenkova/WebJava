package etu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// библиотечный класс для работы с потоками вывода
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @author Fomchenkova Nataliya 7308
 */
@WebServlet("/etu.MedicineList")
public class MedicineList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("med");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineList() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
// Задание типа кодировки для параметров запроса
        request.setCharacterEncoding("utf-8");
        // Чтение параметра name из запроса
        String name = request.getParameter("name");
        // Задание типа содержимого для ответа (в том числе кодировки)
        response.setContentType("text/html;charset=UTF-8");

        //интернационализация
        String lang = request.getParameter("lang");
        if(lang == null){
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
                    "Parameter lang doesn't found");
            return;
        }
        //Установка локали
        ResourceBundle res = null;
        if("en".equalsIgnoreCase(lang) || "ru".equalsIgnoreCase(lang)) {
            res = ResourceBundle.getBundle("med", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());
        }else
        {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Параметр lang может принимать значения en или ru");
            return;
        }

        // Получение потока для вывода ответа
        PrintWriter out = response.getWriter();
        try {
            // Создание HTML-страницы
            out.println("<html>");
            out.println("<head><title>" + res.getString("title") + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + res.getString("main_label")  + (name != null ? name :
                    res.getString("no_name")) + "</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td><b>"+res.getString("specialty")+"</b></td><td><b>"+res.getString("doctor_name") + "</b></td><td><b>"+res.getString("visited")+"</b></td></tr>");
            out.println("<tr><td>Oculist</td><td>Ivanov A.A. </td><td>Yes</td></tr>");
            out.println("<tr><td>Neurologist </td><td> Zhuravleva E.Yu. </td><td>No</td></tr>");
            out.println("<tr><td>Surgeon </td><td>Novikov S.Yu. </td><td>Yes</td></tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            // Закрытие потока вывода
            out.close();
        }
    }
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
