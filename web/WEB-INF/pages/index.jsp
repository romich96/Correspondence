<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <title>Електронна кореспонденція: головна</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <nav id="inhead" class="navbar navbar-static-top" style="margin-left: 30px; margin-right: 30px;">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/"> Кореспонденція </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/incoming">Журнали</a></li>
                <li><a href="/type">Довідники</a></li>
                <li><a href="/incoming_allfiles">Файли</a></li>
                <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Фраза у тексті файлів" title="* в кінці фрази для нечіткого пошуку">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                </form>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a><span class="glyphicon glyphicon-user"></span> <sec:authentication
                        property="principal.username"/></a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Вийти</a></li>
            </ul>
        </div>
    </nav>
    <%--class="parent"--%>
    <div>
        <div class="col-sm-4">
            <div class="list-group">
                <a class="list-group-item active">Зареєструвати новий документ</a>
                <a href="/incoming_add_page" class="list-group-item">Новий вхідний документ</a>
                <a href="/outcoming_add_page" class="list-group-item">Новий вихідний документ</a>
                <a href="/innercoming_add_page" class="list-group-item">Новий внутрішній документ</a>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="list-group">
                <a class="list-group-item active">Журнали документів</a>
                <a href="/incoming" class="list-group-item"><span class="badge">${incomingsCount}</span></span> Журнал вхідних
                    документів</a>
                <a href="/outcoming" class="list-group-item"><span class="badge">${outcomingsCount}</span> Журнал вихідних
                    документів
                </a>
                <a href="/innercoming" class="list-group-item"><span class="badge">${innercomingsCount}</span> Журнал внутрішніх документів
                </a>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="list-group">
                <a class="list-group-item active">Вкладені файли</a>
                <a href="/incoming_allfiles" class="list-group-item"><span class="badge">${incomingFilesCount}</span></span> Вхідні файли</a>
                <a href="/outcoming_allfiles" class="list-group-item"><span class="badge">${outcomingFilesCount}</span> Вихідні файли</a>
                <a href="/innercoming_allfiles" class="list-group-item"><span class="badge">${innercomingFilesCount}</span> Внутрішні файли</a>
            </div>
        </div>
    </div>
    <div>
        <div class="col-sm-4">
            <div class="list-group">
                <a class="list-group-item active">Додати новий пункт довідника</a>
                <a href="/type_add_page" class="list-group-item">Новий тип документа</a>
                <a href="/outorganization_add_page" class="list-group-item">Нова стороння організація</a>
                <a href="/employee_add_page" class="list-group-item">Новий співробітник</a>
                <a href="/executor_add_page" class="list-group-item">Новий виконавець</a>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="list-group">
                <a class="list-group-item active">Довідники</a>
                <a href="/type" class="list-group-item"><span class="badge"></span></span> Довідник типів документів</a>
                <a href="/outorganization" class="list-group-item"><span class="badge"></span> Довідник сторонніх організацій</a>
                <a href="/employee" class="list-group-item"><span class="badge"></span> Довідник співробітників</a>
                <a href="/executor" class="list-group-item"><span class="badge"></span> Довідник виконавців</a>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
