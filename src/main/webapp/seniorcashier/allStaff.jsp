<%@ page import="java.util.List" %>
<%@ page import="ua.training.cashregister.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/10/19
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>

<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="list.all.staff.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>

<table class="table">
    <tr>
        <th><fmt:message key="list.all.staff.id"/></th>
        <th><fmt:message key="list.all.staff.username"/></th>
        <th><fmt:message key="list.all.staff.email"/></th>
        <th><fmt:message key="list.all.staff.role"/></th>
    </tr>

<c:forEach items="${staff}" var="user">
    <tr>
        <th><c:out value="${user.id}"/></th>
        <th><c:out value="${user.username}"/></th>
        <th><c:out value="${user.email}"/></th>
        <th><fmt:message key="${'user.types.'.concat(user.role)}"/></th>\
    </tr>
</c:forEach>

</table>

<div>
    <ul class="pagination">
        <c:if test="${1 != page}">
            <li class="page-item"><a class="page-link"
                                     href="/seniorcashier/api/getStaff?recordsPerPage=${recordsPerPage}&page=${page - 1}">Previous</a>
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
                                             href="/seniorcashier/api/getStaff?recordsPerPage=${recordsPerPage}&page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page lt numberOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="/seniorcashier/api/getStaff?recordsPerPage=${recordsPerPage}&page=${page+1}">Next</a>
            </li>
        </c:if>
    </ul>

    <ul class="pagination">
        <fmt:message key="pagination.entries.selector"/>
        <c:forEach begin="5" end="20" step="5" var="r">
            <c:choose>
                <c:when test="${recordsPerPage eq r}">
                    <li class="page-item active"><a class="page-link">
                            ${r} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="/seniorcashier/api/getStaff?recordsPerPage=${r}&page=${1}">${r}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

    </ul>
</div>


</body>
</html>
