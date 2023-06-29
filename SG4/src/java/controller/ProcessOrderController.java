/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Order;
import model.Ordered;
import model.Product;
import model.Transactions;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import utils.OrderStatus;

public class ProcessOrderController extends HttpServlet {

    TransactionService transactionService = new TransactionServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String type = request.getParameter("classify");
        String tranID = request.getParameter("tranId");
        HttpSession session = request.getSession();
        List<Ordered> deliverings = (List<Ordered>) session.getAttribute("delivering");
        List<Ordered> delivereds = (List<Ordered>) session.getAttribute("delivered");
        List<Ordered> cancelleds = (List<Ordered>) session.getAttribute("cancelled");
        List<Ordered> confirms = (List<Ordered>) session.getAttribute("confirm");

        List<Transactions> tranDeliverings = (List<Transactions>) session.getAttribute("tranDelivering");
        List<Transactions> tranDelivereds = (List<Transactions>) session.getAttribute("tranDelivered");
        List<Transactions> tranCancelleds = (List<Transactions>) session.getAttribute("tranCancelled");
        List<Transactions> tranConfirms = (List<Transactions>) session.getAttribute("transConfirms");

        Transactions tran = transactionService.get(Integer.parseInt(tranID));

        if (Integer.parseInt(type) == 1) {
            tran.setStatus(OrderStatus.DONE);
            int size = tranDeliverings.size();
            for (int i = 0; i < size; i++) {
                Transactions trans = tranDeliverings.get(i);
                if (trans.getId() == Integer.parseInt(tranID)) {
                    tranDeliverings.remove(trans);
                    tranDelivereds.add(trans);
                }
            }
            List<Ordered> saveDelivereds = new ArrayList<>();
            for (Ordered ord : deliverings) {
                if (ord.getTransaction_id().equals(tranID)) {
                    saveDelivereds.add(ord);
                }
            }
            for (Ordered ord : saveDelivereds) {
                deliverings.remove(ord);
                delivereds.add(ord);
            }
        } else if (Integer.parseInt(type) == 2) {
            tran.setStatus(OrderStatus.CANCEL);
            int size = tranConfirms.size();
            for (int i = 0; i < size; i++) {
                Transactions trans = tranConfirms.get(i);
                if (trans.getId() == Integer.parseInt(tranID)) {
                    tranConfirms.remove(trans);
                    tranCancelleds.add(trans);
                }
            }
            List<Ordered> saveCancelleds = new ArrayList<>();
            for (Ordered ord : confirms) {
                if (ord.getTransaction_id().equals(tranID)) {
                    saveCancelleds.add(ord);
                }
            }
            for (Ordered ord : saveCancelleds) {
                confirms.remove(ord);
                cancelleds.add(ord);
            }
            List<Ordered> list = orderedService.getAll();

            // Check don hang da xac nhan hay chua neu chua khong tru so luong
            if (tran.isConfirm()) {
                for (Ordered a : list) {
                    int id = Integer.parseInt(a.getTransaction_id());
                    if (id == Integer.parseInt(tranID)) {
                        Product product = productService.get(Integer.parseInt(a.getProduct_id()));
                        long sold = product.getSold();
                        int inventory = product.getInventory();
                        inventory += a.getQty();
                        if (inventory > 0) {
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

        } else if (Integer.parseInt(type) == 3) {
            int qty = 1;
            int n = 0;
            List<Product> products = new ArrayList<>();
            for (Ordered ord : delivereds) {
                if (ord.getTransaction_id().equals(tranID)) {
                    Product product = productService.get(Integer.parseInt(ord.getProduct_id()));
                    products.add(product);
                }
            }
            for (Ordered ord : cancelleds) {
                if (ord.getTransaction_id().equals(tranID)) {
                    Product product = productService.get(Integer.parseInt(ord.getProduct_id()));
                    products.add(product);
                }
            }
            for (Product product : products) {
                if (session.getAttribute("order") == null) {
                    Order order = new Order();
                    List<Item> listItems = new ArrayList<Item>();
                    Item item = new Item();
                    item.setQty(qty);
                    item.setProduct(product);
                    String y = product.getPrice().replace(".", "");
                    int price = Integer.parseInt(y);  // Giá cũ sản phẩm
                    int percent = Integer.parseInt(product.getDiscount());
                    int a = 100;
                    double balance = (double) percent / 100;
                    double percentBalance = 1 - balance;
                    item.setPrice(NumberFormat.getNumberInstance(Locale.GERMANY).format(percentBalance * price));
                    order.setSumPrice(0);
                    order.setSumPrice(order.getSumPrice() + Integer.parseInt(item.getPrice().replace(".", "")));
                    listItems.add(item);
                    order.setItems(listItems);
                    n = listItems.size();
                    session.setAttribute("length_order", n);
                    session.setAttribute("order", order);
                    session.setAttribute("sumprice", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(order.getSumPrice())));
                } else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> listItems = order.getItems();
                    boolean check = false;
                    for (Item item : listItems) {
                        if (Integer.parseInt(item.getProduct().getId()) == Integer.parseInt(product.getId())) {
                            item.setQty(item.getQty() + qty);
                            String y = item.getProduct().getPrice().replace(".", "");
                            int price = Integer.parseInt(y);  // Giá cũ sản phẩm
                            int percent = Integer.parseInt(item.getProduct().getDiscount());
                            int a = 100;
                            double balance = (double) percent / 100;
                            double percentBalance = 1 - balance;
                            order.setSumPrice(order.getSumPrice() + percentBalance * price);
                            int priceItem = Integer.parseInt(item.getPrice().replace(".", ""));
                            item.setPrice(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceItem + (percentBalance * price)));
                            check = true;
                        }
                    }
                    if (check == false) {
                        Item item = new Item();
                        item.setQty(qty);
                        item.setProduct(product);
                        String y = item.getProduct().getPrice().replace(".", "");
                        int price = Integer.parseInt(y);  // Giá cũ sản phẩm
                        int percent = Integer.parseInt(item.getProduct().getDiscount());
                        int a = 100;
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        item.setPrice(NumberFormat.getNumberInstance(Locale.GERMANY).format(percentBalance * price));
                        order.setSumPrice(order.getSumPrice() + percentBalance * price);
                        listItems.add(item);
                    }
                    n = listItems.size();
                    session.setAttribute("length_order", n);
                    session.setAttribute("order", order);
                    session.setAttribute("sumprice", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(order.getSumPrice())));
                }
            }
        }
        transactionService.edit(tran);
        request.setAttribute("productlist", productService.getAll());
        session.setAttribute("delivering", deliverings);
        session.setAttribute("delivered", delivereds);
        session.setAttribute("cancelled", cancelleds);
        session.setAttribute("tranDelivering", tranDeliverings);
        session.setAttribute("tranDelivered", tranDelivereds);
        session.setAttribute("tranCancelled", tranCancelleds);
        request.setAttribute("type", "2");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/myaccount.jsp");
        dispatcher.forward(request, response);
    }
}
