<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/12/19
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <c:set var="foramter" value='${DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")}'/>
    <title>Receipt list</title>
</head>
<body>
<div>
    <table class = "table">
        <tr>
            <th>ID</th>
            <th>cashier</th>
            <th>created</th>
            <th>sum</th>
            <th></th>
        </tr>

        <c:forEach items="${requestScope.FoundReceipts}" var="receipt">
            <tr>
                <th><c:out value="${receipt.id}"/></th>
                <th><c:out value="${receipt.cashier.username}"/></th>
                <th><c:out value="${receipt.created.format(foramter)}"/></th>
                <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${receipt.sum/100.0}"/> UAH</th>
                <th><a class="btn btn-primary btn-block" role="button"
                    href="/cashier/api/showReceipt?id=${receipt.id}">open</a></th></th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
