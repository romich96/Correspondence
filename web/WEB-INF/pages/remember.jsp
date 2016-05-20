<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Test Session</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<>


<sec:authorize access="!isAuthenticated()">
    <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<p><a class="btn btn-lg btn-success" href="<c:url value="/logout" />" role="button">Вийти</a></p>
</sec:authorize>


<h2>Adding of a String into the session</h2>


<sec:authorize var="loggedIn" access="isAuthenticated()"/>
<c:choose>
    <c:when test="${loggedIn}">
        You are loged in
        <p>Welcome: <sec:authentication property="principal.username"/></p>

        <p>Don't forget: ${thought}</p>
    </c:when>
    <c:otherwise>
        You are logged out
        <p>Don't forget: ${thought}</p>
    </c:otherwise>
</c:choose>


<a href="${pageContext.request.contextPath}/">Main page</a>

<%--<a th:href="${'products/download?id=' + product.id}"><span th:text="${product.name}"></span></a>--%>
<a href="/download">загрузка файла</a>


</body>
</html>