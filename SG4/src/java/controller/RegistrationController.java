package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.RegisterDao;
import jdbc.connectDB;
import model.User;
import service.CityService;
import service.impl.CityServicesImpl;

public class RegistrationController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CityService cityService = new CityServicesImpl();

    public RegistrationController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("city", cityService.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String name = request.getParameter("name");
        String created = request.getParameter("created");
        String avatar = "default-avatar.jpg";

        User user = new User(username, password, email, phone, name, created, address,avatar);

        RegisterDao register = new RegisterDao(connectDB.getConnect());
        if (register.RegisterUser(user)) {
            request.setAttribute("Message", "Bạn đã tạo tài khoàn thành công. Mời bạn đăng nhập <a href='" + request.getContextPath() + "/view/client/login'>tại đây!</a>");
            request.setAttribute("city", cityService.getAll());
            RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
            request.setAttribute("city", cityService.getAll());
            RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
            rd.forward(request, response);
        }
    }
}
