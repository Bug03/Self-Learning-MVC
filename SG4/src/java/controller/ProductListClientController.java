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
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServicesImpl;
import service.impl.ProductServiceImpl;

public class ProductListClientController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServicesImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String id = req.getParameter("id");
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        List<Product> product_paging = productService.pagingpProducts(index);
        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);
        List<Product> productList = productService.getAll();

        int sumProducts = productService.getTotalProduct();
        //Giá giảm
        List<Product> productsList1 = new ArrayList<Product>();
        for (Product product : product_paging) {
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
        req.setAttribute("productlist", product_paging);
        req.setAttribute("productlist1", productsList1);
        // Product bán chạy
        List<Product> product_banchay = productService.getProductById(6);
        req.setAttribute("product_banchay", product_banchay);
        // Phân trang tự động
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
        req.setAttribute("beginP",beginPage);
        req.setAttribute("endP", endPage);
        req.setAttribute("maxP", endPage);
        req.setAttribute("tag", index);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product.jsp");
        dispatcher.forward(req, resp);
    }

}
