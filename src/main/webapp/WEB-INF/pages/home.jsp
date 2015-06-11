<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
    <script type="text/javascript" src="/scripts/editor/plugins/ckplayer/ckplayer.js" charset="utf-8"></script>
</head>
<body class="home">

<h2><fmt:message key="home.heading"/></h2>
<p><fmt:message key="home.message"/></p>

<ul class="glassList">
    <li>
        <a href="<c:url value='/userform'/>"><fmt:message key="menu.user"/></a>
    </li>
    <li>
        <a href="<c:url value='/fileupload'/>"><fmt:message key="menu.selectFile"/></a>
    </li>
</ul>
<%--<div id="a1"></div>
<script type="text/javascript">
    var flashvars={
        p:1,
        e:1
    };
    var video=['http://localhost:8084/attached/media/20150610/20150610_700.mp4->video/mp4'];
    var support=['all'];
    CKobject.embedHTML5('a1','ckplayer_a1',600,400,video,flashvars,support);
</script>--%>
</body>

