<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title>Add New User</title>
</head>
<body>
<form method="POST" action="createUser" >
    <input type="hidden" name="action" value="insert" />
    <p><b><h2>Add New Record(new one)</h2></b></p>
    <table>
        <tr>
            <td>First Name</td>
            <td><label>
                <input type="text" name="firstName" />
            </label></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><label>
                <input type="text" name="lastName" />
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
<p><a href="goToListView">View-All-Records</a></p>
</body>
</html>