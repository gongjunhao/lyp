<%--
  Created by IntelliJ IDEA.
  User: junhao
  Date: 2016/4/26
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>乐优品科技后台管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="../static.jsp"/>
    <!-- lypApp JavaScript -->
    <script src="${pageContext.request.contextPath}/static/angularjs/controller/userController.js"></script>
</head>

<body ng-app="lypApp" ng-controller="userController">

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="../navigation.jsp"/>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading clearfix">
                            <h4 class="panel-title pull-left" style="padding-top: 7.5px;">用户列表</h4>
                            <div class="btn-group pull-right">
                                <a href="#" class="btn btn-success btn-sm" ng-click="addUser()">新增用户</a>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <div id="dataTables-example_wrapper"
                                     class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <div id="dataTables-0" class="dataTables_filter">
                                                <label>
                                                    登录名:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="user.loginName" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-1" class="dataTables_filter">
                                                <label>
                                                    昵称:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="user.nickName" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-2" class="dataTables_filter">
                                                <label>
                                                    状态:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="user.status" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-3" class="dataTables_filter">
                                                <button type="button" class="btn btn-info" ng-click="list()">查询</button>
                                                <button type="button" class="btn btn-info" ng-click="user={}">清空</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                                   id="dataTables-example" role="grid"
                                                   aria-describedby="dataTables-example_info">
                                                <thead>
                                                <tr role="row">
                                                    <th class="{{head.class}}" tabindex={{$index}}" ng-repeat="head in tableHead"
                                                        aria-sort="{{head.code}}" ng-click="sortColumn(head)" style="{{head.style}}">{{head.name}}
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr class="gradeA odd" role="row" ng-repeat="u in pager.list">
                                                    <td class="sorting_1">{{u.id}}</td>
                                                    <td>{{u.loginName}}</td>
                                                    <td>{{u.nickName}}</td>
                                                    <td class="center">{{u.status}}</td>
                                                    <td class="center">{{u.createTime | longToDate | date:'yyyy-MM-dd HH:mm'}}</td>
                                                    <td class="center">
                                                        <button type="button" class="btn btn-outline btn-primary btn-xs" ng-click="upate(u)">编辑</button>
                                                        <button type="button" class="btn btn-outline btn-danger btn-xs" ng-click="delete(u)">删除</button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="dataTables_length pagination" id="dataTables-example_length">
                                                <label>每页显示
                                                    <select ng-model="pager.pageSize" ng-change="list()" class="form-control input-sm"  ng-options="m for m in [10, 25, 50, 100]"></select>
                                                    条
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <uib-pagination total-items="pager.total" ng-model="pager.pageNum"  ng-change="list()"  items-per-page="pager.pageSize" max-size="10" class="pagination-sm"
                                                            boundary-links="true" force-ellipses="true" rotate="false" first-text="首页" previous-text="上一页" next-text="下一页" last-text="尾页" ></uib-pagination>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>

    <div class='notifications bottom-right'></div>

    <div id="dialogs">
        <script type="text/ng-template" id="updateDialog.html">
            <div class="modal-header">
                <h3 class="modal-title">更新用户信息</h3>
            </div>
            <form name="userForm" ng-submit="save(userForm.$valid)" novalidate>
                <div class="modal-body">
                    <div class="form-group">
                        <label>登录名:</label>
                        <input type="text" ng-model="user.loginName" class="form-control" ng-disabled="user.id != null">
                    </div>
                    <div class="form-group" ng-class="{ 'has-error' : userForm.nickName.$invalid && !userForm.nickName.$pristine }">
                        <label>昵称:</label>
                        <input type="text" name="nickName" class="form-control" ng-model="user.nickName" required>
                        <p ng-show="userForm.nickName.$invalid && !userForm.nickName.$pristine" class="help-block">昵称必填.</p>
                    </div>
                    <div class="form-group"  ng-class="{ 'has-error' : userForm.password.$invalid && !userForm.password.$pristine }"  ng-if="user.id == null">
                        <label>密码:</label>
                        <input type="password" ng-model="user.password" class="form-control" ng-disabled="user.id != null" required minlength="6">
                        <p ng-show="userForm.password.$invalid && !userForm.password.$pristine" class="help-block">密码必填.</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit" >保存</button>
                    <button class="btn btn-warning" type="button" ng-click="cancel()">关闭</button>
                </div>
            </form>
        </script>

        <script type="text/ng-template" id="deleteConfirm.html">
            <div class="modal-header">
                <h3 class="modal-title">删除提示</h3>
            </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-3">您确认删除用户：{{user.loginName}}吗？</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" ng-click="updateDel()">确认</button>
                    <button class="btn btn-warning" type="button" ng-click="cancel()">取消</button>
                </div>
            </form>
        </script>
    </div>
</div>
<!-- /#wrapper -->

</body>

</html>
