<%--
  Created by IntelliJ IDEA.
  User: junhao
  Date: 2016/5/1
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>关于我们-乐优品科技</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="../inc/static.jsp"/>
    <script src="${pageContext.request.contextPath}/static/angularjs/controller/cmsController.js"></script>
</head>
<body ng-app="lypApp" ng-controller="cmsController" >
<div class="container">
    <div class="masthead">
        <h3 class="text-muted">乐优品科技</h3>
        <nav>
            <ul class="nav nav-justified">
                <li role="presentation"><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/active">激活</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/query">查询</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/agreement">协议</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/cms/card/contactUs">联系我们</a></li>
            </ul>
        </nav>
    </div>
    <div class="row">

    </div>
    <jsp:include page="../inc/footer.jsp"/>
</div>
</body>

</html>
