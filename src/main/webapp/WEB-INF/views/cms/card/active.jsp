<%--
  Created by IntelliJ IDEA.
  User: junhao
  Date: 2016/5/1
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>质保卡激活-乐优品科技</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="../inc/static.jsp"/>
    <script src="${pageContext.request.contextPath}/static/angularjs/controller/cmsController.js"></script>
</head>
<body ng-app="lypApp" ng-controller="cmsController" ng-init="pageInit()">
<div class="container">

    <div class="masthead">
        <h3 class="text-muted">乐优品科技</h3>
        <nav>
            <ul class="nav nav-justified">
                <li role="presentation"><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/cms/card/active">激活</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/query">查询</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/cms/card/agreement">协议</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/contactUs">联系我们</a></li>
            </ul>
        </nav>
    </div>

    <div class="row">
        <div class="col-md-10 col-md-offset-2" style="margin-top: 20px;">
            <form name="aform" ng-submit="activeCard(aform.$valid)" novalidate>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.certificateCode.$invalid && (aform.certificateCode.$touched || aform.$submitted) }">
                    <label for="certificateCode" class="col-sm-2 control-label text-right">激活码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="certificateCode" id="certificateCode" placeholder="激活码" ng-model="card.certificateCode"
                               ng-remote-validate="${pageContext.request.contextPath}/cms/validCardCode" ng-minlength="12" required >
                        <div ng-show="aform.certificateCode.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.certificateCode.$error.required ">请填写激活码.</div>
                            <div ng-show="aForm.certificateCode.$error.minlength">激活长度12位</div>
                            <div ng-show="aform.certificateCode.$invalid">激活码无效</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.telBrand.$invalid && (aform.telBrand.$touched || aform.$submitted) }">
                    <label for="telBrand" class="col-sm-2 control-label text-right">品牌</label>
                    <div class="col-sm-3">
                        <select class="form-control" name="telBrand" id="telBrand" ng-options=" mobile.code as mobile.name for mobile in mobiles" ng-model="card.telBrand" required
                                placeholder="--手机品牌--" ng-change="getTelModels(card.telBrand)">
                            <option value="">--手机品牌--</option>
                        </select>
                        <div ng-show="aform.telBrand.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.telBrand.$error.required ">请选择手机品牌.</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.telModel.$invalid && (aform.telModel.$touched || aform.$submitted) }">
                    <label for="telModel" class="col-sm-2 control-label text-right">型号</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="telModel" name="telModel" ng-options=" model.code as model.name for model in models" ng-model="card.telModel"
                                required placeholder="--手机型号--">
                            <option value="">--手机型号--</option>
                        </select>
                        <div ng-show="aform.telModel.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.telModel.$error.required ">请选择手机品牌.</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.telIMEI.$invalid && (aform.telIMEI.$touched || aform.$submitted) }">
                    <label for="telIMEI" class="col-sm-2 control-label text-right">IMEI码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="telIMEI" placeholder="IMEI码" required name="telIMEI"
                               ng-model="card.telIMEI" ng-pattern="/^(?!.*(.)\1{5})[0-9A-Fa-f]{15}([0-9A-Fa-f]{2})?$/">
                        <div ng-show="aform.telIMEI.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.telIMEI.$error.required ">请填写IMEI码.</div>
                            <div ng-show="aform.telIMEI.$invalid">激活码无效</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.customerName.$invalid && (aform.customerName.$touched || aform.$submitted) }">
                    <label for="customerName" class="col-sm-2 control-label text-right">顾客姓名</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="customerName" name="customerName" placeholder="顾客姓名" maxlength="50" required ng-model="card.customerName">
                        <div ng-show="aform.customerName.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.customerName.$error.required ">请填写顾客姓名.</div>
                            <div ng-show="aform.customerName.$error.maxlength">长度不能超过50</div>
                        </div>
                    </div>
                </div>
                <div  ng-class="{ 'form-group row':true, 'has-error': aform.mobileNumber.$invalid && (aform.mobileNumber.$touched || aform.$submitted) }">
                    <label for="mobileNumber" class="col-sm-2 control-label text-right">手机号码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="手机号码" required ng-model="card.mobileNumber"
                               ng-pattern="/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/">
                        <div ng-show="aform.mobileNumber.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.mobileNumber.$error.required ">请填写手机号码.</div>
                            <div ng-show="aform.mobileNumber.$invalid">手机号码无效</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.customerEmail.$invalid && (aform.customerEmail.$touched || aform.$submitted) }">
                    <label for="customerEmail" class="col-sm-2 control-label text-right">顾客邮箱</label>
                    <div class="col-sm-3">
                        <input type="email"  name="customerEmail" class="form-control" id="customerEmail" placeholder="可选项 顾客邮箱" ng-model="card.customerEmail">
                        <div ng-show="aform.customerEmail.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.customerEmail.$invalid">邮箱格式不正确</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.store.$invalid && (aform.store.$touched || aform.$submitted) }">
                    <label for="store" class="col-sm-2 control-label text-right">门店</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="store"  name="store"  required
                                ng-options="store.code as store.name for store in stores" ng-model="card.store" placeholder="--门店--" >
                            <option value="">--门店--</option>
                        </select>
                        <div ng-show="aform.store.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.store.$error.required ">请选择手机品牌.</div>
                        </div>
                    </div>
                </div>
                <div ng-class="{ 'form-group row':true, 'has-error': aform.storeTransactor.$invalid && (aform.storeTransactor.$touched || aform.$submitted) }">
                    <label for="storeTransactor" class="col-sm-2 control-label text-right">门店办理人</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="storeTransactor" id="storeTransactor" placeholder="可选项 门店办理人" maxlength="50" ng-model="card.storeTransactor">
                        <div ng-show="aform.customerEmail.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.storeTransactor.$error.maxlength">长度不能超过50</div>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" ng-model="agree" value="1"> 本人已阅读并同意<a href="${pageContext.request.contextPath}/cms/card/agreement" target="_blank"><strong>服务协议</strong></a>的所有条款。
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" ng-disabled="agree!=1 || aForm.$pending" >立即激活</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../inc/footer.jsp"/>
</div>
</body>

</html>
