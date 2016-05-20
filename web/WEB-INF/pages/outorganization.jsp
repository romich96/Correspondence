<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Електронна кореспонденція: довідник сторонніх організацій</title>
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
                <li class="active"><a href="/type">Довідники</a></li>
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
                <li><a href="/type">Типи документів</a></li>
                <li class="active"><a href="/outorganization">Сторонні організації</a></li>
                <li><a href="/employee">Співробітники</a></li>
                <li><a href="/executor">Виконавці</a></li>
            </ul>
        </nav>

        <div>
            <nav class="navbar navbar-default">
                <ul class="nav nav-pills">
                    <li><a id="add_outorganization" class="btn btn-default"><span class="glyphicon glyphicon-plus"
                                                                       style="color:green;"></span>
                        Новий</a></li>
                    <li><a id="update_outorganization" class="btn btn-default"><span class="glyphicon glyphicon-pencil"
                                                                          style="color:orange;"></span> Змінити</a></li>
                    <li><a id="delete_outorganization" class="btn btn-default"><span class="glyphicon glyphicon-minus"
                                                                          style="color:red;"></span> Видалити</a></li>
                    </li>
                </ul>
            </nav>
            <table id="myTable" class="table table-bordered table-hover" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <td>Стороння організація</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${outorganizations}" var="outorganization">
                    <tr data-value="${outorganization.id}">
                        <td>${outorganization.name}</td>
                    </tr>
                </c:forEach>
                </tbody>

                <form style="display: none" action="/outorganization_update_page" method="POST" id="formid">
                    <input type="hidden" id="varid" name="toUpdate" value=""/>
                </form>
            </table>
        </div>
    </div>
    <script type="text/javascript">

        $('#add_outorganization').click(function () {
            window.location.href = '/outorganization_add_page';
        })

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

            $('#myTable tbody').on('click', 'tr', function () {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });

            $('#delete_outorganization').click(function () {
                if (table.rows('.selected').data().length > 0) {
                    if (confirm("Видалити запис?")) {
                        var data = table.$('tr.selected').data('value');
                        $.post("/outorganization/delete", 'toDelete=' + data);
                        table.row('.selected').remove().draw(false);
                    }
                }
            });

            $('#update_outorganization').click(function () {
                if (table.rows('.selected').data().length > 0) {
                    var data = table.$('tr.selected').data('value');

                    $("#varid").val(data);

                    $("#formid").submit();
                }
            });
        });
    </script>
</sec:authorize>
</body>
</html>
