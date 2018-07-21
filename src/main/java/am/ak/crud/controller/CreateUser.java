package am.ak.crud.controller;


import am.ak.crud.bean.UserBean;
import am.ak.crud.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createUser")
public class CreateUser extends HttpServlet {
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
        //int id = Integer.parseInt(uId);
        recordsPerPage = Integer.parseInt(request.getSession().getServletContext().getInitParameter("recordsPerPage"));

        UserBean user = new UserBean();
        //user.setId(id);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        /*int generatedId = */
        dao.addUser(user);

        request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));

        int noOfRecords = dao.getNumberOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.getSession().setAttribute("currentPage", page);

        RequestDispatcher rd = request.getRequestDispatcher("/goToListView");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
