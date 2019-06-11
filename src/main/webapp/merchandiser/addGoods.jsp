<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/11/19
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title>Add goods</title>
</head>
<body>
<div class="container">
    <form action = "/merchandiser/addGoods/" method="POST">
        <div class="form-group">
            <input type="text" name="name" class="form-control" placeholder="name" value="" />
        </div>
        <div class="form-group">
            <select class="form-control" name="type" id = "goodsType">
                <option value="APIECE">apiece</option>
                <option value="BY_WEIGHT">by weight</option>
            </select>
        </div>
        <div class="form-group" id="price">
            <input type="text" name="price" class="form-control" placeholder="price" value="" />
        </div>
        <div class="form-group" id="amount">
            <input type="text" name="amount" class="form-control" placeholder="amount" value="" />
        </div>
        <div class="form-group">
            <input type="submit" class="form-control"  value="Add" />
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
