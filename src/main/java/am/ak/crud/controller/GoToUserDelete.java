package am.ak.crud.controller;

import am.ak.crud.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goToUserDelete")
public class GoToUserDelete extends HttpServlet {

    private int recordsPerPage;
    private UserDao dao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        int uid = Integer.parseInt(userId);
        dao.removeUser(uid);
        Object currentPage = request.getSession().getAttribute("currentPage");
        if (currentPage == null) {
            currentPage = 1;
        }
        int page = (int) currentPage;

        recordsPerPage = Integer.parseInt(request.getServletContext().getInitParameter("recordsPerPage"));

        request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));
        request.setAttribute("currentPage", uid);
        request.setAttribute("noOfPages", 1); // todo

        System.out.println("Record Deleted Successfully");

        RequestDispatcher rd = request.getRequestDispatcher("/goToListView");
        rd.forward(request, response);
    }
}
