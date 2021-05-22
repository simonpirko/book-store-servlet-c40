<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div class="card-group">

            <c:forEach var="book" items="${requestScope.bookRandList}">

                <div class="row">
                    <div class="col-md-6 col-xl-3">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCUXsYk4wNibk64Lgyf6FGZCz0YRLw5c4LhrWn53M1rqyawCz6Y92t3rAO5Iqc45ZQi4&usqp=CAU"
                             alt="Book">
                        <div class="card-body">
                            <h5 class="card-title">${book.name}</h5>
                            <h5 class="card-text">
                                <c:forEach var="item" items="${book.authors}">
                                    ${item},
                                </c:forEach>
                            </h5>
                            <p class="card-text">${book.price} руб.</p>
                            <a href="/book?id=${book.id}" class="btn btn-primary">Подробнее</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
</nav>


<jsp:include page="_footer.jsp"/>

</body>
</html>
