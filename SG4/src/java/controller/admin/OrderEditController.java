package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import utils.OrderStatus;
import utils.StringUtils;

public class OrderEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TransactionService transactionService = new TransactionServicesImpl();
    ProductService productService = new ProductServiceImpl();
    BoardnewService boardnewService = new BoardnewServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Transactions transaction = transactionService.get(Integer.parseInt(id));
        req.setAttribute("order", transaction);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editorder.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        List<Ordered> list = orderedService.getAll();
        List<Transactions> list_tran = transactionService.getAll();
        List<Transactions> check = new ArrayList();
        List<Product> list_pro = productService.getAll();
        Transactions transactions = new Transactions();
        transactions.setId(Integer.parseInt(req.getParameter("order-id")));
        transactions.setUser_name(req.getParameter("order-name"));
        transactions.setUser_mail(req.getParameter("order-mail"));
        transactions.setUser_phone(req.getParameter("order-phone"));
        transactions.setAddress(req.getParameter("order-address"));
        transactions.setMessage(req.getParameter("order-mess"));
        transactions.setAmount(req.getParameter("order-amount"));
        transactions.setPayment(req.getParameter("order-payment"));
        transactions.setStatus(req.getParameter("order-status"));
        transactionService.edit(transactions);
        if (!StringUtils.removeAccent(transactions.getStatus()).equals(StringUtils.removeAccent(OrderStatus.DONE))) {
            for (Ordered a : list) {
                int id = Integer.parseInt(a.getTransaction_id());
                if (id == transactions.getId()) {
                    Product product = productService.get(Integer.parseInt(a.getProduct_id()));
                    long sold = product.getSold();
                    int inventory = product.getInventory();
                    inventory += a.getQty();
                    if(inventory > 0){
                        product.setStatus("1");
                    }
                    product.setInventory(inventory);
                    productService.edit(product);
                    if (sold > 0) {
                        sold -= a.getQty();
                        productService.editSold(sold, Integer.parseInt(a.getProduct_id()));
                    }
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/admin/order/list");
    }
}
