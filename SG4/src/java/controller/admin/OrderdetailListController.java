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
import service.OrderedService;
import service.ProductService;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;

public class OrderdetailListController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OrderedService orderedServcie = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);

        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
//        String department = (String) req.getSession().getAttribute("department_id");
        int sumProducts = orderedServcie.getTotalOrdered("");
        int index = Integer.parseInt(indexPage);
        List<Ordered> orderedList = orderedServcie.paging(index,"");
        req.setAttribute("orderedlist", orderedList);
        int maxPage = sumProducts / 12;
        if (sumProducts % 12 != 0) {
            maxPage++;
        }
        int endPage;
        if (maxPage > 7) {
            endPage = 7;
        } else {
            endPage = maxPage;
        }
        int beginPage = 1;
        int midPage = (endPage + beginPage) / 2;
        int sumPage = 0;
        if (index > midPage) {
            sumPage = index - midPage;
        }
        int check = endPage + sumPage;
        if (check <= maxPage) {
            beginPage = beginPage + sumPage;
            endPage = endPage + sumPage;
        } else {
            beginPage = maxPage - (endPage - 1);
            endPage = maxPage;
        }
        req.setAttribute("beginP", beginPage);
        req.setAttribute("endP", endPage);
        req.setAttribute("maxP", endPage);
        req.setAttribute("tag", index);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-orderdetail.jsp");
        dispatcher.forward(req, resp);
    }
}
