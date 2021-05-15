<%--
  Created by IntelliJ IDEA.
  User: Jagge
  Date: 04.05.2021
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<form action="/reg" method="post">

    <div class="col-md-3">
        <label for="userName" class="col-md-6 offset-md-9">Логин</label>
        <input type="text" class="col-md-6 offset-md-9" id="userName" name="userName">

    </div>
    </div>

    <div class="col-md-3">
        <label for="validationCustom01" class="col-md-6 offset-md-9">Имя</label>
        <input type="text" class="col-md-6 offset-md-9" id="validationCustom01" name="firstName" required >
    </div>
    </div>
    <div class="col-md-3">
        <label for="validationCustom02" class="col-md-6 offset-md-9">Фамилия</label>
        <input type="text" class="col-md-6 offset-md-9" id="validationCustom02" name="lastName" required>
    </div>
    </div>

    <div class="col-md-3">
        <label al for="userBirthDate" class="col-md-6 offset-md-9">День рождения</label>
        <input type="date" class="col-md-6 offset-md-9" id="userBirthDate" name="birthDate" required>
    </div>
    </div>
    <div class="col-md-3">

        <label al for="userAddress" class="col-md-6 offset-md-9">Улица</label>
        <input type="text" class="col-md-6 offset-md-9" id="userAddress" name="userStreet" required>
    </div>
    </div>
    <div class="col-md-3">
        <label al for="userAddress" class="col-md-6 offset-md-9">Номер дома</label>
        <input type="number" class="col-md-6 offset-md-9" id="userAddress1" name="userHome" required>
    </div>
    </div>
    <div class="col-md-3">
        <label for="inputPassword1" class="col-md-6 offset-md-9">Пароль</label>
        <input type="password" class="col-md-6 offset-md-9" id="inputPassword1" name="password">

    </div>
    </div>
    <div class="col-md-3">
        <label for="inputPassword2" class="col-md-6 offset-md-9">Пароль еще раз</label>
        <input type="password" class="col-md-6 offset-md-9" id="inputPassword2" name="password2">
    </div>
    </div>
    ${requestScope.message}
    ${requestScope.list}
    <div class="col-md-3">
        <div class="col-md-6 offset-md-9">
            <button type="submit" style="margin: 5px" class="btn btn-primary">Зарегестрироваться</button>
        </div>
    </div>


</form>
<jsp:include page="../_footer.jsp"/>


</body>
</html>
