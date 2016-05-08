<%--
  Created by IntelliJ IDEA.
  User: junhao
  Date: 2016/4/30
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>乐优品科技后台管理-生成授权码</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="../static.jsp"/>
    <script src="${pageContext.request.contextPath}/static/angularjs/controller/cardController.js"></script>
</head>

<body ng-app="lypApp" ng-controller="cardController">

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="../navigation.jsp"/>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <form name="cardForm" class="form-inline" ng-submit="produce(cardForm.$valid)" novalidate ng-show="!hasProduce">
                    <div ng-class="{ 'form-group':true, 'has-error': cardForm.cardNum.$invalid && cardForm.$submitted }">
                        <label for="exampleInputAmount">请输入生成规则</label>
                        <input type="text" class="form-control" style="width: 250px" id="exampleInputAmount" name="cardNum" ng-model="cardNum" placeholder="20160508{00001-00100}" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail2">备注</label>
                        <input type="text" class="form-control" id="exampleInputEmail2" name="mark" ng-model="mark" placeholder="备注信息" maxlength="200">
                    </div>
                    <button type="submit" class="btn btn-primary">立即生成</button>
                </form>
                <div class=".col-md-6 .col-md-offset-3" ng-show="hasProduce">
                    <p class="text-center">
                        已成功生成<strong>{{cards.length}}</strong>个授权码
                        <button type="button" class="btn btn-primary btn-bg" ng-click="doPrint()">打印</button>&nbsp;
                        <button type="button" class="btn btn-primary btn-bg" ng-click="saveCards()">立即存库</button>
                    </p>
                </div>
            </div>
            <!--startprint-->
            <div class="col-sm-6 col-md-3" ng-repeat="card in cards">
                <div class="thumbnail">
                    <img src="${pageContext.request.contextPath}/static/lyp/images/card.jpg" alt="质保卡">
                    <div class="caption">
                        <h4>乐优品-质保卡</h4>
                        <p>激活码：<strong>{{card.code}}</strong></p>
                        <p>生成日期：<strong>{{card.createTime  | longToDate | date:'yyyy-MM-dd HH:mm'}}</strong></p>
                        <footer ng-if="card.mark != null">备注：<strong>{{card.mark}}</strong></footer>
                    </div>
                </div>
            </div>
            <!--endprint-->
            <div class='notifications bottom-right'></div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>

</div>
<!-- /#wrapper -->

</body>

</html>