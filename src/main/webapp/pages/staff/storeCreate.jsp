<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Добавление новго магазина</title>
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
          <li class="breadcrumb-item"><a href="/staff/store">Список магазинов</a></li>
          <li class="breadcrumb-item active" aria-current="page">Добавление новго магазина</li>
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
        <div class="col-md-8">
          <label for="inputStreet" class="form-label">Улица</label>
          <input type="text" class="form-control" id="inputStreet" placeholder="Введи улицу..." required name="street">
        </div>
        <div class="col-md-4">
          <label for="inputHouse" class="form-label">Дом</label>
          <input type="number" class="form-control" id="inputHouse" placeholder="Введи номер дома..." required name="house">
        </div>
        <div class="col-12">
          <button type="submit" class="btn btn-primary">Добавить новый магазин</button>
        </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>
