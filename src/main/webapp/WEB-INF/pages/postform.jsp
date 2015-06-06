<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postDetail.title"/></title>
    <meta name="menu" content="PostMenu"/>
    <meta name="heading" content="<fmt:message key='postDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="postList.post"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="postDetail.heading"/></h2>
    <fmt:message key="postDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="post" method="post" action="postform" cssClass="well"
           id="postForm" onsubmit="return validatePost(this)">
<form:hidden path="id"/>
    <spring:bind path="post.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="content"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>
    <spring:bind path="post.createTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.createTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createTime" id="createTime"  maxlength="255"/>
        <form:errors path="createTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="creater" items="createrList" itemLabel="label" itemValue="value"/>
    <spring:bind path="post.ifAccessAllReply">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.ifAccessAllReply" styleClass="control-label"/>
        <form:checkbox path="ifAccessAllReply" id="ifAccessAllReply" cssClass="checkbox"/>
        <form:errors path="ifAccessAllReply" cssClass="help-block"/>
    </div>
    <spring:bind path="post.lastReplyTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.lastReplyTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="lastReplyTime" id="lastReplyTime"  maxlength="255"/>
        <form:errors path="lastReplyTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="lastReplyUser" items="lastReplyUserList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="postType" items="postTypeList" itemLabel="label" itemValue="value"/>
    <spring:bind path="post.thumbnailUrl">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.thumbnailUrl" styleClass="control-label"/>
        <form:input cssClass="form-control" path="thumbnailUrl" id="thumbnailUrl"  maxlength="255"/>
        <form:errors path="thumbnailUrl" cssClass="help-block"/>
    </div>
    <spring:bind path="post.title">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.title" styleClass="control-label"/>
        <form:input cssClass="form-control" path="title" id="title"  maxlength="255"/>
        <form:errors path="title" cssClass="help-block"/>
    </div>
    <spring:bind path="post.updateTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.updateTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updateTime" id="updateTime"  maxlength="255"/>
        <form:errors path="updateTime" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty post.id}">
            <button type="submit" class="btn btn-danger" id="delete" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="post" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['postForm']).focus();
    });
</script>
