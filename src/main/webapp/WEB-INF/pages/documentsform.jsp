<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="documentsDetail.title"/></title>
    <meta name="menu" content="DocumentsMenu"/>
    <meta name="heading" content="<fmt:message key='documentsDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="documentsList.documents"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="documentsDetail.heading"/></h2>
    <fmt:message key="documentsDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="documents" method="post" action="documentsform" cssClass="well"
           id="documentsForm" onsubmit="return validateDocuments(this)">
<form:hidden path="id"/>
    <spring:bind path="documents.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment"  maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
    </div>
    <spring:bind path="documents.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="content"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>
    <spring:bind path="documents.createTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.createTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createTime" id="createTime"  maxlength="255"/>
        <form:errors path="createTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="creater" items="createrList" itemLabel="label" itemValue="value"/>
    <spring:bind path="documents.docSize">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.docSize" styleClass="control-label"/>
        <form:input cssClass="form-control" path="docSize" id="docSize"  maxlength="255"/>
        <form:errors path="docSize" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="docType" items="docTypeList" itemLabel="label" itemValue="value"/>
    <spring:bind path="documents.introduction">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.introduction" styleClass="control-label"/>
        <form:input cssClass="form-control" path="introduction" id="introduction"  maxlength="255"/>
        <form:errors path="introduction" cssClass="help-block"/>
    </div>
    <spring:bind path="documents.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="documents.storePath">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.storePath" styleClass="control-label"/>
        <form:input cssClass="form-control" path="storePath" id="storePath"  maxlength="255"/>
        <form:errors path="storePath" cssClass="help-block"/>
    </div>
    <spring:bind path="documents.updateTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.updateTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updateTime" id="updateTime"  maxlength="255"/>
        <form:errors path="updateTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="updater" items="updaterList" itemLabel="label" itemValue="value"/>
    <spring:bind path="documents.viewLimit">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="documents.viewLimit" styleClass="control-label"/>
        <form:checkbox path="viewLimit" id="viewLimit" cssClass="checkbox"/>
        <form:errors path="viewLimit" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty documents.id}">
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

<v:javascript formName="documents" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['documentsForm']).focus();
    });
</script>
