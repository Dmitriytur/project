<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Profile"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-3">
        <div class="list-group">
            <a href="/page/profile" class="list-group-item">Overview<i class="glyphicon glyphicon-user pull-right"></i>
            </a>
            <a href="/page/profile/subscriptions" class="list-group-item">Subscriptions<i class="fa fa-leanpub pull-right"></i>
            </a>
            <a href="/page/profile/settings" class="list-group-item">Profile info<i class="glyphicon glyphicon-cog pull-right"></i>
            </a>
        </div>
    </div>
    <div class="col-md-9">
        <div class="panel panel-default">
      <div class="panel-heading">Change password</div>
      <div class="panel-body">
          <div id="errorMessage" class="alert alert-dismissible alert-warning" style="display: none">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <p>errorMessage</p>
          </div>
          <div id="successMessage" class="alert alert-dismissible alert-success" style="display: none">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <p>Password was updated</p>
          </div>
          <div class="form-group">
              <div id="oldPassword" class="form-group">
                  <label class="control-label">Old password</label>
                  <input type="password" class="form-control" placeholder="Password">
                  <span class="help-block"></span>
              </div>

              <div id="password" class="form-group">
                  <label class="control-label">New password</label>
                  <input type="password" class="form-control" placeholder="Password">
                  <span class="help-block"></span>
              </div>

              <div id="confirmPassword" class="form-group">
                  <label class="control-label">Repeat password</label>
                  <input type="password" class="form-control" placeholder="Confirm password">
                  <span class="help-block"></span>
              </div>


              <button id='btnPassword' class="btn btn-lg btn-success btn-block ">Change password</button>
          </div>
      </div>
</div>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
