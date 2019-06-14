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

<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="messages"/>

<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title><fmt:message key="show.receipt.title"/></title>
    <c:set var="foramter" value='${DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")}'/>
</head>
<body>
    <div>
        <label><fmt:message key="show.receipt.cashier"/>${receipt.cashier.username} </label><br>
        <fmt:message key="show.receipt.created"/><c:out value='${receipt.created.format(foramter)}'/>

        <table class = "table">
            <tr>
                <th><fmt:message key="show.receipt.id"/></th>
                <th><fmt:message key="show.receipt.name"/></th>
                <th><fmt:message key="show.receipt.amount"/></th>
                <th><fmt:message key="show.receipt.price"/></th>
                <th><fmt:message key="show.receipt.sum"/></th>
            </tr>


            <c:forEach items="${requestScope.entries}" var="entry">
                <tr>
                    <th><c:out value="${entry.goods.id}"/></th>
                    <th><c:out value="${entry.goods.name}"/></th>
                    <th><c:out value="${entry.amount}"/> <fmt:message key="${'goods.amount.marking.'.concat(entry.goods.amountMarking)}"/></th>
                    <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${entry.goods.price/100.0}"/>
                        <fmt:message key="${'goods.price.marking.'.concat(entry.goods.priceMarking)}"/></th>
                    <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${entry.price/100.0}"/> <fmt:message key="money.UAH"/></th>
                </tr>
            </c:forEach>
        </table>
        <label><fmt:message key="show.receipt.full.sum"/> <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${receipt.sum/100.0}"/> <fmt:message key="money.UAH"/> </label>
    </div>
</body>
</html>