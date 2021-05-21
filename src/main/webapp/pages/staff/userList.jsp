<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<jsp:include page="../_header.jsp" />

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/home">Главная</a></li>
                    <li class="breadcrumb-item" aria-current="page">Staff меню</li>
                    <li class="breadcrumb-item active" aria-current="page">Список пользователей</li>
                </ol>
            </nav>
        </div>
    </div>
    <form class="row g-3">
        <div class="col-auto">
            <label for="inputRole" class="visually-hidden">Роль</label>
            <select class="form-select" name="role" id="inputRole" aria-label="Select role">
                <option>    Выберите роль    </option>
                <c:forEach var="role" items="${requestScope.roleList}">
                    <option <c:if test="${role == requestScope.user.role}">selected</c:if> value="${role}">${role}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary mb-3">Фильтровать</button>
        </div>
    </form>
    <div class="row">
        <div class="col-md-12 mt-2 mb-2">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Логин</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Дата рождения</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Роль</th>
                    <c:set var="admin" value="ADMIN"/>
                    <c:if test="${sessionScope.user.role == admin}">
                    <th scope="col">Действия</th>
                    </c:if>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.userList}" var="store">
                    <tr>
                        <th>${store.id}</th>
                        <th>${store.username}</th>
                        <th>${store.firstName}</th>
                        <th>${store.lastName}</th>
                        <th>${store.birthDate}</th>
                        <td>${store.address.street}, ${store.address.home}</td>
                        <td>${store.role}</td>


                        <c:if test="${sessionScope.user.role== admin && sessionScope.user.id != store.id}">
                            <td>
                                <a href="/staff/user/edit?id=${store.id}" class="btn btn-warning btn-sm">Редактировать роль</a>
                            </td>
                        </c:if>
                        <c:if test="${sessionScope.user.id == store.id}">
                            <td>
                                <div href="" class="btn btn-warning btn-sm">Изменение роли недоступно</div>
                            </td>
                        </c:if>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>
