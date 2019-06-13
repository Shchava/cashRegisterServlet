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
    <div>
        <ul class="pagination">
            <c:if test="${1 ne page}">
                <li class="page-item"><a class="page-link"
                                         href="/cashier/api/listReceipts?recordsPerPage=${recordsPerPage}&page=${page - 1}">Previous</a>
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
                                                 href="/cashier/api/listReceipts?recordsPerPage=${recordsPerPage}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${page lt numberOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="/cashier/api/listReceipts?recordsPerPage=${recordsPerPage}&page=${page+1}">Next</a>
                </li>
            </c:if>
        </ul>


        <ul class="pagination">
            <label>records on page :</label>
            <c:forEach begin="5" end="20" step="5" var="r">
                <c:choose>
                    <c:when test="${recordsPerPage eq r}">
                        <li class="page-item active"><a class="page-link">
                                ${r} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/cashier/api/listReceipts?recordsPerPage=${r}&page=${1}">${r}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

        </ul>
    </div>
</div>
</body>
</html>
