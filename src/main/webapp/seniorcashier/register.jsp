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

<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="register.register.title"/></title>
</head>
<body>
    <form action="/seniorcashier/api/register" method="POST">
        <input type="text" name="username" placeholder="<fmt:message key="register.username"/>"><br>
        <input type="text" name="email" placeholder="<fmt:message key="register.email"/>"><br>
        <input type="password" name="password" placeholder="<fmt:message key="register.password"/>"><br>
        <select name="role">
            <option value="SENIOR_CASHIER"><fmt:message key="user.types.SENIOR_CASHIER"/></option>
            <option value="CASHIER"><fmt:message key="user.types.CASHIER"/></option>
            <option value="MERCHANDISER"><fmt:message key="user.types.MERCHANDISER"/></option>
        </select><br>
        <input type = submit value = "<fmt:message key="register.register.button"/>">
    </form>



    
    <c:if test="${not empty requestScope.registered}">
        registered : <c:out value="${requestScope.registered}"/>
        </c:if>


</body>
</html>
