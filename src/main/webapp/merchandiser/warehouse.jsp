<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/10/19
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
        <th></th>
    </tr>
    <c:forEach items="${requestScope.StoredGoods}" var="goods">
    <tr>
        <c:choose>
            <c:when test="${editingId ne goods.id}">
            <th><c:out value="${goods.id}"/></th>
            <th><c:out value="${goods.name}"/></th>
            <th><c:out value="${goods.amount}"/> <c:out value="${goods.amountMarking}"/></th>
            <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${goods.price/100.0}"/> <c:out value="${goods.priceMarking}"/></th>
            <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${goods.sum/100.0}"/> UAH</th>
            <th><a class="btn btn-primary btn-block" role="button"
                   href="/merchandiser/api/showWarehouse?recordsPerPage=${recordsPerPage}&page=${page}&editingId=${goods.id}">edit</a></th>
            </c:when>
            <c:otherwise>
                <form action="/merchandiser/api/editEntry?recordsPerPage=${recordsPerPage}&page=${page}&editingId=${goods.id}">
                    <input type="hidden" name="id" value="${goods.id}">
                    <th><c:out value="${goods.id}"/></th>
                    <th><c:out value="${goods.name}"/></th>
                    <th><input type="text" name = "amount" value = ${goods.amount}><c:out value="${goods.amountMarking}"/></th>
                    <th><input type="text" name = "price" value = "${goods.price}">*0.01 <c:out value="${goods.priceMarking}"/></th>
                    <th><label>sum</label></th>
                    <th><input type = submit class="btn btn-primary btn-block" value = "save"></th>
                </form>
            </c:otherwise>
        </c:choose>
    </tr>

    </c:forEach>
</table>
    <button type="button" class="btn btn-primary btn-block" onclick="window.location.href = '/merchandiser/addGoods.jsp';">add goods</button>

    <ul class="pagination">
        <c:if test="${1 != page}">
            <li class="page-item"><a class="page-link"
                                     href="/merchandiser/api/showWarehouse?recordsPerPage=${recordsPerPage}&page=${page - 1}">Previous</a>
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
                                             href="/merchandiser/api/showWarehouse?recordsPerPage=${recordsPerPage}&page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page lt numberOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="/merchandiser/api/showWarehouse?recordsPerPage=${recordsPerPage}&page=${page+1}">Next</a>
            </li>
        </c:if>
    </ul>

    <ul class="pagination">
        records on page :
        <c:forEach begin="5" end="20" step="5" var="r">
            <c:choose>
                <c:when test="${recordsPerPage eq r}">
                    <li class="page-item active"><a class="page-link">
                            ${r} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="/merchandiser/api/showWarehouse?recordsPerPage=${r}&page=${1}">${r}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

    </ul>
</div>

</body>
</html>
