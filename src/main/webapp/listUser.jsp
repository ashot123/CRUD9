<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" type="java.util.List<net.roseindia.bean.UserBean>" scope="request"/>
<jsp:useBean id="currentPage" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="noOfPages" scope="request" type="java.lang.Integer"/>

<html>
<head>
    <title>All User</title>
</head>
<body>
<p><b>All Users</b></p>

<table border="1">
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th align="center">Update User</th>
        <th align="center">Remove User</th>
    </tr>
    <c:forEach var="currentList" items="${users}">
        <tr>
            <td>${currentList.id}</td>
            <td>${currentList.firstName}</td>
            <td>${currentList.lastName}</td>
            <td align="center"><a href="UserHandler?action=editform&userId=${currentList.id}">Update</a></td>
            <td align="center"><a href="UserHandler?action=delete&userId=${currentList.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<table border="0" cellpadding="0" cellspacing="0">
    <td>
        <c:if test="${currentPage != 1}">
            <a href="UserHandler?action=listUser&page=${currentPage - 1}">&nbsp;Previous</a>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    ${i}&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="UserHandler?action=listUser&page=${i}">${i}&nbsp;</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <a href="UserHandler?action=listUser&page=${currentPage + 1}">&nbsp;Next</a>
        </c:if>
    </td>
</table>
<p><a href="UserHandler?action=create">Add User</a></p>
</body>
</html>