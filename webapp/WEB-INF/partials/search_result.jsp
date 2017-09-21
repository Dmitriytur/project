<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/partials/product_list.jspf" %>

<c:if test="${pagination.lastPage > 1}">
<div class="row text-center">
    <ul class="pagination">
        <c:if test="${pagination.prevActive}">
            <li class="prevPage"><a>&laquo;</a></li>
        </c:if>
        <c:if test="${!pagination.prevActive}">
            <li class="prevPage disabled"><a>&laquo;</a></li>
        </c:if>
        <c:forEach begin="${pagination.firstPage}" end="${pagination.lastPage}" var="page">
            <c:if test="${page == pagination.currentPage}">
                <li class="active"><a class="pageLink">${page}</a></li>
            </c:if>
            <c:if test="${page != pagination.currentPage}">
                <li><a class="pageLink">${page}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${pagination.nextActive}">
            <li class="nextPage"><a>&raquo;</a></li>
        </c:if>
        <c:if test="${!pagination.nextActive}">
            <li class="nextPage disabled"><a>&raquo;</a></li>
        </c:if>
    </ul>
</div>
</c:if>
