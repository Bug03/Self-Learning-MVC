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
import model.Item;
import model.Order;
import model.Ordered;
import model.Product;
import model.Transactions;
import service.BoardnewService;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.impl.BoardnewServicesImpl;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;

public class TransactionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    TransactionService transactionService = new TransactionServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();
    BoardnewService boardnewService = new BoardnewServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/checkout.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String tr_usersession = req.getParameter("transaction_usersession");
        String tr_username = req.getParameter("transaction_name");
        String tr_usermail = req.getParameter("transaction_email");
        String tr_userphone = req.getParameter("transaction_phone");
        String tr_useraddress = req.getParameter("transaction_address");
        String tr_usermess = req.getParameter("transaction_mess");
        String tr_amount = req.getParameter("transaction_amount");
        String tr_payment = req.getParameter("transaction_payment");
        String tr_created = req.getParameter("transaction_created");
        String tr_department = req.getParameter("transaction_department");

        Transactions transaction = new Transactions();
        transaction.setUser_session(tr_usersession);
        transaction.setUser_name(tr_username);
        transaction.setUser_mail(tr_usermail);
        transaction.setUser_phone(tr_userphone);
        transaction.setAddress(tr_useraddress);
        transaction.setMessage(tr_usermess);
        transaction.setAmount(tr_amount);
        transaction.setPayment(tr_payment);
        transaction.setCreated(tr_created);
        transaction.setConfirm(false);
        transaction.setStatus("Chờ xác nhận");
        transaction.setAdmin_id("0");
        transactionService.insert(transaction);
        int maxid = 0;
        List<Transactions> transactions = transactionService.getAll();
        if (transactions.size() == 0) {
            maxid = 0;
        } else {
            for (Transactions transactions2 : transactions) {
                if (transactions2.getId() >= maxid) {
                    maxid = transactions2.getId();
                }
            }
        }
        HttpSession session = req.getSession(true);
        Order order = (Order) session.getAttribute("order");
        String username = (String) session.getAttribute("username");
        if (username != null) {
            List<Item> listItems = order.getItems();
            for (Item item : listItems) {
                Ordered ordered = new Ordered();
                ordered.setProduct_id(item.getProduct().getId());
                ordered.setQty(item.getQty());
                ordered.setTransacsion_id(String.valueOf(maxid));
                orderedService.insert(ordered);
            }
            if (session != null) {
                session.removeAttribute("order"); //remove session
                session.removeAttribute("sumprice"); //remove session
                session.removeAttribute("length_order"); //remove session
            }
        }
        List<Ordered> ord = new ArrayList<>();
        List<Transactions> transactionss = transactionService.getAll();
        List<Ordered> list = orderedService.getAll();
        for (Transactions b : transactionss) {
            for (Ordered a : list) {
                int id = Integer.parseInt(a.getTransaction_id());
                if (id == maxid && id == b.getId()) {
                    ord.add(a);
//                    Product product = productService.get(Integer.parseInt(a.getProduct_id()));
//                    long sold = product.getSold();
//                    int inventory = product.getInventory();
//                    product.setInventory(inventory - a.getQty());
//                    if (product.getInventory() == 0) {
//                        product.setStatus("0");
//                    }
//                    productService.edit(product);
//                    if (sold > 0) {
//                        sold += a.getQty();
//                        productService.editSold(sold, Integer.parseInt(a.getProduct_id()));
//                    } else {
//                        productService.editSold(a.getQty(), Integer.parseInt(a.getProduct_id()));
//                    }
                }
            }
        }
        List<Transactions> trans = new ArrayList<>();
        Transactions tran = transactionService.get(maxid);
        trans.add(tran);
        session.setAttribute("successTransaction", tran);
        session.setAttribute("successOrder", ord);
        resp.sendRedirect(req.getContextPath() + "/view/client/checkout-success.jsp");
    }

}
