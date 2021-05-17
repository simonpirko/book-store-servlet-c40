<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Добавление автора</title>
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
                    <li class="breadcrumb-item"><a href="/staff/book">Список книг</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Добавление автора</li>
                </ol>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt-2">
            <form class="row g-3" method="post">
                <div class="col-8">
                    <label for="inputfName" class="form-label">Имя</label>
                    <input type="text" class="form-control" id="inputfName" placeholder="Введи имя..." required name="fName">
                </div>
                <div class="col-md-8">
                    <label for="inputlName" class="form-label">Фамилия</label>
                    <input type="text" class="form-control" id="inputlName" placeholder="Введи фамилию..." required name="lName">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Описание</label>
                    <textarea class="form-control" required name="description" id="exampleFormControlTextarea1" rows="3"> </textarea>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Добавить автора</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../_alert.jsp" />
<jsp:include page="../_footer.jsp" />
</body>
</html>