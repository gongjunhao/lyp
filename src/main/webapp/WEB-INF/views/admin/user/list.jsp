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
                        <div class="panel-heading">
                            用户列表
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
                                                    <th class="sorting_asc" tabindex="0"
                                                        aria-controls="dataTables-example" rowspan="1" colspan="1"
                                                        aria-sort="ascending"
                                                        aria-label="Rendering engine: activate to sort column descending"
                                                        style="width: 40px;">ID
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="dataTables-example"
                                                        rowspan="1" colspan="1"
                                                        aria-label="Browser: activate to sort column ascending"
                                                        style="width: 100px;">登录名
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="dataTables-example"
                                                        rowspan="1" colspan="1"
                                                        aria-label="Platform(s): activate to sort column ascending"
                                                        style="width: 100px;">昵称
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="dataTables-example"
                                                        rowspan="1" colspan="1"
                                                        aria-label="Engine version: activate to sort column ascending"
                                                        style="width: 80px;">状态
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="dataTables-example"
                                                        rowspan="1" colspan="1"
                                                        aria-label="CSS grade: activate to sort column ascending"
                                                        style="width: 100px;">注册时间
                                                    </th>
                                                    <th rowspan="1" colspan="1" style="width: 100px;">
                                                        操作
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr class="gradeA odd" role="row" ng-repeat="u in pager.list">
                                                    <td class="sorting_1">{{u.id}}</td>
                                                    <td>{{u.loginName}}</td>
                                                    <td>{{u.nickName}}</td>
                                                    <td class="center">{{u.status}}</td>
                                                    <td class="center">{{u.createTime}}</td>
                                                    <td class="center">编辑删除</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="dataTables_length" id="dataTables-example_length">
                                                <label>每页显示
                                                    <select ng-model="pager.pageSize" ng-change="list()" class="form-control input-sm"  ng-options="m for m in [10, 25, 50, 100]"></select>
                                                    条
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <pagination total-items="pager.total" ng-model="pager.pageNum" ng-change="list()" items-per-page="pager.pageSize" max-size="10" class="pagination-sm"
                                                        boundary-links="true" rotate="false" first-text="首页" previous-text="上一页" next-text="下一页" last-text="尾页" ></pagination>
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

</div>
<!-- /#wrapper -->

</body>

</html>
