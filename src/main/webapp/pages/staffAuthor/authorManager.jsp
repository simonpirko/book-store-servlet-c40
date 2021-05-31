<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список авторов</title>
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
                    <li class="breadcrumb-item active" aria-current="page">Список авторов</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-2">
            <a href="/staff/book/addAuthor" class="btn btn-outline-primary">Добавить нового автора</a>
        </div>
        <div class="col-md-12 mt-2 mb-2">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Описание</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.listAuthor}" var="author">
                    <tr>
                        <th>${book.id}</th>
                        <th>${book.name}</th>
                        <th>${book.price}</th>
                        <th>${book.genre}</th>
                        <th>
                        <td>
                            <form method="post" action="/staff/author/remove" onsubmit="return confirm('Вы действительно хотите удалить данного автора?')">

                                <a href="/staff/author/edit?id=${author.id}" class="btn btn-warning btn-sm">Редактировать</a>

                                <input type="hidden" name="id" value="${author.id}">
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
</body>
</html>
