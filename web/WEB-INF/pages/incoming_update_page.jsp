<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Електронна кореспонденція: редагувати вхідний документ</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="isnotauthenticated.jsp" %>
<sec:authorize access="isAuthenticated()">
    <div class="container">
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/incoming/update" method="post">
            <h3>Редагувати вхідний документ</h3>

            <input type="hidden" id="id" name="id" value="${id}"/>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocRegNo">Номер вхідного</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inDocRegNo" name="inDocRegNo"
                           value="${incoming.inDocRegNo}" placeholder="Номер вхідного документа">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocRegDate">Дата вхідного</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${incoming.inDocRegDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinDocRegDate"/>
                    <input type="date" class="form-control" id="inDocRegDate" name="inDocRegDate"
                           value="${theFormattedinDocRegDate}"
                           placeholder="Дата вхідного документа у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocType">Тип вхідного</label>

                <div class="col-sm-9">
                    <input list="types" class="form-control" id="inDocType" name="inDocType"
                           value="${incoming.inDocType}"
                           placeholder="Тип вхідного документа">
                    <datalist id="types">
                        <c:forEach items="${types}" var="type">
                        <option value='${type.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocSender">Відправник</label>

                <div class="col-sm-9">
                    <input list="outorganizations" class="form-control" id="inDocSender" name="inDocSender"
                           value="${incoming.inDocSender}"
                           placeholder="Відправник вхідного документа (від кого)">
                    <datalist id="outorganizations">
                        <c:forEach items="${outorganizations}" var="outorganization">
                        <option value='${outorganization.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocReciever">Одержувач</label>

                <div class="col-sm-9">
                    <input list="employees" class="form-control" id="inDocReciever" name="inDocReciever"
                           value="${incoming.inDocReciever}" placeholder="Одержувач вхідного документа (кому)">
                    <datalist id="employees">
                        <c:forEach items="${employees}" var="employee">
                        <option value='${employee.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocTitle">Заголовок документа</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inDocTitle" name="inDocTitle"
                           value="${incoming.inDocTitle}" placeholder="Заголовок документа (короткий опис змісту)">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocResolution">Резолюція</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inDocResolution" name="inDocResolution"
                           value="${incoming.inDocResolution}"
                           placeholder="Резолюція керівника (кому, прийняте рішення)">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocRezoDate">Дата резолюції</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${incoming.inDocRezoDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinDocRezoDate"/>
                    <input type="date" class="form-control" id="inDocRezoDate" name="inDocRezoDate"
                           value="${theFormattedinDocRezoDate}" placeholder="Дата резолюції у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocControlDate">Дата контролю</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${incoming.inDocControlDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinDocControlDate"/>
                    <input type="date" class="form-control" id="inDocControlDate" name="inDocControlDate"
                           value="${theFormattedinDocControlDate}" placeholder="Дата контролю у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocExecutor">Виконавець</label>

                <div class="col-sm-9">
                    <input list="executors" class="form-control" id="inDocExecutor" name="inDocExecutor"
                           value="${incoming.inDocExecutor}" placeholder="Виконавець документу">
                    <datalist id="executors">
                        <c:forEach items="${executors}" var="executor">
                        <option value='${executor.name}'>
                            </c:forEach>
                    </datalist>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inDocFulfilDate">Дата виконання</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${incoming.inDocFulfilDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedinDocFulfilDate"/>
                    <input type="date" class="form-control" id="inDocFulfilDate" name="inDocFulfilDate"
                           value="${theFormattedinDocFulfilDate}" placeholder="Дата виконання у форматі yyyy-mm-dd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocRegNo">Номер нашого вихідного</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control" id="outDocRegNo" name="outDocRegNo"
                           value="${incoming.outDocRegNo}"
                           placeholder="Номер нашого вихідного (відповідь на цей вхідний)">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="outDocRegDate">Дата нашого вихідного</label>

                <div class="col-sm-9">
                    <fmt:formatDate value="${incoming.outDocRegDate}"
                                    type="date"
                                    pattern="yyyy-MM-dd"
                                    var="theFormattedoutDocRegDate"/>
                    <input type="date" class="form-control" id="outDocRegDate" name="outDocRegDate"
                           value="${theFormattedoutDocRegDate}"
                           placeholder="Дата нашого вихідного у форматі yyyy-mm-dd">
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