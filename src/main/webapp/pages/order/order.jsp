<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Order</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/home">Главная</a></li>
                    <li class="breadcrumb-item" aria-current="page">Профиль</li>
                    <li class="breadcrumb-item"><a>Корзина</a></li>
                    <li class="breadcrumb-item"><a>Создание заказа</a></li>
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
                <th scope="col">Жанр</th>
                <th scope="col">Авторы</th>
                <th scope="col">Цена</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.listBook}" var="book">
                <tr>
                    <th>${book.id}</th>
                    <th>${book.name}</th>
                    <th>${book.genre}</th>
                    <th>
                        <c:forEach items="${book.authors}" var="author">
                            ${author.firstName} ${author.lastName}<br></br>
                        </c:forEach>
                    </th>
                    <th>${book.price}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <p><b>Цена заказ: </b>${requestScope.totalPrice}</p>

    <form action="/order" method="post">

        <div class="col-md-4">
            <label for="fname" class="form-label">Имя</label>
            <input type="text" class="form-control" id="fname" placeholder="${requestScope.user.firstName}" required
                   name="firstName">
        </div>
        <div class="col-md-4">
            <label for="lname" class="form-label">Фамилия</label>
            <input type="text" class="form-control" id="lname" placeholder="${requestScope.user.lastName}" required
                   name="lastName">
        </div>

        <label class="form-label">Тип Доставки</label>

        <div class="form-check">
            <input class="form-check-input" type="radio" name="one" id="flexRadioDefault1" value="delivery">
            <label class="form-check-label" for="flexRadioDefault1">
                Доставка
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="one" id="flexRadioDefault2" value="pickup">
            <label class="form-check-label" for="flexRadioDefault2">
                Самовывоз
            </label>
        </div>

        <div class="row">
            <div class="col-md-12 mt-2">
                <form class="row g-3" method="post">
                    <button type="submit" class="btn btn-primary">Оформить заказ</button>
                </form>
            </div>
        </div>

    </form>

</div>


<jsp:include page="../_alert.jsp"/>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
