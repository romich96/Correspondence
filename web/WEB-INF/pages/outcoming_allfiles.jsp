<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Електронна кореспонденція: вихідні файли</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="isnotauthenticated.jsp" %>
    <sec:authorize access="isAuthenticated()">
    <nav id="inhead" class="navbar navbar-default navbar-static-top" style="margin-left: 30px; margin-right: 30px;">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/"> Кореспонденція </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/incoming">Журнали</a></li>
                <li><a href="/type">Довідники</a></li>
                <li class="active"><a href="/incoming_allfiles">Файли</a></li>
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

    <div style="margin-left:30px; margin-right: 30px;">
        <nav class="navbar navbar-default">
            <ul class="nav nav-pills">
                <li><a href="/incoming_allfiles">Вхідні файли</a></li>
                <li class="active"><a href="/outcoming_allfiles">Вихідні файли</a></li>
                <li><a href="/innercoming_allfiles">Внутрішні файли</a></li>
            </ul>
        </nav>
        <div>
            <% int i = 0;%>
            <table id="myTable" class="table table-hover table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <td>№</td>
                    <td>Назва файлу</td>
                    <td align="center">Розмір</td>
                    <td align="center">Дата і час</td>
                    <td align="center">Скачати</td>
                    <td>№ вихідного</td>
                    <td>Дата вихідного</td>
                    <td>Тип вихідного</td>
                    <td>Заголовок вихідного</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${outcoming_allfiles}" var="file">
                    <tr data-value="${file.id}">
                        <td align="right"><%=++i%>
                        </td>
                        <td>${file.fileName}</td>
                        <td align="right"><fmt:formatNumber type="number"
                                                            maxFractionDigits="0"
                                                            value="${file.fileSize/1024}"/> КБ
                        </td>
                        <td align="center">${file.fileChangedDate}</td>
                        <td align="center"><a href="/download/outbox/${file.id}/${file.fileHash}"><span
                                class="glyphicon glyphicon-download"
                                style="color:blue;"></span> Скачати</a></td>
                        <td>${file.getOutcoming().outDocRegNo}</td>
                        <td>
                            <fmt:formatDate value="${file.getOutcoming().outDocRegDate}"
                                            type="date"
                                            pattern="dd.MM.yyyy"
                                            var="theFormattedoutDocRegDate"/>
                                            ${theFormattedoutDocRegDate}
                        </td>
                        <td>${file.getOutcoming().outDocType}</td>
                        <td>${file.getOutcoming().outDocTitle}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            var table = $('#myTable').DataTable({
                        scrollX: true,
                        paging: true,
                        "language": {
                            "zeroRecords": "Нічого не знайдено",
                            "search": "Пошук:",
                            "infoEmpty": "Дані відсутні",
                            "info": "Показано від _START_ до _END_ із _TOTAL_ записів",
                            "infoFiltered": "(показано із _MAX_ записів)",
                            "paginate": {
                                "first": "Перша",
                                "last": "Остання",
                                "next": "Наступна",
                                "previous": "Попередня"
                            },
                            "lengthMenu": "Показати _MENU_ записів"
                        }
                    }
            );
        });
    </script>
    </sec:authorize>
    </body>
</html>
