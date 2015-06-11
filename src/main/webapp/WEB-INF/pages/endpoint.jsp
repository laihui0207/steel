<%--
  Created by IntelliJ IDEA.
  User: sunlaihui
  Date: 6/12/15
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title></title>
</head>
<body>
<div class="container">
<div class="container">
<h1>Spring MVC 3.1 Demo Endpoints</h1>
<c:forEach items="${handlerMethods}" var="entry">
  <div>
  <hr>
  <p><strong>${entry.value}</strong></p>
  </div>
  <div class="span-3 colborder">
  <p>
  <span class="alt">Patterns:</span><br>
  <c:if test="${not empty entry.key.patternsCondition.patterns}">
    ${entry.key.patternsCondition.patterns}
  </c:if>
  </p>
  </div>
</body>
