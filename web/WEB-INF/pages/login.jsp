<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Електронна кореспонденція: авторизація</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container" style="width: 300px; text-align: center;">
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Авторизація</h2>
        <input type="text" class="form-control" name="j_username" placeholder="логін" required autofocus value="">
        <input type="password" class="form-control" name="j_password" placeholder="пароль" required value="">
        <p></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
    </form>
</div>

</body>
</html>
