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
        <div class="panel-heading">Balance</div>
        <div class="panel-body">
            <c:if test="${message != null}">
                <div  class="alert alert-dismissible alert-success" >
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <p>${message}</p>
                </div>
            </c:if>
            <h3>Your balance: $${balance}</h3>
            <form action="/page/profile/balance" class="form-inline" method="post">
            <input type="text" class="form-control" name="amount" placeholder="Amount">
            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>Add balance</button>
            </form>
      </div></div>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
