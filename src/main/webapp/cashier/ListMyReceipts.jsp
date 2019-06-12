<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/12/19
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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
        </tr>

        <c:forEach items="${requestScope.FoundReceipts}" var="receipt">
            <tr>
                <th><c:out value="${receipt.id}"/></th>
                <th><c:out value="${receipt.cashier.username}"/></th>
                <th><c:out value="${receipt.created}"/></th>
                <th><c:out value="${receipt.sum}"/></th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
