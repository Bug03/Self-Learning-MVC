package controller.admin; 
 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import model.Admin;
import service.AdminService;
import service.impl.AdminServicesImpl; 
 
public class AdminAddController extends HttpServlet { 

	private static final long serialVersionUID = 1L; 
	AdminService adminService = new AdminServicesImpl(); 
 
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addadmin.jsp"); 
		dispatcher.forward(req, resp); 
	} 
 
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String admin_username = req.getParameter("admin-username"); 
		String admin_password = req.getParameter("admin-password"); 
		String admin_name = req.getParameter("admin-name"); 
		Admin admin = new Admin(); 
		admin.setUsername(admin_username); 
		admin.setPassword(admin_password); 
		admin.setName(admin_name); 
                String department = (String) req.getSession().getAttribute("department_id");
                admin.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()));
		adminService.insert(admin); 
		resp.sendRedirect(req.getContextPath() + "/admin/admin/list"); 
	} 
	
 
} 
