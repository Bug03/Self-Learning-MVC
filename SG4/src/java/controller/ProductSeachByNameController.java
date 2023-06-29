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
import javax.servlet.http.HttpServletResponse;

import model.Catalog;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServicesImpl;
import service.impl.ProductServiceImpl;

public class ProductSeachByNameController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServicesImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("s");
        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);

        List<Product> productSeachByName = productService.searchByName(name);
        req.setAttribute("productlist", productSeachByName);

        List<Product> productAllList = productService.getAll();
        req.setAttribute("product_all", productAllList);

        //Giá giảm
        List<Product> productsList1 = new ArrayList<Product>();
        for (Product product : productSeachByName) {
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

        req.setAttribute("productlist1", productsList1);
        // Product bán chạy
        List<Product> product_banchay = productService.getProductById(6);
        req.setAttribute("product_banchay", product_banchay);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product-search.jsp");
        dispatcher.forward(req, resp);

    }
}
