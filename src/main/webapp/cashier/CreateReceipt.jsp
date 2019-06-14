<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/11/19
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title><fmt:message key="create.receipt.title"/></title>
</head>
<body>
    <table class = "table">
        <tr>
            <th><fmt:message key="create.receipt.id"/></th>
            <th><fmt:message key="create.receipt.name"/></th>
            <th><fmt:message key="create.receipt.amount"/></th>
            <th><fmt:message key="create.receipt.price"/></th>
            <th><fmt:message key="create.receipt.sum"/></th>
        </tr>


        <c:forEach items="${requestScope.AddedEntries}" var="entry">
            <tr>
            <th><c:out value="${entry.goods.id}"/></th>
            <th><c:out value="${entry.goods.name}"/></th>
            <th><c:out value="${entry.amount}"/> <fmt:message key="${'goods.amount.marking.'.concat(entry.goods.amountMarking)}"/></th>
            <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${entry.goods.price/100.0}"/>
                <fmt:message key="${'goods.price.marking.'.concat(entry.goods.priceMarking)}"/></th>
            <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${entry.price/100.0}"/>
                <fmt:message key="money.UAH"/></th>
            </tr>
        </c:forEach>
    </table>
    <div style="align-content: baseline; align-self: baseline;  position: relative; bottom: 0; ">
    <c:if test="${empty requestScope.found}">
        <form method="get" action="/cashier/api/findgoods">
            <div class="form-group">
                <input type="text" name="ID" class="form-control" placeholder="<fmt:message key="create.receipt.id"/>" value="" />
            </div>
            <div class="form-group">
                <input type="text" name="name" class="form-control" placeholder="<fmt:message key="create.receipt.name"/>" value="" />
            </div>
            <div class="form-group">
                <input type="submit" class="form-control"  value="<fmt:message key="create.receipt.find"/>" />
            </div>
        </form>
    </c:if>

    <c:if test="${not empty requestScope.found}">
        <form method="post" action="/cashier/api/addgoods">
            <table  class = "table">
                <tr>
                    <th><c:out value="${requestScope.found.id}"/></th>
                    <th><c:out value="${requestScope.found.name}"/></th>
                    <th>
                            <input type="text" name="amount" class="form-control" placeholder="<fmt:message key="create.receipt.amount"/>" value="" />

                    </th>
                    <th><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${requestScope.found.price/100.0}"/>
                        <fmt:message key="money.UAH"/></th>
                    <th> <fmt:message key="money.UAH"/></th>
                </tr>
            </table>
                <input type="submit"  value="<fmt:message key="create.receipt.add.goods"/>" />
        </form>
    </c:if>

    <form method="post" action="/cashier/api/closereceipt">
        <div class="form-group">
            <input type="submit" value="<fmt:message key="create.receipt.close"/>" class="form-control" >
        </div>
    </form>
    </div>
</body>
</html>
