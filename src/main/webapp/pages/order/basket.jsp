<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 019 19.05.21
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Basket</title>
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
                </ol>
            </nav>
        </div>
    </div>
    <c:if test="${requestScope.listBook == null}">
        ${requestScope.message}

        <div class="col-md-12 mt-2">
            <a href="/selection" class="btn btn-outline-primary">Перейти к товарам</a>
        </div>
    </c:if>
    <c:if test="${requestScope.listBook != null}">
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
                    <th></th>
                    <td>
                        <form method="post" action="/basket/remove"
                              onsubmit="return confirm('Вы действительно хотите удалить данную книгу из корзины?')">

                            <input type="hidden" name="id" value="${book.id}">
                            <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                        </form>
                    </td>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <p><b>Цена заказа: </b>${requestScope.totalPrice}</p>

        <div class="col-md-12 mt-2">
            <a href="/profile/order" class="btn btn-outline-primary">Перейти к созданию заказа</a>
        </div>

        </c:if>
    </div>
</div>

<jsp:include page="../_alert.jsp"/>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
