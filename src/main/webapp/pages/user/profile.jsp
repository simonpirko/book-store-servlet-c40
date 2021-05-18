<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
</head>
<body>
    <jsp:include page="../_header.jsp" />

    <div class="container">
        <div class="row">
            <div class="coll-md-12 mt-2">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/home">Главная</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Профиль</li>
                    </ol>
                </nav>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 mt-2 mb-2">
                <jsp:include page="../_profileButton.jsp" />
            </div>
            <div class="col-md-9 mt-2 mb-2">
                <div class="card p-2">
                    <span><b>Логин: </b> ${sessionScope.user.username}</span>
                    <span class="mt-1"><b>Имя: </b> ${sessionScope.user.firstName}</span>
                    <span class="mt-1"><b>Фамилия: </b> ${sessionScope.user.lastName}</span>
                    <span class="mt-1"><b>День рождения: </b> ${requestScope.birthdate}</span>
                    <hr>
                    <a class="btn btn-outline-warning" href="/logout">Выйти из аккаунта</a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../_footer.jsp" />
</body>
</html>
