<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Profile"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-3">
        <div class="list-group">
            <a href="/page/profile" class="list-group-item active">Overview<i class="glyphicon glyphicon-user pull-right"></i>
            </a>
            <a href="/page/profile/subscriptions" class="list-group-item">Subscriptions<i class="fa fa-leanpub pull-right"></i>
            </a>
            <a href="/page/profile/settings" class="list-group-item">Profile info<i class="glyphicon glyphicon-cog pull-right"></i>
            </a>
        </div>
    </div>
    <div class="col-md-9">
        <div class="panel panel-default">
      <div class="panel-heading">Overview</div>
      <div class="panel-body">
          <ul class="list-group">
             <li class="list-group-item"><i class="glyphicon glyphicon-user"></i> ${user.userName}</li>
             <li class="list-group-item"><i class="fa fa-envelope"></i> ${user.email}</li>
             <li class="list-group-item"><i class="fa fa-money"></i> Balance: $${user.balance}
             </li>
             <div class="v-space-small"></div>
            <a href="/page/profile/balance" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> Repelish balance</a>
            <a href="/page/profile/password" class="btn btn-success"><i class="fa fa-lock"></i> Change password</a>
          </ul>

      </div>
</div>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
