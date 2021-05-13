<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<nav class="navbar navbar-light bg-light">
    <div class="container">
        <div class="col-sm-10 col-sm offset-3">
                <a class="navbar-brand" href="#">
                    <img src="http://booklikes.com/upload/post/7/f/azure_7f770238ef1e098e3c924c8a4c83bdeb.jpg"
                         class="center-block" alt=""
                         width="500" height="200">
                </a>
        </div>
    </div>
</nav>

<div class="card-group">
    <% for (int i = 0; i < 10; i += 1) { %>
    <tr>
        <div class="mt-3"/>
        <div class="card w-75">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCUXsYk4wNibk64Lgyf6FGZCz0YRLw5c4LhrWn53M1rqyawCz6Y92t3rAO5Iqc45ZQi4&usqp=CAU"
                 alt="...">
            <div class="card-body">
                <h5 class="card-title">Мастер и Маргарита</h5>
                <h5 class="card-title">Булгаков</h5>
                <p class="card-text">Описание</p>
                <a href="#" class="btn btn-primary">Подробнее</a>
            </div>
        </div>
</div>
</tr>
<% } %>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>
