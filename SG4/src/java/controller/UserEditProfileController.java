package controller;
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
import javax.servlet.http.HttpSession;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.CityService;
import service.FileService;
import service.UserService;
import service.impl.CityServicesImpl;
import service.impl.FileServicesImpl;
import service.impl.UserServicesImpl;

public class UserEditProfileController extends HttpServlet {

    UserService userService = new UserServicesImpl();
    CityService cityService = new CityServicesImpl();
    FileService fileService = new FileServicesImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String fileName = "";
        HashMap<String, String> fields = new HashMap<>();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                System.out.println("Item : "+item);
                if (!item.isFormField()) {
                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/view/client/assets/images/avatar");
                    if (!path.exists()) {
                        boolean status = path.mkdirs();
                    }
                    fileName = item.getName();
                    File fileSaved = fileService.uploadFile(item, path.getAbsolutePath());
                    File real = new File(root + "../../web/view/client/assets/images/avatar/"+fileName);
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
        
        System.out.println(fileName);
        User user = userService.get(Integer.parseInt(fields.get("id")));
        String email = fields.get("email");
        String phone = fields.get("phone");
        String address = fields.get("address");
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        if(!fileName.equals("")){
          user.setAvatar(fileName);  
        }     
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.setAttribute("city", cityService.getAll());
        request.setAttribute("type", "1");
        userService.edit(user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/myaccount.jsp");
        dispatcher.forward(request, response);
    }
}
