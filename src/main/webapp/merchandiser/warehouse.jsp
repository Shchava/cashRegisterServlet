<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/10/19
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title>Warehouse</title>
</head>
<body>
<div>
<table class = "table">
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>amount</th>
        <th>price</th>
        <th>sum</th>
    </tr>
    <c:forEach items="${requestScope.StoredGoods}" var="goods">
    <tr>
        <th><c:out value="${goods.id}"/></th>
        <th><c:out value="${goods.name}"/></th>
        <th><c:out value="${goods.amount}"/></th>
        <th><c:out value="${goods.price}"/></th>
        <th><c:out value="${goods.sum}"/></th>

    </tr>

    </c:forEach>
</table>
    <button type="button" class="btn btn-primary btn-block" onclick="window.location.href = '';">add goods</button>
</div>
</body>
</html>
