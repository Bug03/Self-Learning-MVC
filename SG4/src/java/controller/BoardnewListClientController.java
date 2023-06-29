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

public class BoardnewListClientController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BoardnewService boardnewService = new BoardnewServicesImpl();
     AdminService adminService = new AdminServicesImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int sumBoardnews = boardnewService.getTotalBoardnew();
        int index = Integer.parseInt(indexPage);
        List<Boardnew> boardnewList = boardnewService.pagingBoardnew(index);
        req.setAttribute("boardnewlist", boardnewList);
        int maxPage = sumBoardnews / 6;
        if (sumBoardnews % 6 != 0) {
            maxPage++;
        }
        int endPage;
        if (maxPage > 6) {
            endPage = 6;
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
        List<Admin> adminList = adminService.getAll();
        req.setAttribute("adminlist", adminList);
        req.setAttribute("beginP", beginPage);
        req.setAttribute("endP", endPage);
        req.setAttribute("maxP", endPage);
        req.setAttribute("tag", index);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/blog-archive.jsp");
        dispatcher.forward(req, resp);
    }

}
