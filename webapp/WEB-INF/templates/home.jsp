<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Home"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="panel panel-default">
    <div class="panel-heading">Panel heading</div>
    <div class="panel-body">
    <%@ include file="/WEB-INF/partials/product_list.jspf" %>
    </div></div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
