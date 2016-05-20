<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Електронна кореспонденція: редагувати сторонню організацію</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/outorganization/update" method="post">
            <h3>Редагувати сторонню організацію</h3>

            <input type="hidden" id="id" name="id" value="${id}"/>

            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Стороння організація</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" value="${outorganization.name}"
                           placeholder="Стороння організація">
                </div>
            </div>

            <input type="submit" class="btn btn-primary" value="Внести зміни до запису">
        </form>
    </div>
</sec:authorize>
</body>
</html>