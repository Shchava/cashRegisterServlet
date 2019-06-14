<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/13/19
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <c:set var="foramter" value='${DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")}'/>
    <title><fmt:message key="list.all.receipts.title"/></title>
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
    <table class = "table">
        <tr>
            <th><fmt:message key="list.all.receipts.id"/></th>
            <th><fmt:message key="list.all.receipts.cashier"/></th>
            <th><fmt:message key="list.all.receipts.created"/></th>
            <th><fmt:message key="list.all.receipts.sum"/></th>
            <th></th>
        </tr>

        <c:forEach items="${requestScope.FoundReceipts}" var="receipt">
            <tr>
                <th><c:out value="${receipt.id}"/></th>
                <th><c:out value="${receipt.cashier.username}"/></th>
                <th><c:out value="${receipt.created.format(foramter)}"/></th>
                <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${receipt.sum/100.0}"/> <fmt:message key="money.UAH"/></th>
                <th><a class="btn btn-primary btn-block" role="button"
                       href="/cashier/api/showReceipt?id=${receipt.id}"><fmt:message key="list.all.receipts.open"/></a></th></th>
            </tr>
        </c:forEach>
    </table>
    <div>
        <ul class="pagination">
            <c:if test="${1 ne page}">
                <li class="page-item"><a class="page-link"
                                         href="/seniorcashier/api/getAllReceipts?recordsPerPage=${recordsPerPage}&page=${page - 1}"><fmt:message key="pagination.previous"/></a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${page eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/seniorcashier/api/getAllReceipts?recordsPerPage=${recordsPerPage}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${page lt numberOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="/seniorcashier/api/getAllReceipts?recordsPerPage=${recordsPerPage}&page=${page+1}"><fmt:message key="pagination.next"/></a>
                </li>
            </c:if>
        </ul>


        <ul class="pagination">
            <label><fmt:message key="pagination.entries.selector"/></label>
            <c:forEach begin="5" end="20" step="5" var="r">
                <c:choose>
                    <c:when test="${recordsPerPage eq r}">
                        <li class="page-item active"><a class="page-link">
                                ${r} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/seniorcashier/api/getAllReceipts?recordsPerPage=${r}&page=${1}">${r}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

        </ul>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>