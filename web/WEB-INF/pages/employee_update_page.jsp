<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Електронна кореспонденція: редагувати співробітника</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/employee/update" method="post">
            <h3>Редагувати співробітника</h3>

            <input type="hidden" id="id" name="id" value="${id}"/>

            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Співробітник</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" value="${employee.name}"
                           placeholder="Співробітник">
                </div>
            </div>

            <input type="submit" class="btn btn-primary" value="Внести зміни до запису">
        </form>
    </div>
</sec:authorize>
</body>
</html>