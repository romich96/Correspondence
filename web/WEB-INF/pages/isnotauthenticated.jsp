<sec:authorize access="!isAuthenticated()">
    <div class="container" style="width: 600px;">
        <div class="jumbotron" style="margin-top: 20px; text-align: center;">

            <p class="lead">Electronic correspondence.</p>

            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Login</a></p>
            <i>Login: reg 1234</i>
        </div>
        <div class="footer">
            <p>&copy; Shamil Ibatullin 2016</p>
        </div>
    </div>
</sec:authorize>

