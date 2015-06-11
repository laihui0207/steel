<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="newsList.title"/></title>
    <meta name="menu" content="NewsMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="newsList.heading"/></h2>

<form method="get" action="${ctx}/newss" id="searchForm" class="form-inline">
<div id="search" class="text-right">
    <span class="col-sm-9">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
    </span>
    <button id="button.search" class="btn btn-default btn-sm" type="submit">
        <i class="icon-search"></i> <fmt:message key="button.search"/>
    </button>
</div>
</form>

<p><fmt:message key="newsList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/newsform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="newsList" class="table table-condensed table-striped table-hover" requestURI="" id="newsList" export="true" pagesize="25">
    <%--<display:column title="Thumbnail" sortable="false" media="html">
        <img src="${newsList.thumbnailUrl}"/>
    </display:column>--%>
    <display:column property="id" sortable="true" href="newsform" media="html"
        paramId="id" paramProperty="id" titleKey="news.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="news.id"/>
    <display:column property="title" sortable="true" titleKey="news.title"/>
    <display:column property="newsType.name" sortable="true" titleKey="news.newsType" />
    <display:column property="createTime" sortable="true" titleKey="news.createTime"/>
    <display:column property="expiredTime" sortable="true" titleKey="news.expiredTime"/>
    <display:column  sortable="false" titleKey="news.Action">
        <a href="/newss/view?id=${newsList.id}" target="_blank">View</a>
            </display:column>
    <%--<display:column sortProperty="ifAccessLimited" sortable="true" titleKey="news.ifAccessLimited">
        <input type="checkbox" disabled="disabled" <c:if test="${newsList.ifAccessLimited}">checked="checked"</c:if>/>
    </display:column>--%>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="newsList.news"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="newsList.newss"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="newsList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="newsList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="newsList.title"/>.pdf</display:setProperty>
</display:table>
