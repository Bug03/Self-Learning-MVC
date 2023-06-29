package controller;

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

public class BoardnewListClientDetailController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BoardnewService boardnewService = new BoardnewServicesImpl();
    AdminService adminService = new AdminServicesImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Boardnew boardnew = boardnewService.get(Integer.parseInt(id));
        req.setAttribute("boardnew", boardnew);

        List<Boardnew> boardnewList = boardnewService.getAll();
        req.setAttribute("boardnewlist", boardnewList);
        
        List<Admin> adminList = adminService.getAll();
        req.setAttribute("adminlist", adminList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/blog-single.jsp");
        dispatcher.forward(req, resp);
    }

}
