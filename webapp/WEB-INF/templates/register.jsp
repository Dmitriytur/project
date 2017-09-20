<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Register"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">Sign up</div>
            <div class="panel-body">
                <div id="errorMessage" class="alert alert-dismissible alert-warning" style="display: none">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <p>errorMessage</p>
                </div>
                <div class="form-group">

                    <div id="username" class="form-group">
                        <label class="control-label">Username</label>
                        <input type="text" class="form-control" placeholder="Username">
                        <span class="help-block"></span>
                    </div>

                    <div id="email" class="form-group">
                        <label class="control-label">Email</label>
                        <input type="text" class="form-control" placeholder="Email">
                        <span class="help-block"></span>
                    </div>

                    <div id="password" class="form-group">
                        <label class="control-label">Password</label>
                        <input type="password" class="form-control" placeholder="Password">
                        <span class="help-block"></span>
                    </div>

                    <div id="confirmPassword" class="form-group">
                        <label class="control-label">Confirm password</label>
                        <input type="password" class="form-control" placeholder="Confirm password">
                        <span class="help-block"></span>
                    </div>


                    <button id='btnRegister' class="btn btn-lg btn-success btn-block ">Register</button>
                </div>

            </div>
        </div>

    </div>
</div>


<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
