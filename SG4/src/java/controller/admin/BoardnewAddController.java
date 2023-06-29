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
import model.Admin;

import model.Boardnew;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.AdminService;
import service.BoardnewService;
import service.FileService;
import service.impl.AdminServicesImpl;
import service.impl.BoardnewServicesImpl;
import service.impl.FileServicesImpl;

public class BoardnewAddController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BoardnewService boardnewService = new BoardnewServicesImpl();
    FileService fileService = new FileServicesImpl();
    AdminService adminService = new AdminServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addboardnew.jsp");
        String username = (String) req.getSession().getAttribute("admin-username");
        List<Admin> admin = adminService.getAll();
        req.setAttribute("admin", admin);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String imageNews = "";
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
                System.out.println("Item : " + item);
                if (!item.isFormField()) {
                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/view/client/assets/images/news");
                    if (!path.exists()) {
                        boolean status = path.mkdirs();
                    }
                    imageNews = item.getName();
                    System.out.println("FileName : " + imageNews);
                    File fileSaved = fileService.uploadFile(item, path.getAbsolutePath());
                    File real = new File(root + "../../web/view/client/assets/images/news/" + imageNews);
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
        String new_title = fields.get("new-title");
        String new_content = fields.get("new-content");
        String new_author = fields.get("new-author");
        String new_created = fields.get("new-created");
        String new_description = fields.get("new-description");
        Boardnew boardnew = new Boardnew();
        boardnew.setTitle(new_title);
        boardnew.setContent(new_content);
        if(imageNews.equals("")){
            boardnew.setImage_link("Whats-News-Today_1493062809311_9576980_ver1.0_1280_720.png");
        } else {
          boardnew.setImage_link(imageNews);  
        }
        boardnew.setAuthor(new_author);
        boardnew.setCreated(new_created);
        boardnew.setDescription(new_description);
        boardnewService.insert(boardnew);
        resp.sendRedirect(req.getContextPath() + "/admin/new/list");
    }

}
