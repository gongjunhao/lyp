<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/25
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>乐优品科技后台管理-字典管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="../static.jsp"/>
    <script src="${pageContext.request.contextPath}/static/angularjs/controller/dictionaryController.js"></script>
    <style>
        .left-side {
            margin-left: 0px;
            padding-top: 10px;
            float: none;
            display: table-cell;
        }

        .right-side {
            float: none;
            display: table-cell;
        }
    </style>
</head>

<body ng-app="lypApp" ng-controller="dictionaryController" ng-init="treeInit()">

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="../navigation.jsp"/>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <!-- 左侧ztree -->
                <div class="col-md-2 left-side">
                    <div class="zTreeDemoBackground left">
                        <ul id="dictTree" class="ztree"></ul>
                    </div>
                </div>
                <!-- 右侧table -->
                <div class=".col-md-9 .col-md-offset-3 right-side">
                    <div class="panel panel-default">
                        <div class="panel-heading clearfix">
                            <h4 class="panel-title pull-left" >字典列表</h4>
                            <div class="btn-group pull-right">
                                <a href="#" class="btn btn-success btn-sm" ng-click="addDictionary()">新增字典</a>
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
                                                    父级ID:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="dictionary.parentId" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-2" class="dataTables_filter">
                                                <label>
                                                    名称:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="dictionary.name" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-1" class="dataTables_filter">
                                                <label>
                                                    编码:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="dictionary.code" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-3" class="dataTables_filter">
                                                <button type="button" class="btn btn-info" ng-click="list()">查询</button>
                                                <button type="button" class="btn btn-info" ng-click="dictionary={}">清空</button>
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
                                                    <td>{{u.parentId}}</td>
                                                    <td>{{u.name}}</td>
                                                    <td class="center">{{u.code}}</td>
                                                    <td class="center">{{u.status}}</td>
                                                    <td class="center">{{u.createTime | longToDate | date:'yyyy-MM-dd HH:mm'}}</td>
                                                    <td class="center">
                                                        <button type="button" ng-disabled="{{u.id == 1}}" class="btn btn-outline btn-primary btn-xs" ng-click="upate(u)">修改</button>
                                                        <button type="button" ng-disabled="{{u.id == 1}}" class="btn btn-outline btn-danger btn-xs" ng-click="delete(u)">删除</button>
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
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>

    <div class='notifications bottom-right'></div>

    <div id="dialogs">
        <script type="text/ng-template" id="updateDialog.html">
            <div class="modal-header">
                <h3 class="modal-title">更新字典</h3>
            </div>
            <form name="aform" ng-submit="save(aform.$valid)" novalidate>
                <div class="modal-body">
                    <div ng-class="{ 'form-group':true,  }">
                        <label>父级:</label>
                        <input type="text" name="name" class="form-control" ng-model="parentNode.name" ng-readonly="true">
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': aform.name.$invalid && (aform.name.$touched || aform.$submitted) }">
                        <label>名称:</label>
                        <input type="text"  name="name" ng-model="dictionary.name" class="form-control" required>
                        <div ng-show="aform.name.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.name.$error.required ">名称必填.</div>
                        </div>
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': aform.code.$invalid && (aform.code.$touched || aform.$submitted) }">
                        <label>编码:</label>
                        <input type="text"  name="code" ng-model="dictionary.code" class="form-control" ng-readonly="{{dictionary.id != null}}"
                               ng-remote-validate="${pageContext.request.contextPath}/admin/dictionary/validCode" required>
                        <div ng-show="aform.code.$touched || aform.$submitted"  class="help-block">
                            <div ng-show="aform.code.$error.required ">编码必填.</div>
                            <div ng-show="aForm.code.$error.ngRemoteValidate">此编码已被占用</div>
                        </div>
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
                    <div class="col-md-6 col-md-offset-3">您确认删除字典：{{dictionary.name}}吗？</div>
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
