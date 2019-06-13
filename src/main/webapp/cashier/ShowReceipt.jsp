<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/13/19
  Time: 7:36 PM
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
    <title>Receipt</title>
    <c:set var="foramter" value='${DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")}'/>
</head>
<body>
    <div>
        <label>Cashier : ${receipt.cashier.username} </label><br>
        created : <c:out value='${receipt.created.format(foramter)}'/>

        <table class = "table">
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>amount</th>
                <th>price</th>
                <th>sum</th>
            </tr>


            <c:forEach items="${requestScope.entries}" var="entry">
                <tr>
                    <th><c:out value="${entry.goods.id}"/></th>
                    <th><c:out value="${entry.goods.name}"/></th>
                    <th><c:out value="${entry.amount}"/> <c:out value="${entry.goods.amountMarking}"/></th>
                    <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${entry.goods.price/100.0}"/>
                        <c:out value="${entry.goods.priceMarking}"/></th>
                    <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${entry.price/100.0}"/> UAH</th>
                </tr>
            </c:forEach>
        </table>
        <label> Sum : <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${receipt.sum/100.0}"/> UAH </label>
    </div>
</body>
</html>