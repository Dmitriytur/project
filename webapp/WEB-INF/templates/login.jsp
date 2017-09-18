<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">Sign in</div>
            <div class="panel-body">

                <c:if test="${errorMessage != null}">
                    <div class="alert alert-dismissible alert-warning">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <p>${errorMessage}</p>
                    </div>
                </c:if>
                <form id="loginForm" action="/page/login" method="post">
                    <div class="form-group">
                        <label class="control-label">Username</label>
                        <div class="input-group input-border">
                            <span class="input-group-addon glyphicon glyphicon-user"></span>
                            <input id="username" name="username" type="text" class="form-control" placeholder="Username" required>
                        </div>
                        <label class="control-label">Password</label>
                        <div class="input-group input-border">
                            <span class="input-group-addon glyphicon glyphicon-lock"></span>
                            <input id="password" name="password" type="password" class="form-control" placeholder="Password" required>
                        </div>

                        <button id='btnLogin' class="btn btn-lg btn-success btn-block" type="submit">Sign in</button>
                    </div>
                </form>

            </div>
        </div>

    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
