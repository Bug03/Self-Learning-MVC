package controller.admin;

import java.io.IOException;
import java.util.List;

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

public class ProductEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService cateService = new CategoryServicesImpl();
        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);
        String id = req.getParameter("id");
        Product product = productService.get(Integer.parseInt(id));
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editproduct.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        Product product = new Product();
        product.setId(req.getParameter("product-sku"));
        product.setCatalog_id(req.getParameter("product-cate"));
        product.setName(req.getParameter("product-name"));
        product.setPrice(req.getParameter("product-price"));
        product.setStatus(req.getParameter("product-status"));
        product.setDescription(req.getParameter("product-desc"));
        product.setContent(req.getParameter("product-content"));
        product.setDiscount(req.getParameter("product-discount"));
        product.setImage_link(req.getParameter("product-image"));
        product.setCreated(req.getParameter("product-day"));
        product.setSold(Long.parseLong(req.getParameter("product-sold")));
        product.setInventory(Integer.parseInt(req.getParameter("product-inventory")));
        productService.edit(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}
