<%@ page import="java.util.List" %>
<%@ page import="ua.training.cashregister.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: yaroslav
  Date: 6/10/19
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>All staff</title>
</head>
<body>
<h1>     <c:out value="${requestScope.users}"> value</c:out>  </h1>
${requestScope.values()}
${requestScope.ts}
<table>
    <tr>
        <th>ID</th>
        <th>username</th>
        <th>email</th>
        <th>role</th>
    </tr>
<%
    @SuppressWarnings("unchecked")
    List<User> staff = (List<User>) request.getAttribute("staff");
    for(User user: staff){
        out.print("<tr> ");
        out.print("<th>" + user.getId()+ "</th><th>" + user.getUsername() + "</th> <th>" + user.getEmail() + "</th> <th>" + user.getRole().name() + "</th>");
        out.println("/<tr>");
    }

%>
</table>

</body>
</html>
