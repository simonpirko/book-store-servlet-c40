<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Добавление книги</title>
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
                    <li class="breadcrumb-item"><a href="/staff/book">Список книг</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Добавление книги</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-2">
            <form class="row g-3" method="post">
                <div class="col-12">
                    <label for="inputName" class="form-label">Название</label>
                    <input type="text" class="form-control" id="inputName" placeholder="Введи название..." required name="name">
                </div>
                <div class="col-md-4">
                    <label for="inputStreet" class="form-label">Цена</label>
                    <input type="number" class="form-control" id="inputStreet" placeholder="Введи цену..." required name="price">
                </div>
                <div class="col-md-4">
                    <label for="inputHouse" class="form-label">Жанр</label>
                    <input type="text" class="form-control" id="inputHouse" placeholder="Введи жанр..." required name="genre">
                </div>
                <div class="col-md-4">
                    <label for="inputDate" class="form-label">Дата публикации</label>
                    <input type="date" class="form-control" id="inputDate" required name="publicDate">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Описание</label>
                    <textarea class="form-control" required name="description" id="exampleFormControlTextarea1" rows="3"> </textarea>
                </div>
                <h5>Список авторов</h5>
                <div class="list-group">
                    <c:forEach var="author" items="${requestScope.listAuthor}">
                        <label class="list-group-item">
                            <input class="form-check-input me-1" type="checkbox" name="authorIdList" value="${author.id}">
                                ${author.id} ${author.firstName} ${author.lastName}
                        </label>
                    </c:forEach>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Добавить новую книгу</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>