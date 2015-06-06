<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="collapse navbar-collapse" id="navbar">
<ul class="nav navbar-nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="Home"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    <!--NewsType-START-->
    <menu:displayMenu name="NewsTypeMenu"/>
    <!--NewsType-END-->
    
    
    
    
    <!--Group-START-->
    <menu:displayMenu name="GroupMenu"/>
    <!--Group-END-->
    
    <!--News-START-->
    <menu:displayMenu name="NewsMenu"/>
    <!--News-END-->
    <!--Post-START-->
    <menu:displayMenu name="PostMenu"/>
    <!--Post-END-->
    <!--PostType-START-->
    <menu:displayMenu name="PostTypeMenu"/>
    <!--PostType-END-->
    <!--Reply-START-->
    <menu:displayMenu name="ReplyMenu"/>
    <!--Reply-END-->
</ul>
</div>
</menu:useMenuDisplayer>
