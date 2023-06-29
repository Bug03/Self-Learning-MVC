/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Ordered;
import model.Transactions;
import model.User;
import service.CityService;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.UserService;
import service.impl.CityServicesImpl;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import service.impl.UserServicesImpl;
import utils.OrderStatus;
import utils.StringUtils;

public class ManagementAccount extends HttpServlet {

    UserService userService = new UserServicesImpl();
    CityService cityService = new CityServicesImpl();
    TransactionService transactionService = new TransactionServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        User user = userService.get(name);
        int sizePass = user.getPassword().length();

        // Quản lý tiến trình
        List<Ordered> orders = orderedService.getAll(); 
        List<Ordered> myOrders = new ArrayList<>();
        List<Transactions> tran = transactionService.get(name);
        int i = 0;
        for (Ordered ord : orders) {
            for (Transactions transs : tran) {
                if (Integer.parseInt(ord.getTransaction_id()) == transs.getId()) {
                    myOrders.add(ord);
                }
            }
        }
        List<Ordered> deliverings = new ArrayList<>();
        List<Ordered> delivereds = new ArrayList<>();
        List<Ordered> cancelleds = new ArrayList<>();
        List<Ordered> confirm = new ArrayList<>();
        
        List<Transactions> transDeliverings = new ArrayList<>();
        List<Transactions> transDelivereds = new ArrayList<>();
        List<Transactions> transCancelleds = new ArrayList<>();
        List<Transactions> transConfirms = new ArrayList<>();

        for (Ordered ord : myOrders) {
            Transactions trans = transactionService.get(Integer.parseInt(ord.getTransaction_id()));
            if (StringUtils.removeAccent(trans.getStatus()).equals(StringUtils.removeAccent(OrderStatus.DOING))) {
                deliverings.add(ord);
            }
            if (StringUtils.removeAccent(trans.getStatus()).equals(StringUtils.removeAccent(OrderStatus.DONE))) {
                delivereds.add(ord);
            }
            if (StringUtils.removeAccent(trans.getStatus()).equals(StringUtils.removeAccent(OrderStatus.CANCEL))) {
                cancelleds.add(ord);
            }
            if(StringUtils.removeAccent(trans.getStatus()).equals(StringUtils.removeAccent(OrderStatus.CONFIRM))){
                confirm.add(ord);
            }
        }
        
        for (Transactions transs : tran) {
            if (StringUtils.removeAccent(transs.getStatus()).equals(StringUtils.removeAccent(OrderStatus.DOING))) {
                transDeliverings.add(transs);
            }
            if (StringUtils.removeAccent(transs.getStatus()).equals(StringUtils.removeAccent(OrderStatus.DONE))) {
                transDelivereds.add(transs);
            }
            if (StringUtils.removeAccent(transs.getStatus()).equals(StringUtils.removeAccent(OrderStatus.CANCEL))) {
                transCancelleds.add(transs);
            }
            if(StringUtils.removeAccent(transs.getStatus()).equals(StringUtils.removeAccent(OrderStatus.CONFIRM))){
                transConfirms.add(transs);
            }
        }
        session.setAttribute("tranDelivering", transDeliverings);
        session.setAttribute("tranDelivered", transDelivereds);
        session.setAttribute("tranCancelled", transCancelleds);
        session.setAttribute("transConfirms", transConfirms);
        request.setAttribute("type", type);
        request.setAttribute("productlist", productService.getAll());
        session.setAttribute("delivering", deliverings);
        session.setAttribute("delivered", delivereds);
        session.setAttribute("cancelled", cancelleds);
        session.setAttribute("confirm", confirm);
        session.setAttribute("myOrder", myOrders);
        session.setAttribute("myTrans", tran);
        request.setAttribute("size", sizePass);
        session.setAttribute("user", user);
        session.setAttribute("city", cityService.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/myaccount.jsp");
        dispatcher.forward(request, response);
    }
}
