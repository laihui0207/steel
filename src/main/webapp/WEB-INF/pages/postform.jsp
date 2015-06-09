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

<%--<div class="col-sm-3">
    <h2><fmt:message key="postDetail.heading"/></h2>
    <fmt:message key="postDetail.message"/>
</div>--%>

<div class="col-sm-12">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="post" method="post" action="postform" cssClass="well"
           id="postForm" onsubmit="return validatePost(this)">
<form:hidden path="id"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="postType.id" items="${postTypeList}" itemLabel="name" itemValue="id"/>
    <spring:bind path="post.title">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="post.title" styleClass="control-label"/>
    <form:input cssClass="form-control" path="title" id="title"  maxlength="255"/>
    <form:errors path="title" cssClass="help-block"/>
    </div>
    <spring:bind path="post.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.content" styleClass="control-label"/>
        <form:textarea cssClass="form-control" path="content" id="contentinput"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>
    <spring:bind path="post.ifAccessAllReply">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.ifAccessAllReply" styleClass="control-label"/>
        <form:checkbox path="ifAccessAllReply" id="ifAccessAllReply" cssClass="checkbox"/>
        <form:errors path="ifAccessAllReply" cssClass="help-block"/>
    </div>
    <spring:bind path="post.thumbnailUrl">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.thumbnailUrl" styleClass="control-label"/>
        <form:input cssClass="form-control" path="thumbnailUrl" id="thumbnailUrl"  maxlength="255"/>
        <form:errors path="thumbnailUrl" cssClass="help-block"/>
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
<script type="text/javascript" src="<c:url value='/scripts/editor/kindeditor-all-min.js'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['postForm']).focus();
        KindEditor.ready(function(K) {
            window.editor = K.create('#contentinput', {
                uploadJson : "<c:url value='/editeruploadattachement'/>",
                fileManagerJson : "<c:url value='/editeruploadattachement'/>",
                allowFileManager : true,filterMode: false
            });
        });
    });
</script>
