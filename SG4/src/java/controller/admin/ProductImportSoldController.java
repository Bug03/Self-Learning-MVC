
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

/**
 *
 * @author Admin
 */
public class ProductImportSoldController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CategoryService cateService = new CategoryServicesImpl();
        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);

        String id = req.getParameter("id");
        Product product = productService.get(Integer.parseInt(id));
        req.setAttribute("product", product);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editproductsold.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String imports = req.getParameter("product-import-sold");
        Product product = productService.get(Integer.parseInt(req.getParameter("product-sku")));
        int inventory = product.getInventory();
        inventory += Integer.parseInt(imports);
        product.setInventory(inventory);
        if(inventory > 0){
            product.setStatus("1");
        }
        productService.edit(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}
