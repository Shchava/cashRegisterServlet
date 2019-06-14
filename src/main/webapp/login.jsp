<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/9/19
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title><fmt:message key="login.login.title"/></title>
</head>


<body>
<div class="container login-container">
    <div class="row">
        <div class="col login-form-1">
            <h3><fmt:message key="login.login.label" /></h3>
            <form method="POST" action="/login/">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="<fmt:message key="login.username" />" value="" />
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="<fmt:message key="login.password" />" value="" />
                </div>
                <div class="form-group">
                    <input type="submit" class="form-control"  value="<fmt:message key="login.login.button" />" />
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
