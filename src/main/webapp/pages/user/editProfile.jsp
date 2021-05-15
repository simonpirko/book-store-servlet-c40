<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование профиля</title>
</head>
<body>
<jsp:include page="../_header.jsp" />

<div class="container">
    <div class="row">
        <div class="coll-md-12 mt-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Главная</a></li>
                    <li class="breadcrumb-item"><a href="/profile">Профиль</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Редактирование профиля</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 mt-2 mb-2">
            <jsp:include page="../_profileButton.jsp" />
        </div>
        <div class="col-md-9 mt-2 mb-2">
            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home" aria-selected="true">Редактирование основных данных</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Редактирование адреса</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-contact" type="button" role="tab" aria-controls="pills-contact" aria-selected="false">Изменение пароля</button>
                </li>
            </ul>
            <div class="tab-content" id="pills-tabContent">

<%--                Edit main data tab--%>
                <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                    <form class="row g-3" method="post" action="/edit-profile/main">
                        <div class="col-md-12">
                            <label for="inputEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="inputEmail" required name="email" value="boris99@gmail.com">
                        </div>
                        <div class="col-md-6">
                            <label for="inputLastName" class="form-label">Имя</label>
                            <input type="text" class="form-control" id="inputLastName" required name="firstName" value="Борис">
                        </div>
                        <div class="col-6">
                            <label for="inputFirstName" class="form-label">Фамилия</label>
                            <input type="text" class="form-control" id="inputFirstName" required name="lastName" value="Борисович">
                        </div>
                        <div class="col-12">
                            <label for="inputDate" class="form-label">Дата рождения</label>
                            <input type="date" class="form-control" id="inputDate" name="date" value="1999-11-20">
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-outline-success">Редактировать профиль</button>
                        </div>
                    </form>
                </div>

<%--                Edit address tab--%>
                <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                    <form class="row g-3" method="post" action="/edit-profile/address">
                        <div class="col-md-9">
                            <label for="inputStreet" class="form-label">Улица</label>
                            <input type="text" class="form-control" id="inputStreet" required name="street" value="Немига">
                        </div>
                        <div class="col-md-3">
                            <label for="inputHouse" class="form-label">Дом</label>
                            <input type="number" class="form-control" id="inputHouse" required name="house" value="83">
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-outline-success">Редактировать адрес</button>
                        </div>
                    </form>
                </div>

<%--                Edit password tab--%>
                <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
                    <form class="row g-3" method="post" action="/edit-profile/password">
                        <div class="col-md-12">
                            <label for="inputOldPass" class="form-label">Старый пароль</label>
                            <input type="password" class="form-control" id="inputOldPass" required name="oldPassword">
                        </div>
                        <div class="col-md-6">
                            <label for="inputNewPass" class="form-label">Новый пароль</label>
                            <input type="password" class="form-control" id="inputNewPass" required name="newPassword">
                        </div>
                        <div class="col-6">
                            <label for="inputConfirmNewPass" class="form-label">Повторите новый пароль</label>
                            <input type="password" class="form-control" id="inputConfirmNewPass" required name="confirmNewPassword">
                        </div>

                        <div class="col-12">
                            <button type="submit" class="btn btn-outline-success">Изменить пароль</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../_footer.jsp" />
</body>
</html>
