<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html>
<head>
    <title>Електронна кореспонденція: внутрішні документи</title>
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
                <li class="active"><a href="/incoming">Журнали</a></li>
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

    <div style="margin-left:30px; margin-right: 30px;">
        <nav class="navbar navbar-default">
            <ul class="nav nav-pills">
                <li><a href="/incoming">Вхідні документи</a></li>
                <li><a href="/outcoming">Вихідні документи</a></li>
                <li class="active"><a href="/innercoming">Внутрішні документи</a></li>
            </ul>
        </nav>

        <nav class="navbar navbar-default">
            <ul class="nav nav-pills">
                <li><a id="add_innercoming" class="btn btn-default"><span class="glyphicon glyphicon-plus"
                                                                       style="color:green;"></span> Новий</a></li>
                <li><a id="update_innercoming" class="btn btn-default"><span class="glyphicon glyphicon-pencil"
                                                                          style="color:orange;"></span> Змінити</a></li>
                <li><a id="delete_innercoming" class="btn btn-default"><span class="glyphicon glyphicon-minus"
                                                                          style="color:red;"></span> Видалити</a></li>
                <li><a id="files_innercoming" class="btn btn-default"><span class="glyphicon glyphicon-paperclip"
                                                                         style="color:brown;"></span> Файли</a></li>
            </ul>
        </nav>
        <div>
            <table id="myTable" class="table table-bordered table-hover" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <td>№ внутрішнього</td>
                    <td>Дата внутрішнього</td>
                    <td>Тип внутрішнього</td>
                    <td>Відправник</td>
                    <td>Одержувач</td>
                    <td>Заголовок</td>
                    <td>Дата контролю</td>
                    <td>Виконавець</td>
                    <td>Дата виконання</td>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>№ внутрішнього</td>
                    <td>Дата внутрішнього</td>
                    <td>Тип внутрішнього</td>
                    <td>Відправник</td>
                    <td>Одержувач</td>
                    <td>Заголовок</td>
                    <td>Дата контролю</td>
                    <td>Виконавець</td>
                    <td>Дата виконання</td>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${innercomings}" var="innercoming">
                    <tr data-value="${innercoming.id}">
                        <td>${innercoming.innerDocRegNo}</td>
                        <td><fmt:formatDate value="${innercoming.innerDocRegDate}" pattern="dd.MM.yyyy"/></td>
                        <td>${innercoming.innerDocType}</td>
                        <td>${innercoming.innerDocSender}</td>
                        <td>${innercoming.innerDocReciever}</td>
                        <td>${innercoming.innerDocTitle}</td>
                        <c:choose>
                            <c:when test="${(not empty innercoming.innerDocControlDate) && (now gt innercoming.innerDocControlDate) && (empty innercoming.innerDocFulfilDate)}">
                                <td style="background-color:rgba(255, 160, 160, 0.75);">
                            </c:when>
                            <c:when test="${(not empty innercoming.innerDocControlDate) && (now le innercoming.innerDocControlDate) && (empty innercoming.innerDocFulfilDate)}">
                                <td style="background-color:rgba(255, 250, 160, 0.75);">
                            </c:when>
                            <c:otherwise>
                                <td>
                            </c:otherwise>
                        </c:choose>
                                <fmt:formatDate value="${innercoming.innerDocControlDate}" pattern="dd.MM.yyyy"/></td>
                        <td>${innercoming.innerDocExecutor}</td>
                        <td><fmt:formatDate value="${innercoming.innerDocFulfilDate}" pattern="dd.MM.yyyy"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
        <form style="display: none" action="/incoming_update_page" method="POST" id="formidupdate">
            <input type="hidden" id="toUpdate" name="toUpdate" value=""/>
        </form>
        <form style="display: none" action="/incoming_files_page" method="POST" id="formidfiles">
            <input type="hidden" id="toFiles" name="toFiles" value=""/>
        </form>
    <script type="text/javascript">

        $('#add_innercoming').click(function () {
            window.location.href = '/innercoming_add_page';
        })

        $(document).ready(function () {
            var table = $('#myTable').DataTable({
                        "order": [[ 0, "desc" ]],
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

            $('#myTable tbody').on('click', 'tr', function () {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });

            $('#delete_innercoming').click(function () {
                if (table.rows('.selected').data().length > 0) {
                    if (confirm("Видалити запис?")) {
                        var data = table.$('tr.selected').data('value');
                        $.post("/innercoming/delete", 'toDelete=' + data);
                        table.row('.selected').remove().draw(false);
//                    alert(table.$('tr.selected').data('value'));
                    }
                }
            });

            $('#update_innercoming').click(function () {
                if (table.rows('.selected').data().length > 0) {
                    var data = table.$('tr.selected').data('value');
                    $("#toUpdate").val(data);
                    $("#formidupdate").submit();
                }
            });

            $('#files_innercoming').click(function () {
                if (table.rows('.selected').data().length > 0) {
                    var data = table.$('tr.selected').data('value');
                    $("#toFiles").val(data);
                    $("#formidfiles").submit();
                }
            });
        });
    </script>
    </sec:authorize>
    </body>
</html>
