<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test='${ sessionScope.user.role == "ADMIN" || sessionScope.user.role == "MODERATOR" }'>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
      Staff меню
    </a>
    <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
      <li><a class="dropdown-item" href="/staff/store">Магазины</a></li>
      <li><a class="dropdown-item" href="/staff/store/create">Добавить новый магазин</a></li>
      <li><hr></li>
      <li><a class="dropdown-item" href="/staff/book">Книги</a></li>
      <li><hr></li>
      <li><a class="dropdown-item" href="/staff/user/all">Список пользователей</a></li>
      <li><hr></li>
      <li><a class="dropdown-item" href="/staff/orderList">Список заказов пользователей</a></li>
  </ul>
  </li>
</c:if>
