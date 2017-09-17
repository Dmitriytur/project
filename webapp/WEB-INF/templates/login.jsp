<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">Sign in</div>
            <div class="panel-body">

                <div class="form-group">
                    <label class="control-label">Username</label>
                    <div class="input-group input-border">
                        <span class="input-group-addon glyphicon glyphicon-user"></span>
                        <input type="text" class="form-control" placeholder="Username">
                    </div>
                    <label class="control-label">Password</label>
                    <div class="input-group input-border">
                        <span class="input-group-addon glyphicon glyphicon-lock"></span>
                        <input type="password" class="form-control"  placeholder="Password">
                    </div>

                    <button id='btnLogin' class="btn btn-lg btn-success btn-block">Sign in</button>
                </div>

            </div>
        </div>

    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
