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
                        <li class="breadcrumb-item"><a href="/">Главная</a></li>
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
                    <span><b>Логин: </b> Boris</span>
                    <span class="mt-1"><b>Email: </b> boris88@gmail.com</span>
                    <span class="mt-1"><b>Имя: </b> Борис</span>
                    <span class="mt-1"><b>Фамилия: </b> Борисовский</span>
                    <span class="mt-1"><b>День рождения: </b> 20.05.1999</span>
                    <span class="mt-1"><b>Дата регистрации аккаунта: </b> 01.05.2021 21:45</span>
                    <hr>
                    <a class="btn btn-outline-warning" href="/logout">Выйти из аккаунта</a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../_footer.jsp" />
</body>
</html>
