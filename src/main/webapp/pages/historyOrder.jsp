<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История заказов</title>
</head>
<body>
<jsp:include page="_header.jsp" />

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Главная</a></li>
                    <li class="breadcrumb-item"><a href="/profile">Профиль</a></li>
                    <li class="breadcrumb-item active" aria-current="page">История заказов</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 mt-2 mb-2">
            <jsp:include page="_profileButton.jsp" />
        </div>
        <div class="col-md-9 mt-2 mb-2">
            <div class="btn-group" role="group" aria-label="Filter orders">
                <a class="btn btn-outline-secondary" href="?filter=all">Все</a>
                <a class="btn btn-outline-secondary" href="?filter=active">Активные</a>
                <a class="btn btn-outline-secondary" href="?filter=done">Выполнены</a>
            </div>

            <%-- Open content order 1 --%>
            <hr>
            <ul class="nav nav-tabs mt-4" id="order-1-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="main-info-tab-1" data-bs-toggle="tab" data-bs-target="#main-info-1" type="button" role="tab" aria-controls="main-info-1" aria-selected="true">Общая информация о заказе</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="book-list-tab-1" data-bs-toggle="tab" data-bs-target="#book-list-1" type="button" role="tab" aria-controls="book-list-1" aria-selected="false">Список книг в заказе</button>
                </li>
            </ul>
            <div class="tab-content" id="order-1-tabContent">
                <div class="tab-pane fade show active mt-1" id="main-info-1" role="tabpanel" aria-labelledby="main-info-tab-1">
                    <span class="mt-1"><b>Номер заказа: </b> 8475</span><br>
                    <span class="mt-1"><b>Статус заказа: </b> в обработке</span><br>
                    <span class="mt-1"><b>Сумма заказа: </b> 97</span><br>
                    <span class="mt-1"><b>Тип доставки: </b> самовывоз по адресу - г. Минск, Немига 78</span><br>
                    <span class="mt-1"><b>Дата оформления заказа: </b> 03.05.2021 18:00</span>
                </div>
                <div class="tab-pane fade" id="book-list-1" role="tabpanel" aria-labelledby="book-list-tab-1">
                    <table class="table mt-1">
                        <thead>
                        <tr>
                            <th scope="col">Название</th>
                            <th scope="col">Цена</th>
                            <th scope="col">Количество</th>
                            <th scope="col">Общая стоимость</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- Close content order 1 --%>

            <%-- Open content order 2 --%>
            <hr>
            <ul class="nav nav-tabs mt-4" id="order-2-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="main-info-tab-2" data-bs-toggle="tab" data-bs-target="#main-info-2" type="button" role="tab" aria-controls="main-info-2" aria-selected="true">Общая информация о заказе</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="book-list-tab-2" data-bs-toggle="tab" data-bs-target="#book-list-2" type="button" role="tab" aria-controls="book-list-2" aria-selected="false">Список книг в заказе</button>
                </li>
            </ul>
            <div class="tab-content" id="order-2-tabContent">
                <div class="tab-pane fade show active mt-1" id="main-info-2" role="tabpanel" aria-labelledby="main-info-tab-2">
                    <span class="mt-1"><b>Номер заказа: </b> 8475</span><br>
                    <span class="mt-1"><b>Статус заказа: </b> в обработке</span><br>
                    <span class="mt-1"><b>Сумма заказа: </b> 97</span><br>
                    <span class="mt-1"><b>Тип доставки: </b> самовывоз по адресу - г. Минск, Немига 78</span><br>
                    <span class="mt-1"><b>Дата оформления заказа: </b> 03.05.2021 18:00</span>
                </div>
                <div class="tab-pane fade" id="book-list-2" role="tabpanel" aria-labelledby="book-list-tab-2">
                    <table class="table mt-1">
                        <thead>
                        <tr>
                            <th scope="col">Название</th>
                            <th scope="col">Цена</th>
                            <th scope="col">Количество</th>
                            <th scope="col">Общая стоимость</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- Close content order 2 --%>

            <%-- Open content order 3 --%>
            <hr>
            <ul class="nav nav-tabs mt-4" id="order-3-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="main-info-tab-3" data-bs-toggle="tab" data-bs-target="#main-info-3" type="button" role="tab" aria-controls="main-info-3" aria-selected="true">Общая информация о заказе</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="book-list-tab-3" data-bs-toggle="tab" data-bs-target="#book-list-3" type="button" role="tab" aria-controls="book-list-3" aria-selected="false">Список книг в заказе</button>
                </li>
            </ul>
            <div class="tab-content" id="order-3-tabContent">
                <div class="tab-pane fade show active mt-1" id="main-info-3" role="tabpanel" aria-labelledby="main-info-tab-3">
                    <span class="mt-1"><b>Номер заказа: </b> 8475</span><br>
                    <span class="mt-1"><b>Статус заказа: </b> в обработке</span><br>
                    <span class="mt-1"><b>Сумма заказа: </b> 97</span><br>
                    <span class="mt-1"><b>Тип доставки: </b> самовывоз по адресу - г. Минск, Немига 78</span><br>
                    <span class="mt-1"><b>Дата оформления заказа: </b> 03.05.2021 18:00</span>
                </div>
                <div class="tab-pane fade" id="book-list-3" role="tabpanel" aria-labelledby="book-list-tab-3">
                    <table class="table mt-1">
                        <thead>
                        <tr>
                            <th scope="col">Название</th>
                            <th scope="col">Цена</th>
                            <th scope="col">Количество</th>
                            <th scope="col">Общая стоимость</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        <tr>
                            <td>Алиса в стране чудес</td>
                            <td>45</td>
                            <td>2</td>
                            <td>90</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- Close content order 3 --%>

        </div>
    </div>
</div>

<jsp:include page="_footer.jsp" />
</body>
</html>
