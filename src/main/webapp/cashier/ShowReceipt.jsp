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
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item passive">
                    <label class="navbar-brand">${sessionScope.LoggedUser.username}</label>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/index.jsp"><fmt:message key="header.home"/><span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <ul class="navbar-nav ">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${pageContext.response.locale.country}</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown">
                        <a class="dropdown-item" href="?lang=UA"><fmt:message key="header.language.UA"/></a>
                        <a class="dropdown-item" href="?lang=EN"><fmt:message key="header.language.EN"/></a>
                    </div>
                </li>
                <li>
                    <a class="nav-link" href="/logout"><fmt:message key="header.logout"/><span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</div>

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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>