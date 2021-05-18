<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список книг</title>
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
                    <li class="breadcrumb-item active" aria-current="page">Список книг</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-2">
            <a href="/staff/book/add" class="btn btn-outline-primary">Добавить новую книгу</a>
        </div>
        <div class="col-md-12 mt-2">
            <a href="/staff/book/addAuthor" class="btn btn-outline-primary">Добавить нового автора</a>
        </div>
        <div class="col-md-12 mt-2 mb-2">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Название</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Жанр</th>
                    <th scope="col">Авторы</th>
                    <th scope="col">Дата публикации</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.listBook}" var="book">
                    <tr>
                        <th>${book.id}</th>
                        <th>${book.name}</th>
                        <th>${book.price}</th>
                        <th>${book.genre}</th>
                        <th>
                        <c:forEach items="${book.authors}" var="author">
                        ${author.firstName} ${author.lastName}<br></br>
                        </c:forEach>
                        </th>
                        <th>${book.publicationDate}</th>
                        <td>
                            <form method="post" action="/staff/book/remove" onsubmit="return confirm('Вы действительно хотите удалить данную книгу?')">

                                <a href="/staff/book/edit?id=${book.id}" class="btn btn-warning btn-sm">Редактировать</a>

                                <input type="hidden" name="id" value="${book.id}">
                                <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                            </form>
                        </td>
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