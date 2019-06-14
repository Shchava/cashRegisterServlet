<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/11/19
  Time: 11:34 AM
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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title><fmt:message key="add.goods.title"/></title>
</head>
<body>
<div class="container">
    <form action = "/merchandiser/api/addGoods" method="POST">
        <div class="form-group">
            <input type="text" name="name" class="form-control" placeholder="<fmt:message key="add.goods.name"/>" value="" />
        </div>
        <div class="form-group">
            <select class="form-control" name="type" id = "goodsType">
                <option value="APIECE"><fmt:message key="goods.types.APIECE"/></option>
                <option value="BY_WEIGHT"><fmt:message key="goods.types.BY_WEIGHT"/></option>
            </select>
        </div>
        <div class="form-group" id="price">
            <input type="text" name="price" class="form-control" placeholder="<fmt:message key="add.goods.price"/>" value="" />
        </div>
        <div class="form-group" id="amount">
            <input type="text" name="amount" class="form-control" placeholder="<fmt:message key="add.goods.amount"/>" value="" />
        </div>
        <div class="form-group">
            <input type="submit" class="form-control"  value="<fmt:message key="add.goods.add"/>" />
        </div>
    </form>
    <script>
        var typeSelect = document.getElementById("goodsType");
        var price = document.getElementById("price");
        var amount = document.getElementById("amount");
        typeSelect.addEventListener("change",function () {
            console.log(typeSelect.value);
            if(typeSelect.value == "APIECE"){
                price.placeholder = "price per one";
                price.update();
                amount.placeholder = "count";
            }else{
                price.placeholder = "price per gram";
                amount.placeholder = "weight";
            }
        })
    </script>
</div>
</body>
</html>
