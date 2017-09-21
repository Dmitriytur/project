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
            <a href="/page/profile/settings" class="list-group-item active">Profile info<i class="glyphicon glyphicon-cog pull-right"></i>
            </a>
        </div>
    </div>
    <div class="col-md-9">
        <div class="panel panel-default">
      <div class="panel-heading">Profile</div>
      <div class="panel-body">
          <c:if test="${errorMessage != null}">
              <div  class="alert alert-dismissible alert-warning" >
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <p>${errorMessage}</p>
            </div>
          </c:if>
          <c:if test="${message != null}">
              <div class="alert alert-dismissible alert-success" >
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <p>${message}</p>
            </div>
          </c:if>
          <form  action="/page/profile/settings" method="post">
          <div class="form-group">

              <div  class="form-group">
                  <label class="control-label">First name</label>
                  <input name="firstName" type="text" class="form-control" placeholder="First name" required maxlength="20" value="${profile.firstName}">
                  <span class="help-block"></span>
              </div>

              <div class="form-group">
                  <label class="control-label">Last name</label>
                  <input name="lastName" type="text" class="form-control" placeholder="Last name" required maxlength="20" value="${profile.lastName}">
                  <span class="help-block"></span>
              </div>

              <div class="form-group">
                  <label class="control-label">City</label>
                  <input name="city" type="text" class="form-control" placeholder="City" required maxlength="20"value="${profile.city}">
                  <span class="help-block"></span>
              </div>

              <div  class="form-group">
                  <label class="control-label">Address</label>
                  <input name="address" type="text" class="form-control" placeholder="Address" required maxlength="50" value="${profile.address}">
                  <span class="help-block"></span>
              </div>

              <div  class="form-group">
                  <label class="control-label">Zip-code</label>
                  <input name="zip" type="text" class="form-control" placeholder="Zip-code" required maxlength="10" value="${profile.zipCode}">
                  <span class="help-block"></span>
              </div>
              <input name="new" hidden="hidden" value="${profile == null}">



              <button  type="submit" class="btn btn-lg btn-success btn-block ">Save</button>
          </div>
          </form>
      </div>
</div>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
