<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Книга - ${requestScope.book.name}</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col">
            <img src="https://cdn.discordapp.com/attachments/323836563563741194/841339956094042132/2.jpg"
                 class="rounded float-start" alt="Book">
        </div>
        <div class="col">
            <div class="text">
                <figure>
                    <blockquote class="blockquote">
                        <h2>${requestScope.book.name}</h2>
                    </blockquote>
                    <figcaption class="blockquote-footer">
                        <cite title="Source Title">${requestScope.book.description}</cite>
                    </figcaption>
                    <hr>
                    <p><b>Автор: </b> <br>
                        <c:forEach var="item" items="${requestScope.book.authors}">
                            - ${item.firstName} ${item.lastName}<br>
                        </c:forEach>
                    </p>
                    <p><b>Жанр: </b>${requestScope.book.genre}</p>
                </figure>

                <h3>${requestScope.book.price} руб.</h3>
                <form method="post" action="/basket" onsubmit="return confirm('Книга добавлена в корзину')">

                    <input type="hidden" name="id" value="${book.id}">
                    <button type="submit" class="btn btn-warning">Положить в корзину</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <hr>
        <div class="col-md-6 offset-md-3">
            <div class="comment-box lg-2">
                <h3>Добавить комментарий</h3>
                <div class="rating">
                    <input type="radio" name="rating" value="5" id="1">
                    <label for="1">1</label>
                    <input type="radio" name="rating" value="4" id="2">
                    <label for="2">2</label>
                    <input type="radio" name="rating" value="3" id="3">
                    <label for="3">3</label>
                    <input type="radio" name="rating" value="2" id="4">
                    <label for="4">4</label>
                    <input type="radio" name="rating" value="1" id="5">
                    <label for="5">5 (Рейтинг)</label>
                </div>

                <div class="comment-area">
                    <textarea class="form-control" placeholder="Сообщение" rows="4"></textarea>
                </div>

                <div class="comment-bans mt-3">
                    <div class="row">

                        <div class="col-6">
                            <div class="pull-left">
                                <button class="btn btn-success btn-sm">Отмена</button>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="pull-right">
                                <button class="btn btn-success send btn-sm">Отправить<i
                                        class="fa fa-long-arrow-right ml-1"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <section class="content-item" id="comments">
        <div class="container">
            <div class="row">
                <div class="col">

                    <h3>Комментариев:
                        <c:if test="${requestScope.book.comments == null}">0</c:if>
                        <c:if test="${requestScope.book.comments != null}">${requestScope.book.comments.size()}</c:if>
                    </h3>

                    <c:forEach var="item" items="${requestScope.book.comments}">
                        <div class="media">
                            <hr/>
                            <a class="pull-left" href="#"></a>
                            <div class="media-body">
                                <h4 class="media-heading">${item.user.lastName}</h4>
                                    <%--                                <ul class="list-unstyled list-inline media-detail pull-right">--%>
                                    <%--                                    <li><i class="fa fa-thumbs-up"></i>13</li>--%>
                                    <%--                                </ul>--%>
                                    <%--                                <ul class="list-unstyled list-inline media-detail pull-right">--%>
                                    <%--                                    <li class=""><a href="">Like</a></li>--%>
                                    <%--                                </ul>--%>
                                <p>${item.comment}</p>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </section>
</div>


<footer>
    <jsp:include page="_footer.jsp"/>
</footer>
</body>
</html>
