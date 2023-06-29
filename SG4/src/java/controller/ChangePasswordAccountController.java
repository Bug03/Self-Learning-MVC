/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import service.UserService;
import service.impl.UserServicesImpl;

public class ChangePasswordAccountController extends HttpServlet {

    UserService userService = new UserServicesImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = userService.get(username);
        user.setPassword(request.getParameter("newpassword"));
        userService.edit(user);
        int sizePass = user.getPassword().length();
        request.setAttribute("size", sizePass);
        request.setAttribute("message", "Đổi mật khẩu thành công!");
        request.setAttribute("user", user);
        request.setAttribute("type", "1");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/myaccount.jsp");
        dispatcher.forward(request, response);
    }
}
