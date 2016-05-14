<%--
  Created by IntelliJ IDEA.
  Role: junhao
  Date: 2016/4/30
  Time: 23:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>乐优品科技后台管理-质保卡激活管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <jsp:include page="../static.jsp"/>
    <!-- lypApp JavaScript -->
    <script src="${pageContext.request.contextPath}/static/angularjs/controller/insuranceController.js"></script>
</head>

<body ng-app="lypApp" ng-controller="insuranceController">

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
                            <h4 class="panel-title pull-left" style="padding-top: 7.5px;">激活列表</h4>
                            <div class="btn-group pull-right" ng-if="false">
                                <a href="#" class="btn btn-success btn-sm" ng-click="addRole()">新增激活</a>
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
                                                    激活码:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="insurance.certificateCode" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-1" class="dataTables_filter">
                                                <label>
                                                    品牌:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="insurance.telBrand" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-4" class="dataTables_filter">
                                                <label>
                                                    型号:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="insurance.telModel" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-5" class="dataTables_filter">
                                                <label>
                                                    IMEI码:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="insurance.telIMEI" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-6" class="dataTables_filter">
                                                <label>
                                                    顾客姓名:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="insurance.customerName" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-7" class="dataTables_filter">
                                                <label>
                                                    手机号码:
                                                    <input type="text" class="form-control input-sm" placeholder="" ng-model="insurance.mobileNumber" aria-controls="dataTables-example">
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-2" class="dataTables_filter">
                                                <label>
                                                    状态:
                                                    <select name="stauts" ng-options="status.code as status.name for status in statuses" class="form-control" ng-model="insurance.status">
                                                        <option value="">--请选择--</option>
                                                    </select>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div id="dataTables-3" class="dataTables_filter">
                                                <button type="button" class="btn btn-info" ng-click="list()">查询</button>
                                                <button type="button" class="btn btn-info" ng-click="insurance={}">清空</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table table-striped table-bordered table-hover dataTable no-footer"
                                                   id="dataTables-example" insurance="grid"
                                                   aria-describedby="dataTables-example_info">
                                                <thead>
                                                <tr insurance="row">
                                                    <th class="{{head.class}}" tabindex={{$index}}" ng-repeat="head in tableHead"
                                                        aria-sort="{{head.code}}" ng-click="sortColumn(head)" style="{{head.style}}">{{head.name}}
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr class="gradeA odd" insurance="row" ng-repeat="insurance in pager.list">
                                                    <td class="sorting_1">{{insurance.id}}</td>
                                                    <td>{{insurance.certificateCode}}</td>
                                                    <td>{{dictMaps[insurance.telBrand]}}</td>
                                                    <td class="center">{{dictMaps[insurance.telModel]}}</td>
                                                    <td class="center">{{insurance.telIMEI}}</td>
                                                    <td class="center">{{insurance.customerName}}</td>
                                                    <td class="center">{{insurance.mobileNumber}}</td>
                                                    <td class="center">{{dictMaps[insurance.store]}}</td>
                                                    <td class="center">{{dictMaps[insurance.status]}}</td>
                                                    <td class="center">{{insurance.createTime | longToDate | date:'yyyy-MM-dd HH:mm'}}</td>
                                                    <td class="center">
                                                        <button type="button" class="btn btn-outline btn-primary btn-xs" ng-click="upate(insurance)">编辑</button>
                                                        <button type="button" class="btn btn-outline btn-danger btn-xs" ng-click="delete(insurance)">删除</button>
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
                <h3 class="modal-title">更新激活信息</h3>
            </div>
            <form name="insuranceForm" ng-submit="save(insuranceForm.$valid)" novalidate>
                <div class="modal-body">
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.certificateCode.$invalid && insuranceForm.$submitted }">
                        <label>激活码:</label>
                        <input type="text"  name="certificateCode" ng-model="insurance.certificateCode" class="form-control" ng-disabled="true">
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>品牌:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.telBrand" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>型号:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.telModel" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>IMEI码:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.telIMEI" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>顾客姓名:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.customerName" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>顾客邮箱:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.customerEmail" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>手机号码:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.mobileNumber" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>省份:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.province" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>城市:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.city" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.insuranceName.$invalid && insuranceForm.$submitted }">
                        <label>供应商:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.provider" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.customerName.$invalid && insuranceForm.$submitted }">
                        <label>门店:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.store" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.customerName.$invalid && insuranceForm.$submitted }">
                        <label>门店办理人:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.storeTransactor" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.customerName.$invalid && insuranceForm.$submitted }">
                        <label>创建时间:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.createTime" ng-disabled="true" >
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.stauts.$invalid && insuranceForm.$submitted }">
                        <label>状态:</label>
                        <select name="stauts" ng-options="status.code as status.name for status in statuses" class="form-control" ng-model="insurance.status">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                    <div ng-class="{ 'form-group':true, 'has-error': insuranceForm.customerName.$invalid && insuranceForm.$submitted }">
                        <label>备注:</label>
                        <input type="text" name="customerName" class="form-control" ng-model="insurance.mark" >
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">照片</label>
                        <div ng-repeat="file in attaches">
                            <img ng-src="{{file.savePath}}" class="img-rounded" width="500px" height="250px">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit"  >保存</button>
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
                        <div class="col-md-6 col-md-offset-3">您确认删除用户：{{insurance.customerName}}激活吗？</div>
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
