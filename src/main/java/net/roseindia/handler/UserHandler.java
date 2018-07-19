package net.roseindia.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.roseindia.dao.UserDao;
import net.roseindia.bean.UserBean;

public class UserHandler extends HttpServlet {
    private static String INSERT = "/user.jsp";
    private static String EDIT = "/edit.jsp";
    private static String USER_RECORD = "/listUser.jsp";
    private UserDao dao;
    private int page = 1;
    private int recordsPerPage = 5;

    public UserHandler() {
        super();
        dao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "";
        String uId = request.getParameter("userId");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("create")) {
            redirect = INSERT;
        } else if (/*!((uId) == null) && */action.equalsIgnoreCase("insert")) {
            //int id = Integer.parseInt(uId);
            UserBean user = new UserBean();
            //user.setId(id);
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            /*int generatedId = */
            dao.addUser(user);
            redirect = USER_RECORD;
            request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));

            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            System.out.println("Record Added Successfully");
        } else if (action.equalsIgnoreCase("delete")) {
            String userId = request.getParameter("userId");
            int uid = Integer.parseInt(userId);
            dao.removeUser(uid);
            redirect = USER_RECORD;
            request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));
            System.out.println("Record Deleted Successfully");
        } else if (action.equalsIgnoreCase("editform")) {

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
            redirect = EDIT;
        } else if (action.equalsIgnoreCase("edit")) {
            String userId = request.getParameter("userId");
            int uid = Integer.parseInt(userId);
            UserBean user = new UserBean();
            user.setId(uid);
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            dao.editUser(user);
            request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));

            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            //request.setAttribute("users", dao.getAllUsers());
            //request.setAttribute("user", user);
            redirect = USER_RECORD;
            System.out.println("Record updated Successfully");
        } else if (action.equalsIgnoreCase("listUser")) {
            //int page = 1;
            //int recordsPerPage = 3;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            request.setAttribute("users", dao.getAllUsers((page - 1) * recordsPerPage, recordsPerPage));
            redirect = USER_RECORD;
        }

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}