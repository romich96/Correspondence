<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Електронна кореспонденція: редагувати внутрішний документ</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/innercoming/update" method="post">
            <h3>Редагувати внутрішний документ</h3>

            <input type="hidden" id="id" name="id" value="${id}"/>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocRegNo">Номер внутрішнього</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="innerDocRegNo" name="innerDocRegNo"
                           value="${innercoming.innerDocRegNo}" placeholder="Номер внутрішнього документа">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocRegDate">Дата внутрішнього</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${innercoming.innerDocRegDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinnerDocRegDate"/>
                    <input type="date" class="form-control" id="innerDocRegDate" name="innerDocRegDate"
                           value="${theFormattedinnerDocRegDate}"
                           placeholder="Дата внутрішного документа у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocType">Тип внутрішного</label>

                <div class="col-sm-9">
                    <input list="types" class="form-control" id="innerDocType" name="innerDocType"
                           value="${innercoming.innerDocType}"
                           placeholder="Тип внутрішнього документа">
                    <datalist id="types">
                        <c:forEach items="${types}" var="type">
                        <option value='${type.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocSender">Відправник</label>

                <div class="col-sm-9">
                    <input list="employees1" class="form-control" id="innerDocSender" name="innerDocSender"
                           value="${innercoming.innerDocSender}"
                           placeholder="Відправник внутрішнього документа (від кого)">
                    <datalist id="employees1">
                        <c:forEach items="${employees}" var="employee">
                        <option value='${employee.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocReciever">Одержувач</label>

                <div class="col-sm-9">
                    <input list="employees2" class="form-control" id="innerDocReciever" name="innerDocReciever"
                           value="${innercoming.innerDocReciever}" placeholder="Одержувач вхідного документа (кому)">
                    <datalist id="employees2">
                        <c:forEach items="${employees}" var="employee">
                        <option value='${employee.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocTitle">Заголовок документа</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="innerDocTitle" name="innerDocTitle"
                           value="${innercoming.innerDocTitle}" placeholder="Заголовок документа (короткий опис змісту)">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocControlDate">Дата контролю</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${innercoming.innerDocControlDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinnerDocControlDate"/>
                    <input type="date" class="form-control" id="innerDocControlDate" name="innerDocControlDate"
                           value="${theFormattedinnerDocControlDate}" placeholder="Дата контролю у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocExecutor">Виконавець</label>

                <div class="col-sm-9">
                    <input list="executors" class="form-control" id="innerDocExecutor" name="innerDocExecutor"
                           value="${innercoming.innerDocExecutor}" placeholder="Виконавець документу">
                    <datalist id="executors">
                        <c:forEach items="${executors}" var="executor">
                        <option value='${executor.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="innerDocFulfilDate">Дата виконання</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${innercoming.innerDocFulfilDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinnerDocFulfilDate"/>
                    <input type="date" class="form-control" id="innerDocFulfilDate" name="innerDocFulfilDate"
                           value="${theFormattedinnerDocFulfilDate}" placeholder="Дата виконання у форматі yyyy-mm-dd">
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