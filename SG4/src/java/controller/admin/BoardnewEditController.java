package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;

import model.Boardnew;
import service.AdminService;
import service.BoardnewService;
import service.impl.AdminServicesImpl;
import service.impl.BoardnewServicesImpl;

public class BoardnewEditController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BoardnewService boardnewService = new BoardnewServicesImpl();
    AdminService adminService = new AdminServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Boardnew boardnew = boardnewService.get(Integer.parseInt(id));
        List<Admin> admins = adminService.getAll();
        req.setAttribute("boardnew", boardnew);
        req.setAttribute("admins", admins);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editboardnew.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        Boardnew boardnew = new Boardnew();
        boardnew.setId(req.getParameter("new-id"));
        boardnew.setTitle(req.getParameter("new-title"));
        boardnew.setContent(req.getParameter("new-content"));
        boardnew.setImage_link(req.getParameter("new-image_link"));
//        boardnew.setAuthor(req.getParameter("new-author"));
//        boardnew.setCreated(req.getParameter("new-created"));
        boardnew.setDescription(req.getParameter("new-description"));
        boardnewService.edit(boardnew);
        resp.sendRedirect(req.getContextPath() + "/admin/new/list");
    }
}
