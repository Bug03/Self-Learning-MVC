/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Ordered;
import model.Product;
import model.Transactions;
import service.AdminService;
import service.BoardnewService;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.impl.AdminServicesImpl;
import service.impl.BoardnewServicesImpl;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import utils.OrderStatus;

/**
 *
 * @author Admin
 */
public class OrderConfirmController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TransactionService transactionService = new TransactionServicesImpl();
    ProductService productService = new ProductServiceImpl();
    BoardnewService boardnewService = new BoardnewServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();
    AdminService adminService = new AdminServicesImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        String admin = (String) session.getAttribute("admin-username");
        Transactions transactions = transactionService.get(Integer.parseInt(id));
        for (Admin ad : adminService.getAll()) {
            if (ad.getUsername().equals(admin)) {
                transactions.setAdmin_id(String.valueOf(ad.getId()));
                break;
            }
        }
//        List<Ordered> ordereds = new ArrayList<>();
//        for (Ordered o : orderedService.getAll()) {
//            if (Integer.parseInt(o.getTransaction_id()) == transactions.getId()) {
//                ordereds.add(o);
//            }
//        }
//        for (Ordered o : ordereds) {
//            Product product = productService.get(Integer.parseInt(o.getProduct_id()));
//            long sold = product.getSold();
//            int inventory = product.getInventory();
//            product.setSold(sold + o.getQty());
//            product.setInventory(inventory - o.getQty());
//            productService.edit(product);
//        }
        transactions.setStatus(OrderStatus.DOING);
        transactions.setConfirm(true);
        transactionService.edit(transactions);
        resp.sendRedirect(req.getContextPath() + "/admin/order/list");
    }
}
