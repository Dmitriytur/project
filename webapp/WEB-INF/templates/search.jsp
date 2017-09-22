<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Search"/>
<%@ include file="/WEB-INF/fragments/top.jspf" %>

<div class="container">
    <div class="row">
        <div class="panel panel-default">
        <div class="panel-heading">Search</div>
        <div class="panel-body">
            <div class="form-inline">
                <div class="form-group">
                    <label class="filter-col" style="margin-right:0;" for="pref-search">Search:</label>
                    <input id="name" type="text" class="form-control input-sm"   value="${param.name}">
                </div>

                <div class="form-group">
                    <label class="filter-col" style="margin-right:0;" for="pref-orderby">Category:</label>
                    <select id="category" class="form-control" >
                            <option value="">All</option>
                            <c:forEach items="${categories}" var="item">
                                <option value="${item}">${item}</option>
                            </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="filter-col" style="margin-right:0;" for="pref-orderby">Order by:</label>
                    <select id="sortBy"  class="form-control">
                        <option value="summary_rating">Rating</option>
                        <option value="price">Price</option>
                        <option value="name">Name</option>
                    </select>
                </div>

                <div class="form-group">
                    <select id="order"  class="form-control" >
                        <option value="desc">Descending</option>
                        <option value="">Ascending</option>

                    </select>
                </div>

                <div class="form-group">
                    <label class="filter-col" style="margin-right:0;" for="pref-perpage">Items per page:</label>
                    <select id="limit"  class="form-control">
                        <option value="4">4</option>
                        <option value="8" selected>8</option>
                        <option value="12">12</option>
                        <option value="16">16</option>
                        <option value="20">20</option>
                    </select>
                </div>


                <button  id="searchBtn" type="button" class="btn btn-success" >Search</button>
            </div>
        </div></div>
    </div>
    <div id="searchResult">

    </div>
</div>

<%@ include file="/WEB-INF/fragments/bottom.jspf" %>

<script src="/js/search.js" charset="utf-8"></script>
