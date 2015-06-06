<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postTypeList.title"/></title>
    <meta name="menu" content="PostTypeMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="postTypeList.heading"/></h2>

<form method="get" action="${ctx}/postTypes" id="searchForm" class="form-inline">
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

<p><fmt:message key="postTypeList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/postTypeform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="postTypeList" class="table table-condensed table-striped table-hover" requestURI="" id="postTypeList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="postTypeform" media="html"
        paramId="id" paramProperty="id" titleKey="postType.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="postType.id"/>
    <display:column property="comment" sortable="true" titleKey="postType.comment"/>
    <display:column property="createTime" sortable="true" titleKey="postType.createTime"/>
    <display:column property="name" sortable="true" titleKey="postType.name"/>
    <display:column property="updateTime" sortable="true" titleKey="postType.updateTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="postTypeList.postType"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="postTypeList.postTypes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="postTypeList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="postTypeList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="postTypeList.title"/>.pdf</display:setProperty>
</display:table>
