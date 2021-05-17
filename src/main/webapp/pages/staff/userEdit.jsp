<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактирование пользователя</title>
</head>
<body>
<jsp:include page="../_header.jsp" />

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Главная</a></li>
                    <li class="breadcrumb-item" aria-current="page">Staff меню</li>
                    <li class="breadcrumb-item"><a href="/staff/user/all">Список пользователей</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Редактировние пользователя</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-2">
            <form class="row g-3" method="post">
                <div class="col-md-12">
                    <label for="inputRole" class="form-label">Роль</label>
                    <select class="form-select" id="inputRole" aria-label="User role" name="role">
                        <c:forEach var="role" items="${requestScope.roleList}">
                            <option <c:if test="${role == requestScope.user.role}">selected</c:if> value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Редактировать пользователя</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>
