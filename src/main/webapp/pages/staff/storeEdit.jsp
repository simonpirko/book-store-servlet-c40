<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактирование магазина</title>
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
                    <li class="breadcrumb-item"><a href="/staff/store">Список магазинов</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Редактировние магазина</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-2">
            <form class="row g-3" method="post">
                <div class="col-12">
                    <label for="inputName" class="form-label">Название</label>
                    <input type="text" class="form-control" id="inputName" placeholder="Введи название..." required name="name" value="${requestScope.store.name}">
                </div>
                <div class="col-md-8">
                    <label for="inputStreet" class="form-label">Улица</label>
                    <input type="text" class="form-control" id="inputStreet" placeholder="Введи улицу..." required name="street" value="${requestScope.store.address.street}">
                </div>
                <div class="col-md-4">
                    <label for="inputHouse" class="form-label">Дом</label>
                    <input type="number" class="form-control" id="inputHouse" placeholder="Введи номер дома..." required name="house" value="${requestScope.store.address.home}">
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Редактировать магазин</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>
