package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import model.Boardnew;
import model.Product;
import service.BoardnewService;
import service.ProductService;
import service.impl.BoardnewServicesImpl;
import service.impl.ProductServiceImpl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Ordered;
import service.OrderedService;
import service.impl.OrderedServiceImpl;

public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();
    BoardnewService boardnewService = new BoardnewServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        List<Product> list_products = productService.getAll();
        List<Ordered> list_ordered = orderedService.getAll();
        // Product Sản phẩm mới
        List<Product> product_moi = productService.productNew();
        session.setAttribute("product_news", product_moi);
        // Product Sản phẩm giảm giá
        List<Product> product_giamgia = productService.productSale();
        session.setAttribute("product_sales", product_giamgia);
        List<Product> product_sellings = new ArrayList<>();
        for(Product a : list_products){
            if(a.getSold() >= 100){
                product_sellings.add(a);
            }
        }
        // Product Sản phẩm bán chạy
        session.setAttribute("product_sellings", product_sellings);
        // Product Đĩa game
        List<Product> oppo = productService.getProductById(3);
        req.setAttribute("oppo", oppo);

        // Product máy chơi game
        List<Product> apple = productService.getProductById(1);
        req.setAttribute("apple", apple);
        // Check sản phẩm khác
        List<Product> xiaomi = productService.getProductById(4);
        req.setAttribute("xiaomi", xiaomi);

        // Check sản phẩm gaming
        List<Product> samsung = productService.getProductById(2);
        req.setAttribute("samsung", samsung);
        
        List<Product> vivo = productService.getProductById(5);
        req.setAttribute("vivo", vivo);
        
        List<Product> other = productService.getProductById(6);
        req.setAttribute("other", other);

        // List tin tức
        List<Boardnew> boardnewList = boardnewService.getAll();
        req.setAttribute("boardnewlist", boardnewList);

        List<Product> productList = productService.getAll();
        req.setAttribute("productlist", productList);

        //Giá giảm
        List<Product> productsList1 = new ArrayList<Product>();
        for (Product product : productList) {
            Product product1 = productService.get(Integer.parseInt(product.getId()));
            String y = product1.getPrice().replace(".", "");
            int price = Integer.parseInt(y);  // Giá cũ sản phẩm
            int percent = Integer.parseInt(product1.getDiscount());
            int a = 100;
            double balance = (double) percent / 100;
            double percentBalance = 1 - balance;
            product1.setPrice(String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(price * percentBalance)));
            productsList1.add(product1);
        }

        session.setAttribute("productlist1", productsList1);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/index.jsp");
        dispatcher.forward(req, response);
    }
}
