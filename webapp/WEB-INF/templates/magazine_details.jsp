<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/ratingstars.tld" prefix="m" %>
<c:set var="title" value="Magazine details"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <img id="primary" src="/img/magazines/${periodical.id}/1.jpg" class="primary-image" alt="magazine"/>
            <div class="product-thumbnails" name>
                <img src="/img/magazines/${periodical.id}/1.jpg" class="thumb active" alt="magazine thumb"/>
                <c:forEach var="i" begin="2" end="${periodical.images}">
                    <img src="/img/magazines/${periodical.id}/${i}.jpg" class="thumb" alt="magazine thumb"/>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-7 col-md-offset-1">
            <h1>${periodical.name} Magazine</h1>
            <p>Published
                <c:if test="${periodical.periodicity < 2}">
                    monthly
                </c:if>
                <c:if test="${periodical.periodicity >= 2}">
                    ${periodical.periodicity} times per month
                </c:if>
            </p>
            <div class="rating-stars big-stars">
                <m:ratingStars rating="${periodical.rating}"/>
            </div>
            <span class="">${reviewsAmount} reviews</span>
            <p class="prod-descr">${periodical.description}</p>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Subscription</h3>
                </div>
                <div class="panel-body">
                    Panel content
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">Similar magazines</div>
            <div class="panel-body">
                Panel content
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-xs-6 col-md-6 text-center">

                        <div class="rating-stars big-stars">
                            <h1>${periodical.rating}</h1>
                            <m:ratingStars rating="${periodical.rating}"/>
                        </div>
                        <div>
                            <span class="glyphicon glyphicon-user"></span> ${reviewsAmount} reviews
                        </div>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <div class="row rating-desc">
                            <div class="col-xs-3 col-md-3 text-right">
                                <span class="glyphicon glyphicon-star"></span>5
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress progress-striped">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                        <span class="sr-only">80%</span>
                                    </div>
                                </div>
                            </div>
                            <!-- end 5 -->
                            <div class="col-xs-3 col-md-3 text-right">
                                <span class="glyphicon glyphicon-star"></span>4
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60%</span>
                                    </div>
                                </div>
                            </div>
                            <!-- end 4 -->
                            <div class="col-xs-3 col-md-3 text-right">
                                <span class="glyphicon glyphicon-star"></span>3
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                        <span class="sr-only">40%</span>
                                    </div>
                                </div>
                            </div>
                            <!-- end 3 -->
                            <div class="col-xs-3 col-md-3 text-right">
                                <span class="glyphicon glyphicon-star"></span>2
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                        <span class="sr-only">20%</span>
                                    </div>
                                </div>
                            </div>
                            <!-- end 2 -->
                            <div class="col-xs-3 col-md-3 text-right">
                                <span class="glyphicon glyphicon-star"></span>1
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 15%">
                                        <span class="sr-only">15%</span>
                                    </div>
                                </div>
                            </div>
                            <!-- end 1 -->
                        </div>
                        <!-- end row -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${reviews}" var="item">
            <div class="rating-stars">
                <m:ratingStars rating="${item.score}"/>
            </div>
            <h3>${item.message}</h3>

        </c:forEach>
    </div>

</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
