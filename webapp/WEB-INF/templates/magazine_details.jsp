<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/ratingstars.tld" prefix="m" %>
<c:set var="title" value="Magazine details"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <img id="primary" src="/img/discover-magazine-1.jpg" class="primary-image" alt="magazine"/>
                <div class="product-thumbnails" name>
                    <img src="/img/discover-magazine-1.jpg" class="thumb active" alt="magazine thumb" />
                    <img src="/img/discover-magazine-2.jpg" class="thumb" alt="magazine thumb" />
                    <img src="/img/discover-magazine-3.jpg" class="thumb" alt="magazine thumb" />
                    <img src="/img/discover-magazine-4.jpg" class="thumb" alt="magazine thumb" />
                    <img src="/img/discover-magazine-5.jpg" class="thumb" alt="magazine thumb" />
                </div>
        </div>
        <div class="col-md-7 col-md-offset-1">
            <h1>${periodical.name} Magazine</h1>
            <span>Published monthly</span>
            <div class="rating-stars big-stars">
                <m:ratingStars rating="${periodical.rating}" />
            </div>
            <p>${periodical.description}</p>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
