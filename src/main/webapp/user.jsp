<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add New User</title>
</head>
<body>
<form method="POST" action='UserHandler' name="frmAddUser">
    <input type="hidden" name="action" value="insert" />
    <p><b>Add New Record</b></p>
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
<p><a href="UserHandler?action=listUser">View-All-Records</a></p>
</body>
</html>