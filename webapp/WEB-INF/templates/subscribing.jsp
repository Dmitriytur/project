<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/ratingstars.tld" prefix="m" %>
<c:set var="title" value="Subscribing"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="col-md-3 product-preview-box">

        <a  class="thumbnail text-center product-preview">
            <img src="/img/magazines/${periodical.id}/1.jpg" class="img-preview" alt="magazine">
            <p>
                <span class="thumbnail-bold">Name:
                </span>${periodical.name}</p>
            <p>
                <span class="thumbnail-bold">Price:
                </span>$${periodical.price}</p>
            <p>
                <div class="rating-stars">
                    <m:ratingStars rating="${periodical.rating}" />
                </div>

            </p>

        </a>
    </div>
    <div class="col-md-9">
        <div class="panel panel-default">
        <div class="panel-heading">Subscription</div>
        <div class="panel-body">
            <c:if test="${errorMessage != null}">
                <div  class="alert alert-dismissible alert-warning" >
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <p>${errorMessage}</p>
              </div>
            </c:if>
            <ul class="list-group">
               <li class="list-group-item"><h3>Price: $${periodical.price}</h3></li>
               <li class="list-group-item"><h3>Published
                   <c:if test="${periodical.periodicity < 2}">
                       monthly
                   </c:if>
                   <c:if test="${periodical.periodicity >= 2}">
                       ${periodical.periodicity} times per month
                   </c:if></li>
                <li class="list-group-item"><h3>Period (months): ${period}</h3></li>
               <li class="list-group-item"><h3>Total: $${periodical.price * period * periodical.periodicity}</h3></li>

               <div class="v-space-small"></div>

               <form action="/page/subscribe" method="post">
                <input type="hidden" value="${periodical.id}" name="periodicalId">
                <input type="hidden" value="${period}" name="period">
                   <button type="submit"  class="btn btn-success">Subscribe</button>
                </form>
            </ul>
        </div></div>

    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
<script src="/js/open_review.js" charset="utf-8"></script>
