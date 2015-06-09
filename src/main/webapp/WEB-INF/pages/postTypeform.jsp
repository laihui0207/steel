<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="postTypeDetail.title"/></title>
    <meta name="menu" content="PostTypeMenu"/>
    <meta name="heading" content="<fmt:message key='postTypeDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="postTypeList.postType"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="postTypeDetail.heading"/></h2>
    <fmt:message key="postTypeDetail.message"/>
</div>

<div class="col-sm-6">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="postType" method="post" action="postTypeform" cssClass="well"
               id="postTypeForm" onsubmit="return validatePostType(this)">
        <form:hidden path="id"/>
        <spring:bind path="postType.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="postType.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name" maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
        </div>
        <spring:bind path="postType.comment">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="postType.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment" maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty postType.id}">
                <button type="submit" class="btn btn-danger" id="delete" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>

            <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<v:javascript formName="postType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['postTypeForm']).focus();
    });
</script>
