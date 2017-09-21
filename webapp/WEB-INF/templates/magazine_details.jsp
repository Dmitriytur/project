<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/ratingstars.tld" prefix="m" %>
<c:set var="title" value="Magazine details"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <img id="primary" src="/img/magazines/${model.periodical.id}/1.jpg" class="primary-image" alt="magazine"/>
            <div class="product-thumbnails" name>
                <img src="/img/magazines/${model.periodical.id}/1.jpg" class="thumb active" alt="magazine thumb"/>
                <c:forEach var="i" begin="2" end="${model.periodical.images}">
                    <img src="/img/magazines/${model.periodical.id}/${i}.jpg" class="thumb" alt="magazine thumb"/>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-7 col-md-offset-1">
            <h1>${model.periodical.name} Magazine</h1>
            <p>Published
                <c:if test="${model.periodical.periodicity < 2}">
                    monthly
                </c:if>
                <c:if test="${model.periodical.periodicity >= 2}">
                    ${model.periodical.periodicity} times per month
                </c:if>
            </p>
            <div class="rating-stars big-stars">
                <m:ratingStars rating="${model.periodical.rating}"/>
            </div>
            <span class="">${reviewsAmount} reviews</span>
            <p class="prod-descr">${model.periodical.description}</p>
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
        <div class="col-md-6 ">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-xs-6 col-md-6 text-center">

                        <div class="rating-stars big-stars">
                            <h1>${model.periodical.rating}</h1>
                            <m:ratingStars rating="${model.periodical.rating}"/>
                        </div>
                        <div>
                            <span class="glyphicon glyphicon-user"></span> ${model.reviewsAmount} reviews
                        </div>
                    </div>
                    <div class="col-md-6">
        				<h4>Rating breakdown</h4>
        				<div class="pull-left">
        					<div class="pull-left" style="width:35px; line-height:1;">
        						<div style="height:9px; margin:5px 0;">5 <span class="glyphicon glyphicon-star"></span></div>
        					</div>
        					<div class="pull-left" style="width:180px;">
        						<div class="progress" style="height:9px; margin:8px 0;">
        						  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="5" aria-valuemin="0" aria-valuemax="5" style="width: ${model.reviewStatisticPercents[5]}%">
        							<span class="sr-only">80% Complete (danger)</span>
        						  </div>
        						</div>
        					</div>
        					<div class="pull-right" style="margin-left:10px;">${model.reviewStatisticAmounts[5]}</div>
        				</div>
        				<div class="pull-left">
        					<div class="pull-left" style="width:35px; line-height:1;">
        						<div style="height:9px; margin:5px 0;">4 <span class="glyphicon glyphicon-star"></span></div>
        					</div>
        					<div class="pull-left" style="width:180px;">
        						<div class="progress" style="height:9px; margin:8px 0;">
        						  <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="4" aria-valuemin="0" aria-valuemax="5" style="width: ${model.reviewStatisticPercents[5]}%">
        							<span class="sr-only">80% Complete (danger)</span>
        						  </div>
        						</div>
        					</div>
        					<div class="pull-right" style="margin-left:10px;">${model.reviewStatisticAmounts[4]}</div>
        				</div>
        				<div class="pull-left">
        					<div class="pull-left" style="width:35px; line-height:1;">
        						<div style="height:9px; margin:5px 0;">3 <span class="glyphicon glyphicon-star"></span></div>
        					</div>
        					<div class="pull-left" style="width:180px;">
        						<div class="progress" style="height:9px; margin:8px 0;">
        						  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="3" aria-valuemin="0" aria-valuemax="5" style="width: ${model.reviewStatisticPercents[3]}%">
        							<span class="sr-only">80% Complete (danger)</span>
        						  </div>
        						</div>
        					</div>
        					<div class="pull-right" style="margin-left:10px;">${model.reviewStatisticAmounts[3]}</div>
        				</div>
        				<div class="pull-left">
        					<div class="pull-left" style="width:35px; line-height:1;">
        						<div style="height:9px; margin:5px 0;">2 <span class="glyphicon glyphicon-star"></span></div>
        					</div>
        					<div class="pull-left" style="width:180px;">
        						<div class="progress" style="height:9px; margin:8px 0;">
        						  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="5" style="width: ${model.reviewStatisticPercents[2]}%">
        							<span class="sr-only">80% Complete (danger)</span>
        						  </div>
        						</div>
        					</div>
        					<div class="pull-right" style="margin-left:10px;">${model.reviewStatisticAmounts[2]}</div>
        				</div>
        				<div class="pull-left">
        					<div class="pull-left" style="width:35px; line-height:1;">
        						<div style="height:9px; margin:5px 0;">1 <span class="glyphicon glyphicon-star"></span></div>
        					</div>
        					<div class="pull-left" style="width:180px;">
        						<div class="progress" style="height:9px; margin:8px 0;">
        						  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="5" style="width: ${model.reviewStatisticPercents[1]}%">
        							<span class="sr-only">80% Complete (danger)</span>
        						  </div>
        						</div>
        					</div>
        					<div class="pull-right" style="margin-left:10px;">${model.reviewStatisticAmounts[1]}</div>
        				</div>
        			</div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <c:forEach items="${model.reviews}" var="item">
            <div class="panel panel-default">

            <div class="panel-heading">${model.userMap[item.userId].userName}<span class="pull-right">${item.creationDate}</span></div>
            <div class="panel-body">
                <div class="rating-stars">
                    <m:ratingStars rating="${item.score}"/>
                </div>
                <h3>${item.message}</h3>
            </div></div>

        </c:forEach>
        <c:if test="${user != null}">
        <div class="well well-sm">
            <div class="text-right">
                <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">Leave a Review</a>
            </div>

            <div class="row" id="post-review-box" style="display:none;">
                <div class="col-md-12">
                    <form accept-charset="UTF-8" action="" method="post">
                        <input id="ratings-hidden" name="rating" type="hidden">
                        <textarea class="form-control animated" cols="50" id="new-review" name="comment" placeholder="Enter your review here..." rows="5"></textarea>

                        <div class="text-right">
                            <div class="stars starrr" data-rating="0"></div>
                            <a class="btn btn-danger btn-sm" href="#" id="close-review-box" style="display:none; margin-right: 10px;">
                            <span class="glyphicon glyphicon-remove"></span>Cancel</a>
                            <button class="btn btn-success btn-lg" type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div
	</div>
    </c:if>
    </div>

</div>



</div>



<%@ include file="/WEB-INF/fragments/bottom.jspf" %>
<script src="/js/open_review.js" charset="utf-8"></script>
