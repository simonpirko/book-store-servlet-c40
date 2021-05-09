<%--
  Created by IntelliJ IDEA.
  User: іван
  Date: 05.05.2021
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>selection</title>
    <jsp:include page="_header.jsp"/>
</head>
<body>


<form class="row g-3 needs-validation" novalidate>
    <div class="col-md-3">
        <label for="validationCustom00" class="form-label">Жанр</label>
        <select class="form-select" id="validationCustom00" required>
            <option selected disabled value="">Выберите жанр</option>
            <option>Драмма</option>
            <option>Драмма</option>
            <option>Драмма</option>
        </select>
        <div class="invalid-feedback">
            Жанр.
        </div>
    </div>
    <div class="col-md-3">
        <label for="validationCustom01" class="form-label">Автор</label>
        <input type="text" class="form-control" id="validationCustom01" required placeholder="Введите автора">
        <div class="invalid-feedback">
            Автор книги.
        </div>
    </div>
    <div class="col-md-3">
        <label for="validationCustom02" class="form-label">Год</label>
        <input type="text" class="form-control" id="validationCustom02" required placeholder="1995-2021">
        <div class="invalid-feedback">
            Год издания.
        </div>
    </div>

    <div class="col-12">
        <button class="btn btn-primary" type="submit">Применить фильтр</button>
    </div>
</form>



<div class="card-group">
    <% for (int i = 0; i < 10; i += 1) { %>
    <tr>
        <div class="mt-3"/>
        <div class="card w-75">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCUXsYk4wNibk64Lgyf6FGZCz0YRLw5c4LhrWn53M1rqyawCz6Y92t3rAO5Iqc45ZQi4&usqp=CAU"
                 alt="...">
            <div class="card-body">
                <h5 class="card-title">Мастер и Маргарита</h5>
                <h5 class="card-title">Булгаков</h5>
                <p class="card-text">Описание</p>
                <a href="#" class="btn btn-primary">Подробнее</a>
            </div>
        </div>
</div>
</tr>
<% } %>
</div>

<nav class="navbar navbar-expand-lg navbar-light bg-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">Список всех книг</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
        integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"
        integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
        crossorigin="anonymous"></script>
</body>
<footer>
    <jsp:include page="_footer.jsp"/>
</footer>
</html>
