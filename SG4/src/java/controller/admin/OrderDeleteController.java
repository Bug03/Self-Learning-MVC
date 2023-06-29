package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ordered;
import model.Product;
import model.Transactions;
import service.BoardnewService;
import service.OrderedService;
import service.ProductService;

import service.TransactionService;
import service.impl.BoardnewServicesImpl;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;
import utils.OrderStatus;

public class OrderDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TransactionService transactionService = new TransactionServicesImpl();
    ProductService productService = new ProductServiceImpl();
    BoardnewService boardnewService = new BoardnewServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        List<Ordered> list_ordered = orderedService.getAll();
//        for (Ordered a : list_ordered) {
//            int orderedID = Integer.parseInt(a.getTransaction_id());
//            if (orderedID == Integer.parseInt(id)) {
//                Transactions trans = transactionService.get(Integer.parseInt(id));
//                String status = trans.getStatus();
//                Product product = productService.get(Integer.parseInt(a.getProduct_id()));
//                if(!status.equals(OrderStatus.CANCEL)){
//                    long sold = product.getSold();
//                    int inventory = product.getInventory();
//                    sold -= a.getQty();
//                    inventory += a.getQty();
//                    if (inventory > 0) {
//                        product.setStatus("1");
//                    }
//                    product.setSold(sold);
//                    product.setInventory(inventory);
//                }
//                productService.edit(product);
//            }
//        }
        transactionService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/admin/order/list");
    }
}
