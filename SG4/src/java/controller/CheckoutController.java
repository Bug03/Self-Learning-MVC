package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import service.BoardnewService;
import service.CityService;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.UserService;
import service.impl.BoardnewServicesImpl;
import service.impl.CityServicesImpl;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import service.impl.UserServicesImpl;

public class CheckoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CityService cityService = new CityServicesImpl();
    UserService userService = new UserServicesImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        User user = userService.get(username);
        req.setAttribute("user", user);
        req.setAttribute("city", cityService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/checkout.jsp");
        dispatcher.forward(req, resp);
    }

}
