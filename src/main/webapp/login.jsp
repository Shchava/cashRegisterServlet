<%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/9/19
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title>login</title>
</head>
<body>
<div class="container login-container">
    <div class="row">
        <div class="col login-form-1">
            <h3>Login</h3>
            <form method="POST" action="/login/">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="username" value="" />
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="password" value="" />
                </div>
                <div class="form-group">
                    <input type="submit" class="btnSubmit" value="Login" />
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
