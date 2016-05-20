<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Електронна кореспонденція: редагувати вихідний документ</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/outcoming/update" method="post">
            <h3>Редагувати вихідний документ</h3>

            <input type="hidden" id="id" name="id" value="${id}"/>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocRegNo">Номер вихідного</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="outDocRegNo" name="outDocRegNo"
                           value="${outcoming.outDocRegNo}" placeholder="Номер вихідного документа">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocRegDate">Дата вихідного</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${outcoming.outDocRegDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedoutDocRegDate"/>
                    <input type="date" class="form-control" id="outDocRegDate" name="outDocRegDate"
                           value="${theFormattedoutDocRegDate}"
                           placeholder="Дата вихідного документа у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocType">Тип вихідного</label>

                <div class="col-sm-9">
                    <input list="types" class="form-control" id="outDocType" name="outDocType"
                           value="${outcoming.outDocType}"
                           placeholder="Тип вихідного документа">
                    <datalist id="types">
                        <c:forEach items="${types}" var="type">
                        <option value='${type.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocSender">Відправник</label>

                <div class="col-sm-9">
                    <input list="employees" class="form-control" id="outDocSender" name="outDocSender"
                           value="${outcoming.outDocSender}"
                           placeholder="Відправник вихідного документа (від кого)">
                    <datalist id="employees">
                        <c:forEach items="${employees}" var="employee">
                        <option value='${employee.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocReciever">Одержувач</label>

                <div class="col-sm-9">
                    <input list="outorganizations" class="form-control" id="outDocReciever" name="outDocReciever"
                           value="${outcoming.outDocReciever}" placeholder="Одержувач вихідного документа (кому)">
                    <datalist id="outorganizations">
                        <c:forEach items="${outorganizations}" var="outorganization">
                        <option value='${outorganization.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocTitle">Заголовок документа</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="outDocTitle" name="outDocTitle"
                           value="${outcoming.outDocTitle}" placeholder="Заголовок документа (короткий опис змісту)">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocControlDate">Дата контролю</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${outcoming.outDocControlDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedoutDocControlDate"/>
                    <input type="date" class="form-control" id="outDocControlDate" name="outDocControlDate"
                           value="${theFormattedoutDocControlDate}" placeholder="Дата контролю у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocExecutor">Виконавець</label>

                <div class="col-sm-9">
                    <input list="executors" class="form-control" id="outDocExecutor" name="outDocExecutor"
                           value="${outcoming.outDocExecutor}" placeholder="Виконавець документу">
                    <datalist id="executors">
                        <c:forEach items="${executors}" var="executor">
                        <option value='${executor.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocFulfilDate">Дата виконання</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${outcoming.outDocFulfilDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedoutDocFulfilDate"/>
                    <input type="date" class="form-control" id="outDocFulfilDate" name="outDocFulfilDate"
                           value="${theFormattedoutDocFulfilDate}" placeholder="Дата виконання у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocRegNo">Номер вхідного</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inDocRegNo" name="inDocRegNo"
                           value="${outcoming.inDocRegNo}"
                           placeholder="Номер вхідного у одержувача">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocRegDate">Дата вхідного</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${outcoming.inDocRegDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinDocRegDate"/>
                    <input type="date" class="form-control" id="inDocRegDate" name="inDocRegDate"
                           value="${theFormattedinDocRegDate}"
                           placeholder="Дата вхідного одержувача у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="upfiles">Додати файли</label>

                <div class="col-sm-9" id="upfiles">
                    <div class="input-group">
                <span class="input-group-btn">
                    <span class="btn btn-primary btn-file">
                        Відкрити&hellip; <input type="file" name="upfiles[]" multiple>
                    </span>
                </span>
                        <input type="text" class="form-control" readonly>
                    </div>
                <span class="help-block">
                    Можна додати декілька файлів (.doc, .docx, .ppt, .pptx, .pdf, .txt)
                </span>

                </div>
            </div>
            <input type="submit" class="btn btn-primary" value="Внести зміни до запису">
        </form>
    </div>

    <script>
        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                    numFiles = input.get(0).files ? input.get(0).files.length : 1,
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [numFiles, label]);
        });

        $(document).ready(function () {
            $('.btn-file :file').on('fileselect', function (event, numFiles, label) {
                var input = $(this).parents('.input-group').find(':text'),
                        log = numFiles > 1 ? numFiles + ' файли(ів) обрано' : label;
                if (input.length) {
                    input.val(log);
                } else {
                    if (log) alert(log);
                }
            });
        });
    </script>
</sec:authorize>
</body>
</html>