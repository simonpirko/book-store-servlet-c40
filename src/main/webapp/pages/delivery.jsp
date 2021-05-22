<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

          <div class="card mt-3 mb-3">
              <div class="card-header">
                  Самовывоз
              </div>
              <div class="card-body">
                  <ul class="list-group">
                  <h5 class="card-title">
                      <c:forEach var="store" items="${requestScope.storeList}">
                          <li class="list-group-item">${store.name}, (${store.address.street}, д. ${store.address.home})</li>
                      </c:forEach>
                  </h5>
                  </ul>
              </div>
          </div>


      <div class="card mt-3 mb-3">
        <div class="card-header">
          Доставка
        </div>
        <div class="card-body">
          <blockquote class="blockquote mb-0">
            <p>Доставка курьером осуществляется в городе Минске (в пределах мкада). При сумме заказа от 100Р доставка бесплатная, если менее 100Р стоимость доставки 7Р Срок доставки 2 рабочих дня. Доставка осуществляется с понедельника по субботу с 10-00 до 23-00.</p>
            <p>Доставка за пределами Минска осуществляется курьерсой службой в течении 7 рабочих дней. Цена доставки расчитывается менеджером во время заказа.</p>
            <p>Так же возможно забрать товар самовывозом бесплатно из нашего магазина по адресу <b>Тимирязева 67</b> с понедельника по субботу с 9-30 до 21-00.</p>
          </blockquote>
        </div>
      </div>

      <div class="card mt-3 mb-3">
        <div class="card-header">
          Оплата
        </div>
        <div class="card-body">
          <blockquote class="blockquote mb-0">
            <p>Оплата заказа возможна несколькими вариантами: наличными при получении заказа, картой при получении заказа, картой онлайн во время заказа. Возможны дополнительные варианты оплаты для юр. лиц, более подроную информацию уточняйте у менеджера.</p>
          </blockquote>
        </div>
      </div>

    </div>
  </div>
</div>

<jsp:include page="_footer.jsp" />
</body>
</html>
