package controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Order;

public class UpdateCartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/view/client/cart");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> listItems = order.getItems();
        order.setSumPrice(0);
        for(Item item : listItems) {
            item.setQty(Integer.parseInt(req.getParameter(item.getProduct().getId())));
            if (item.getQty() == 0) {
                item.setQty(1);
            }
            String y = item.getProduct().getPrice().replace(".", "");
            long price = Integer.parseInt(y);  // Giá cũ sản phẩm
            int percent = Integer.parseInt(item.getProduct().getDiscount());
            double balance = (double) percent / 100;
            double percentBalance = 1 - balance;
            double quantity = (double) item.getQty();
            item.setPrice(String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(percentBalance * price * quantity)));
            String itemPrice = item.getPrice().replace(".", "");
            order.setSumPrice(order.getSumPrice() + Long.parseLong(itemPrice));
        }
        order.setItems(listItems);
        session.setAttribute("order", order);
        session.setAttribute("sumprice", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(order.getSumPrice())));
        req.setAttribute("updateMessage", "Thành công : Bạn đã cập nhập giỏ hàng !");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/cart.jsp");
        dispatcher.forward(req, resp);
    }
}
