
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Order</title>
</head>
<body>
<jsp:include page="../_header.jsp" />

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/home">Главная</a></li>
                    <li class="breadcrumb-item" aria-current="page">Профиль</li>
                    <li class="breadcrumb-item"><a >Корзина</a></li>
                    <li class="breadcrumb-item"><a >Создание заказа</a></li>
                </ol>
            </nav>
        </div>
    </div>

    <h5>Список книг</h5>
    <div class="col-md-12 mt-2 mb-2">
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Книга</th>
                <th scope="col">Автор</th>
                <th scope="col">Цена</th>
                <th scope="col">Количество</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.listBook}" var="book">
                <th>${book.id}</th>
                <th>${book.name}</th>
                <th>${book.authors}</th>
                <th>${book.price}</th>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <p><b>Имя пользователя: </b>${requestScope.user.username}</p>
    <p><b>Цена заказ: </b>${requestScope.totalPrice}</p>

    <div class="row">
        <div class="col-md-12 mt-2">
            <form class="row g-3" method="post">

                <div class="col-md-6">
                    <label for="inputAddress" class="form-label">Адрес</label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="Введи адрес..." required name="address">
                </div>
                <div class="col-md-6">
                    <label for="type" class="form-label">Тип Доставки</label>
                    <input type="text" class="form-select" id="type"  required name="type">
                </div>


                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Оформить заказ</button>
                </div>
            </form>
        </div>
    </div>

    </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>
