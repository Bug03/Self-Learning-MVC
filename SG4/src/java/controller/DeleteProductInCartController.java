package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.Order;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class DeleteProductInCartController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productservice = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession(true);
        Product product = productservice.get(Integer.parseInt(id));
        Order order = (Order) session.getAttribute("order");
        List<Item> listItems = order.getItems();
        for (Item item : listItems) {
            if (Integer.parseInt(item.getProduct().getId()) == Integer.parseInt(product.getId())) {
                order.setSumPrice(order.getSumPrice() - Long.parseLong(item.getPrice().replace(".", "")));
                listItems.remove(item);
                break;
            }
        }
        order.setItems(listItems);
        session.setAttribute("order", order);
        int n = (int) session.getAttribute("length_order");
        session.setAttribute("length_order", n - 1);
        resp.sendRedirect(req.getContextPath() + "/view/client/cart");
        if (order.getSumPrice() == 0) {
            session.setAttribute("sumprice", "0");
        } else {
            session.setAttribute("sumprice", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(order.getSumPrice())));
        }

    }
}
