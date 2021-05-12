<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Список магазинов</title>
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
          <li class="breadcrumb-item active" aria-current="page">Список магазинов</li>
        </ol>
      </nav>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12 mt-2">
      <a href="/staff/store/create" class="btn btn-outline-primary">Добавить новый магазин</a>
    </div>
    <div class="col-md-12 mt-2 mb-2">
      <table class="table table-hover">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">Название</th>
          <th scope="col">Адрес</th>
          <th scope="col">Действия</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${requestScope.storeList}" var="store">
            <tr>
              <th>${store.id}</th>
              <td>${store.name}</td>
              <td>${store.address.street}, ${store.address.home}</td>
              <td>
                <form method="post" action="/staff/store/delete" onsubmit="return confirm('Вы действительно хотите удалить данный магазин?')">

                  <a href="/staff/store/edit?id=${store.id}" class="btn btn-warning btn-sm">Редактировать</a>

                  <input type="hidden" name="id" value="${store.id}">
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
