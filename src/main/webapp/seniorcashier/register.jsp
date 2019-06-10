<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/9/19
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="/seniorcashier/register/" method="POST">
        <input type="text" name="username"><br>
        <input type="text" name="email"><br>
        <input type="password" name="password"><br>
        <select name="role">
            <option value="SENIOR_CASHIER">senior cashier</option>
            <option value="CASHIER">cashier</option>
            <option value="MERCHANDISER">merchandiser</option>
        </select><br>
        <input type = submit>
    </form>



    
    <c:if test="${not empty requestScope.registered}">
        registered : <c:out value="${requestScope.registered}"/>
        </c:if>


</body>
</html>
