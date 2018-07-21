package am.ak.crud.controller;

import am.ak.crud.dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goToListView")
public class GoToListView extends HttpServlet {

    private UserDao dao = new UserDao();

    private int page;
    private int recordsPerPage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        page = 1;
        //recordsPerPage = 5;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        recordsPerPage = Integer.parseInt(request.getServletContext().getInitParameter("recordsPerPage"));

        String initStr = request.getParameter("page");
        if(initStr == null || initStr.isEmpty() ) {
            page = 1;
        } else {
            page =  Integer.parseInt(initStr);
        }
        int noOfRecords = dao.getNumberOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/showUserList.jsp");
        rd.forward(request, response);
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        doGet(request, response);
    }
}

