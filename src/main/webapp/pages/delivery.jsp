<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Доставка и оплата</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<div class="container">
  <div class="row">
    <div class="coll-md-12 mt-2">
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/">Главная</a></li>
          <li class="breadcrumb-item active" aria-current="page">Доставка и оплата</li>
        </ol>
      </nav>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">

      <div class="card mt-3 mb-3">
        <div class="card-header">
          Доставка
        </div>
        <div class="card-body">
          <blockquote class="blockquote mb-0">
            <p>Доставка курьером осуществляется в городе Минске (в пределах мкада). При сумме заказа от 100Р доставка БЕСПЛАТНАЯ, если менее 100Р стоимость доставки 7Р Срок доставки 2 рабочих дня. <br>Доставка за пределами Минска осузщствляется курьерсой службой в течении 7 рабочих дней. Цена доставки расчитывается менеджером во время заказа.</p>
          </blockquote>
        </div>
      </div>

      <div class="card mt-3 mb-3">
        <div class="card-header">
          Оплата
        </div>
        <div class="card-body">
          <blockquote class="blockquote mb-0">
            <p>Оплата заказа возможна несколькими вариантами: наличными при получении заказа, картой при получении заказа, картой онлайн во время заказа.</p>
          </blockquote>
        </div>
      </div>

    </div>
  </div>
</div>

<jsp:include page="_footer.jsp" />
</body>
</html>
