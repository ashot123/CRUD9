package am.ak.crud.controller;

import am.ak.crud.bean.UserBean;
import am.ak.crud.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    private UserDao dao = new UserDao();

    private int page;
    private int recordsPerPage;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        recordsPerPage = Integer.parseInt(request.getSession().getServletContext().getInitParameter("recordsPerPage"));

        String userId = request.getParameter("userId");
        int uid = Integer.parseInt(userId);
        UserBean user = new UserBean();
        user.setId(uid);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        dao.editUser(user);
        request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));

        int noOfRecords = dao.getNumberOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        RequestDispatcher rd = request.getRequestDispatcher("/goToListView");
        rd.forward(request, response);
    }
}
