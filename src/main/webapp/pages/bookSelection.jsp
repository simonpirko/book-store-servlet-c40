<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>selection</title>
    <jsp:include page="_header.jsp"/>
</head>
<body>

<div class="container">
    <div class="row">
        <form class="row g-3 needs-validation" novalidate>
            <div class="col-md-3">
                <label for="inputGenre" class="form-label">Жанр</label>
                <select class="form-select" id="inputGenre" name="genre">
                    <option selected disabled>Выберите жанр</option>
                    <c:forEach items="${requestScope.filter.get(0)}" var="item">
                        <option value="${item}">${item}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <label for="inputAuthor" class="form-label">Автор</label>
                <select class="form-select" id="inputAuthor" name="author">
                    <option selected disabled>Выберите автора</option>
                    <c:forEach items="${requestScope.filter.get(1)}" var="item">
                        <option value="${item}">${item}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <label for="validationCustom02" class="form-label">Год издания - от</label>
                <input type="number" class="form-control" id="validationCustom02" placeholder="1700" name="yearMin">
            </div>
            <div class="col-md-3">
                <label for="validationCustom023" class="form-label">Год издания - до</label>
                <input type="number" class="form-control" id="validationCustom023" placeholder="2021" name="yearMax">
            </div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Применить фильтр</button>
            </div>
        </form>

        <div class="card-group">
            <c:forEach var="book" items="${requestScope.bookList}">
                <div class="mt-2 mb-2">
                    <div class="card w-750" style="width: 95%">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCUXsYk4wNibk64Lgyf6FGZCz0YRLw5c4LhrWn53M1rqyawCz6Y92t3rAO5Iqc45ZQi4&usqp=CAU"
                             alt="Book">
                        <div class="card-body">
                            <h5 class="card-title">${book.name}</h5>
                            <h5 class="card-text">
                                <c:forEach var="item" items="${book.authors}">
                                    ${item},
                                </c:forEach>
                            </h5>
                            <p class="card-text">${book.price} руб.</p>
                            <a href="/book?id=${book.id}" class="btn btn-primary">Подробнее</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

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
