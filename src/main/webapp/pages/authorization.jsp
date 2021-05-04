<%--
  Created by IntelliJ IDEA.
  User: Jagge
  Date: 04.05.2021
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<form action="#" method="post">

    <form class="row g-3">
        <div class="col-md-6">
            <label for="inputUserName" class="form-label">User name</label>
            <input type="text" class="form-control" id="inputUserName">
        </div>
        <div class="col-md-6">
            <label for="inputPassword4" class="form-label">Password</label>
            <input type="password" class="form-control" id="inputPassword4">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Log in</button>
        </div>
</form>
<jsp:include page="_footer.jsp"/>
</body>
</html>
