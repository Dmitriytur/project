<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Message"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">${message}</h1>
        <p class="text-center"><a class="btn btn-primary" href="/page/home"><i class="fa fa-home"></i> Take Me Home</a></p>
    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
