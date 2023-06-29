package controller.admin;

import controller.UserEditProfileController;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Catalog;
import model.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.CategoryService;
import service.FileService;
import service.ProductService;
import service.impl.CategoryServicesImpl;
import service.impl.FileServicesImpl;
import service.impl.ProductServiceImpl;

public class ProductAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();
    FileService fileService = new FileServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService cateService = new CategoryServicesImpl();
        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addproduct.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");        
        String avatarProduct = "";
        HashMap<String, String> fields = new HashMap<>();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                System.out.println("Item : "+item);
                if (!item.isFormField()) {
                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/view/client/assets/images/products/img-test");
                    if (!path.exists()) {
                        boolean status = path.mkdirs();
                    }
                    avatarProduct = item.getName();
                    System.out.println("FileName : "+avatarProduct);
                    File fileSaved = fileService.uploadFile(item, path.getAbsolutePath());
                    File real = new File(root + "../../web/view/client/assets/images/products/img-test/"+avatarProduct);
                    fileService.copyFile(fileSaved, real);
                } else {
                    fields.put(item.getFieldName(), item.getString("UTF-8"));
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(UserEditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserEditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(avatarProduct);

        String product_cate = fields.get("product-cate");
        String product_name = fields.get("product-name");
        String product_price = fields.get("product-price");
        String product_status = "1";
        String product_desc = fields.get("product-desc");
        String product_content = fields.get("product-content");
        String product_discount = fields.get("product-discount");
        String product_inventory = fields.get("product-sum");
        String product_day = fields.get("product-day");

        Product product = new Product();
        product.setCatalog_id(product_cate);
        product.setName(product_name);
        product.setPrice(product_price);
        if(Integer.parseInt(product_inventory) == 0){
            product_status = "0";
        }
        product.setStatus(product_status);
        product.setDescription(product_desc);
        product.setContent(product_content);
        product.setDiscount(product_discount);
        product.setImage_link(avatarProduct);
        product.setCreated(product_day);
        product.setInventory(Integer.parseInt(product_inventory));
        productService.insert(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}
