<%--
  Created by IntelliJ IDEA.
  User: junhao
  Date: 2016/4/24
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>乐优品科技</title>
    <meta name="keyword" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="inc/static.jsp"/>
</head>
    <body>
    <div class="container">
        <div class="masthead">
            <h3 class="text-muted">乐优品科技</h3>
            <nav>
                <ul class="nav nav-justified">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">首页</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/active">激活</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/query">查询</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/agreement">协议</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/contactUs">联系我们</a></li>
                </ul>
            </nav>
        </div>

        <div class="jumbotron">
            <h2><strong>赤膜王防爆膜屏幕质保协议</strong></h2>
            <p class="lead">购买手机屏安保障服务时，您的手机屏幕应完好无损。
                您在购买时应提供上表中所要求的信息，提供显示手机序列号或IMEI号的屏幕完好照片，并缴纳费用。
                您所提供的信息资料 及照片应真实有效，否则恕我们不能为您提供服务，且不能退还您已缴纳的购买本手机屏安保障服务的费用。
                本协议购买后的15天为等待期。本协议自第16天生效，有效期为1年...</p>
            <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/cms/card/active" role="button">立即激活</a></p>
        </div>

        <div class="row marketing">
            <div class="col-lg-6">
                <h4>公司简介</h4>
                <p>公司成立于2010年......</p>

                <h4>产品中心</h4>
                <p>主要经营各种品牌手机及配件</p>

                <h4>人才招聘</h4>
                <p>欢迎投递简历至hr@lyp360.com</p>
            </div>

            <div class="col-lg-6">
                <h4>行业资讯</h4>
                <p>2016年5月 手机价格对比统计</p>

                <h4>屏保协议</h4>
                <p>本协议购买后的15天为等待期。本协议自第16天生效，有效期为1年</p>

                <h4>联系我们</h4>
                <p>河南省 郑州市 二七区 xxx大厦 3层 305</p>
            </div>
        </div>
        <jsp:include page="inc/footer.jsp"/>
    </div>
</body>

</html>

