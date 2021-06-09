<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ListOrder</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/home">Главная</a></li>
                    <li class="breadcrumb-item" aria-current="page">Staff меню</li>
                    <li class="breadcrumb-item active" aria-current="page">Список заказов</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">

        <div class="col-md-12 mt-2 mb-2">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Пользователь</th>
                    <th scope="col">Список книг</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Статус</th>
                    <th scope="col">Тип доставки</th>
                    <th scope="col">Дата заказа</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.listOrder}" var="order">
                    <tr>
                        <th>${order.id}</th>
                        <th>${order.user}</th>
                        <th>
                            <c:forEach items="${order.bookList}" var="book">
                                ${book.name}
                                <c:forEach items="${book.authors}" var="author">
                                    ${author.firstName} ${author.lastName}
                                </c:forEach> <br></br>
                            </c:forEach>
                        </th>
                        <th>${order.totalPrice}</th>
                        <th>${order.address}</th>
                        <th>${order.status}</th>
                        <th>${order.type}</th>
                        <th>${order.orderDate}</th>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../_alert.jsp"/>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
