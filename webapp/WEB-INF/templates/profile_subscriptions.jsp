<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Profile"/>
<c:set var="pageStyle" value="/css/profile.css"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-3">
        <div class="list-group">
            <a href="/page/profile" class="list-group-item">Overview<i class="glyphicon glyphicon-user pull-right"></i>
            </a>
            <a href="/page/profile/subscriptions" class="list-group-item active">Subscriptions<i class="fa fa-leanpub pull-right"></i>
            </a>
            <a href="/page/profile/settings" class="list-group-item">Profile info<i class="glyphicon glyphicon-cog pull-right"></i>
            </a>
        </div>
    </div>
    <div class="col-md-9">
        <div class="panel panel-default">
      <div class="panel-heading">Subscriptions</div>
      <div class="panel-body">
          <table class="table table-striped table-hover ">
              <thead>
                  <tr>
                      <th>Magazine</th>
                      <th>Start date</th>
                      <th>End date</th>
                      <th>Active</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach items="${subscriptions}" var="item">
                      <tr>
                           <td><a href="/page/magazines?item=${item.subscription.periodicalId}">${item.periodicalName}</a></td>
                          <td>${item.subscription.startDate}</td>
                          <td>${item.subscription.endDate}</td>
                          <c:if test="${item.active}">
                              <td style="color:green">Active</td>
                          </c:if>
                          <c:if test="${!item.active}">
                              <td style="color:red">Out-of-date</td>
                          </c:if>
                     </tr>
                  </c:forEach>
              </tbody>
        </table>
      </div>
</div>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
