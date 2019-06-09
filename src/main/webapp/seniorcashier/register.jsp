<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/9/19
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/seniorcashier/register">
        <input type="text" name="username"><br>
        <input type="password" name="password"><br>
        <select name="role">
            <option value="seniorCashier">senior cashier</option>
            <option value="cashier">cashier</option>
            <option value="merchandiser">merchandiser</option>
        </select><br>
        <input type = submit>
    </form>
</body>
</html>
