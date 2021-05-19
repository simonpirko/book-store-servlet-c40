<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Страница не найдена</title>
</head>
<body>
<jsp:include page="../_header.jsp" />

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center">Страница не найдена</h2>
            <c:if test="${requestScope.message != null}">
                <h4 class="text-center">${requestScope.message}</h4>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../_footer.jsp" />
</body>
</html>
