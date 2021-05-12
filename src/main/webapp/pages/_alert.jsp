<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .fixedAlert {
        position: fixed;
        bottom: 0;
        right: 15px;
    }
</style>

<c:if test="${requestScope.message != null}">
    <div class="alert alert-info alert-dismissible fade show fixedAlert" role="alert">
        <strong>${requestScope.message}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
