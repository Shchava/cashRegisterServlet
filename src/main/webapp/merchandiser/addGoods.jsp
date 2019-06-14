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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
