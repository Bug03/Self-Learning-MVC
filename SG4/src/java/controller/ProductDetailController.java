package controller;

import java.io.IOException;
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
import model.Review;
import service.CategoryService;
import service.ProductService;
import service.ReviewService;
import service.impl.CategoryServicesImpl;
import service.impl.ProductServiceImpl;
import service.impl.ReviewServicesImpl;

public class ProductDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServicesImpl();
    ProductService productService = new ProductServiceImpl();
    ReviewService reviewService = new ReviewServicesImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product detail_product = productService.get(Integer.parseInt(id));
        req.setAttribute("detail_product", detail_product);

        List<Catalog> name_cate_of_product = cateService.getCateByProduct(Integer.parseInt(id));
        req.setAttribute("name_cate_of_product", name_cate_of_product);

        String idCate = detail_product.getCatalog_id();

        List<Product> productListCate = productService.getProductById(Integer.parseInt(idCate));

        req.setAttribute("productById", productListCate);

        List<Review> reviewById = reviewService.getReviewById(Integer.parseInt(id));
        req.setAttribute("reviewbyid", reviewById);

        int reviewQuantity = 0;
        for(Review a : reviewById){
            reviewQuantity++;
        }
        List<Product> productList = productService.getAll();
        req.setAttribute("productlist", productList);
        //Giá giảm
        List<Product> productsList1 = new ArrayList<Product>();
        for (Product product : productList) {
            Product product1 = productService.get(Integer.parseInt(product.getId()));
            String y = product.getPrice().replace(".", "");
            int price = Integer.parseInt(y);  // Giá cũ sản phẩm
            int percent = Integer.parseInt(product.getDiscount());
            int a = 100;
            double balance = (double) percent / 100;
            double percentBalance = 1 - balance;
            product1.setPrice(String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(percentBalance * price)));
            productsList1.add(product1);
        }
        req.setAttribute("productlist1", productsList1);
        req.setAttribute("reviewQuantity", reviewQuantity);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product-detail.jsp");
        dispatcher.forward(req, resp);
    }

}
