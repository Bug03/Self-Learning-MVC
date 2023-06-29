package controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.Order;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class AddtoCartController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int n = 0;
        int qty = 1;
        String id;
        if (request.getParameter("product-id") != null) {
            id = request.getParameter("product-id");
            Product product = productService.get(Integer.parseInt(id));;
            if (product != null) {
                if (request.getParameter("qty") != null) {
                    qty = Integer.parseInt(request.getParameter("qty"));
                }
                HttpSession session = request.getSession();
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
            List<Product> productList1 = productService.getAll();
            List<Product> productsList1 = new ArrayList<Product>();
            for(Product product2 : productList1) {
                Product product1 = productService.get(Integer.parseInt(product2.getId()));
                String y = product1.getPrice().replace(".", "");
                int price = Integer.parseInt(y);  // Giá cũ sản phẩm
                int percent = Integer.parseInt(product1.getDiscount());
                int a = 100;
                double balance = (double) percent / 100;
                double percentBalance = 1 - balance;
                product1.setPrice(String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(price * percentBalance)));
                productsList1.add(product1);
            }
            HttpSession session = request.getSession();
            session.setAttribute("productlist1", productsList1); // Lưu các sản phẩm giảm giá
            response.sendRedirect(request.getContextPath() + "/view/client/product");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

}
