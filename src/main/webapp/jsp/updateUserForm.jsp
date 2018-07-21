<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@ page import="UserBean" %>--%>
<%--<%@ page import="UserDao"%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" scope="request" type="am.ak.crud.bean.UserBean"/>

<html>
<head>
    <title>Edit User</title>
</head>
<body>
<p><b>All Users</b></p>

<form method="POST" action="updateUser" ><input
        type="hidden" name="action" value="edit"/>

    <table>
        <tr>
            <td>User ID</td>
            <td>
                <label>
                    <input style="background-color: #d4d4d4" type="text" name="userId" readonly="readonly"
                           value= ${userBean.id}>
                </label>
            </td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><label>
                <input type="text" name="firstName" value= ${userBean.firstName}>
            </label></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><label>
                <input type="text" name="lastName" value=${userBean.lastName}>
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update"/></td>
        </tr>
    </table>
</form>
<p><a href="goToListView">View-All-Records</a></p>
</body>
</html>