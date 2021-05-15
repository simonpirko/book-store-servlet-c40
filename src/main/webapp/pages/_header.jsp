<%--
  Created by IntelliJ IDEA.
  User: itsmymac
  Date: 30.04.21
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <title>Book Store</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">BOOKSHOPPER</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/selection">Книги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">О нас</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Контакты</a>
                </li>

                <jsp:include page="_staff_menu.jsp" />

            </ul>

            <div class="d-flex">
                <form class="d-flex m-0">
                    <button class="btn btn-outline-success" type="submit">Поиск</button>
                    <input class="form-control ms-2" type="search" placeholder="Поиск" aria-label="Search">
                </form>
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll ms-3" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">
                            <a class="nav-link  d-flex justify-content-between align-items-center" href="#">
                                <p class="m-0">Корзина</p>
                                <i class="fas fa-shopping-cart ms-1" style="font-size: 30px"></i>
                            </a>
                    </li>
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item ms-3 d-flex">
                            <a class="nav-link d-flex justify-content-between align-items-center" href="/reg">
                                <p class="m-0">Регистрация</p>
                                <i class="fas fa-user ms-1" style="font-size: 30px"></i>
                            </a>
                        </li>
                        <li class="nav-item ms-3 d-flex">
                            <a class="nav-link d-flex justify-content-between align-items-center" href="/auth">
                                <p class="m-0">Авторизация</p>
                                <i class="fas fa-user ms-1" style="font-size: 30px"></i>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item ms-3 d-flex">
                            <a class="nav-link d-flex justify-content-between align-items-center" href="/profile">
                                <p class="m-0">Личный кабинет</p>
                                <i class="fas fa-user ms-1" style="font-size: 30px"></i>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
