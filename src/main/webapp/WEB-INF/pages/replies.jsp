<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="replyList.title"/></title>
    <meta name="menu" content="ReplyMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="replyList.heading"/></h2>

<form method="get" action="${ctx}/replies" id="searchForm" class="form-inline">
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

<p><fmt:message key="replyList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/replyform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="replyList" class="table table-condensed table-striped table-hover" requestURI="" id="replyList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="replyform" media="html"
        paramId="id" paramProperty="id" titleKey="reply.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="reply.id"/>
    <display:column property="content" sortable="true" titleKey="reply.content"/>
    <display:column property="replyTime" sortable="true" titleKey="reply.replyTime"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="replyList.reply"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="replyList.replies"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="replyList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="replyList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="replyList.title"/>.pdf</display:setProperty>
</display:table>
