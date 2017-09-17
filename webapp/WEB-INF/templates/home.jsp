<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Home"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-12 v-space"></div>

    <c:forEach items="${periodicalsList}" var="item" varStatus="loop">
        <c:if test="${loop.index % 4 == 0}">
            <div class="row"></c:if>
            <div class="col-md-3 product-preview-box">

                <a href="#" class="thumbnail text-center product-preview">
                    <img src="${item.image}" class="img-preview" alt="magazine">
                    <p>
                        <span class="thumbnail-bold">Name:
                        </span>${item.name}</p>
                    <p>
                        <span class="thumbnail-bold">Price:
                        </span>$${item.price}</p>

                </a>
            </div>
            <c:if test="${loop.index % 4 == 3}"></div>
        </c:if>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
