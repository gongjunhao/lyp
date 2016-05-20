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
    <title>质保卡激活查询-乐优品科技</title>
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
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/cms/card/query">查询</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/agreement">协议</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/contactUs">联系我们</a></li>
            </ul>
        </nav>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
            <form name="aform" ng-submit="query(aform.$valid)" novalidate>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.certificateCode.$invalid && (aform.certificateCode.$touched || aform.$submitted) }">
                    <label for="certificateCode" class="col-sm-2 control-label text-right">激活码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="certificateCode" id="certificateCode" placeholder="激活码" ng-model="card.certificateCode"
                               ng-remote-validate="${pageContext.request.contextPath}/cms/validApplyCode" ng-minlength="5" >
                        <div ng-show="aform.certificateCode.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.certificateCode.$invalid">激活码无效</div>
                        </div>
                    </div>
                </div>
                <div  ng-class="{ 'form-group row':true, 'has-error': aform.mobileNumber.$invalid && (aform.mobileNumber.$touched || aform.$submitted) }">
                    <label for="mobileNumber" class="col-sm-2 control-label text-right">手机号码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="手机号码" ng-model="card.mobileNumber"
                               ng-pattern="/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/">
                        <div ng-show="aform.mobileNumber.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.mobileNumber.$invalid">手机号码无效</div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" ng-disabled="aForm.$pending" >查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
            <form class="form-horizontal" ng-repeat="insurance in insurances">
                <div class="form-group">
                    <label class="col-sm-2 control-label">激活码</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.certificateCode}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机品牌</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{dictMaps[insurance.insurance.telBrand]}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">型号</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{dictMaps[insurance.insurance.telModel]}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">IMEI码</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.telIMEI}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.customerName}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号码</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.mobileNumber}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.customerEmail}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">门店</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{dictMaps[insurance.insurance.store]}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">门店办理人</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.storeTransactor}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">激活时间</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{insurance.insurance.createTime | longToDate | date:'yyyy-MM-dd HH:mm'}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态</label>
                    <div class="col-sm-3">
                        <p class="form-control-static">{{dictMaps[insurance.insurance.status]}}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">照片</label>
                    <div class="col-sm-3">
                        <div ng-repeat="file in insurance.attachs">
                            <img ng-src="{{file.savePath}}" class="img-rounded">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="../inc/footer.jsp"/>
</div>
</body>

</html>
