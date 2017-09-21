<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Home"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">

    <c:forEach items="${popularPeriodicals}" var="item">
        <div class="panel panel-default">
        <div class="panel-heading">${item.key}</div>
        <div class="panel-body">


            <c:set var="periodicalsList" value="${item.value}"></c:set>
            <%@ include file="/WEB-INF/partials/product_list.jspf" %>
        </div></div>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
