<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Електронна кореспонденція: файли вихідного документа</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <div class="container" style="margin-top: 30px;">
        <div class="well">
            <h3>Файли вихідного документа</h3>
            <h4>№ ${outcoming.outDocRegNo} від <fmt:formatDate value="${outcoming.outDocRegDate}"
                                                             type="date"
                                                             pattern="dd.MM.yyyy"
                                                             var="theFormattedoutDocRegDate"/>${theFormattedoutDocRegDate} ${outcoming.outDocType} ${outcoming.outDocTitle} </h4>
        </div>
        <input type="hidden" id="id" name="id" value="${id}"/>

        <div>
            <% int i = 0;%>
            <table class="table table-hover table-bordered" style="margin-top: 30px" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <td align="center"><b>№</b></td>
                    <td align="center"><b>Назва файлу</b></td>
                    <td align="center"><b>Розмір</b></td>
                    <td align="center"><b>Дата і час</b></td>
                    <td align="center"><b>Скачати</b></td>
                    <td align="center"><b>Удалити</b></td>
                </tr>
                </thead>

                <c:forEach items="${filesToDelete}" var="file">
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
                        <td align="center"><a class="confirmation" style="cursor:pointer"><span
                                class="glyphicon glyphicon-remove"
                                style="color:red;"></span> Удалити</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <script>
        $(function () {
            var elems = document.getElementsByClassName('confirmation');
            var confirmIt = function (e) {
                if (!confirm('Видалити файл?')) {
                    e.preventDefault();
                }
                else {
                    var data = $(this).parents('tr').data('value');
                    $.post("/remove/outcoming_file", 'toDelete=' + data);
                    $(this).parents('tr').remove();
                }
            };
            for (var i = 0, l = elems.length; i < l; i++) {
                elems[i].addEventListener('click', confirmIt, false);
            }
        });
    </script>
</sec:authorize>
</body>
</html>