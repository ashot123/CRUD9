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

@WebServlet("/goToUserUpdate")
public class GoToUserUpdate extends HttpServlet {
    private UserDao dao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        UserBean user = new UserBean();
        //user.setId(id);
        int id = Integer.parseInt(userId);
        UserBean userBean = dao.getUserById(id);

        user.setId(id);
        user.setFirstName(userBean.getFirstName());
        user.setLastName(userBean.getFirstName());
        request.setAttribute("userBean", userBean);            //user.getFirstName(); // setFirstName(request.getParameter("firstName"));
        //user.getLastName(); //setLastName(request.getParameter("lastName"));
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/updateUserForm.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
