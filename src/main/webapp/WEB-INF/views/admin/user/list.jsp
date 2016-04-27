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
                                        <div class="col-sm-6">
                                            <div class="dataTables_length" id="dataTables-example_length">
                                                <label>每页显示
                                                    <select name="pageSize" aria-controls="dataTables-example" class="form-control input-sm">
                                                        <option value="10">10</option>
                                                        <option value="25">25</option>
                                                        <option value="50">50</option>
                                                        <option value="100">100</option>
                                                    </select>
                                                    条
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div id="dataTables-example_filter" class="dataTables_filter"><label>Search:<input
                                                    type="search" class="form-control input-sm" placeholder=""
                                                    aria-controls="dataTables-example"></label></div>
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
                                                <tr class="gradeA odd" role="row" ng-repeat="u in users">
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
                                            <div class="dataTables_info" id="dataTables-example_info" role="status"
                                                 aria-live="polite">显示 1 至 10 共 57 条记录
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="dataTables_paginate paging_simple_numbers"
                                                 id="dataTables-example_paginate">
                                                <ul class="pagination">
                                                    <li class="paginate_button previous disabled"
                                                        aria-controls="dataTables-example" tabindex="0"
                                                        id="dataTables-example_previous"><a href="#">上一页</a></li>
                                                    <li class="paginate_button active"
                                                        aria-controls="dataTables-example" tabindex="0"><a
                                                            href="#">1</a></li>
                                                    <li class="paginate_button " aria-controls="dataTables-example"
                                                        tabindex="0"><a href="#">2</a></li>
                                                    <li class="paginate_button " aria-controls="dataTables-example"
                                                        tabindex="0"><a href="#">3</a></li>
                                                    <li class="paginate_button " aria-controls="dataTables-example"
                                                        tabindex="0"><a href="#">4</a></li>
                                                    <li class="paginate_button " aria-controls="dataTables-example"
                                                        tabindex="0"><a href="#">5</a></li>
                                                    <li class="paginate_button " aria-controls="dataTables-example"
                                                        tabindex="0"><a href="#">6</a></li>
                                                    <li class="paginate_button next" aria-controls="dataTables-example"
                                                        tabindex="0" id="dataTables-example_next"><a href="#">下一页</a>
                                                    </li>
                                                </ul>
                                            </div>
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
