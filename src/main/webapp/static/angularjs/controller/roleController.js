lypApp.controller('roleController', function ($scope, $http, $uibModal) {
    $scope.role = {};

    $scope.pager = {
        pageNum:1,
        pageSize:10,
    };
    $scope.orderBy = "id";
    $scope.sort = "asc";

    //初始化表头
    $scope.tableHead = new Array();
    $scope.tableHead.push({code:"id",           name:"ID",            class:"sorting_asc", style:"width:40px;",     sort:"asc"});
    $scope.tableHead.push({code:"roleCode",     name:"角色编码",      class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"roleName",     name:"角色名称",      class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"status",       name:"状态",          class:"sorting",     style:"width:80px;",     sort:""});
    $scope.tableHead.push({code:"createTime",   name:"注册时间",      class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"",             name:"操作",          class:"",            style:"width:100px;",    sort:""});

    $scope.list = function () {
        var data = {pageNum:$scope.pager.pageNum,pageSize:$scope.pager.pageSize,role:$scope.role,orderBy:$scope.orderBy, sort:$scope.sort};
        $http({
            method: 'POST',
            url: '/admin/role/list',
            data: data
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.pager = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };


    $scope.upate = function (role) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'roleUpdateController',
            //size: "lg",
            resolve: { role: role }
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            //$scope.selected = selectedItem;
            console.log(data);
            $scope.list();
            $('.bottom-right').notify({
                type:'info',
                message: { text: '更新成功! ' }
            }).show();
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    $scope.addRole = function () {
        var role = {};
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'roleUpdateController',
            resolve: { role: role }
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            console.log(data);
            $scope.list();
            $('.bottom-right').notify({
                type:'success',
                message: { text: '新增成功!' }
            }).show();
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    //删除提示弹框
    $scope.delete = function (role) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'deleteConfirm.html',
            controller: 'roleUpdateController',
            size: "sm",
            resolve: { role: role }
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            //$scope.selected = selectedItem;
            console.log(data);
            $scope.list();
            $('.bottom-right').notify({
                type:'info',
                message: { text: '删除成功!' }
            }).show();
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    //表头排序
    $scope.sortColumn = function(head) {
        if(head.code != ""){
            angular.forEach($scope.tableHead,  function(curHead){
                if(curHead.code != ""){
                    if(curHead.code == head.code){
                        $scope.orderBy = head.code;
                        if(head.sort  == ""){
                            $scope.sort = "asc";
                            head.sort = "asc";
                            head.class = "sorting_asc";
                        } else if(head.sort == "asc"){
                            $scope.sort = "desc";
                            head.sort = "desc";
                            head.class = "sorting_desc";
                        } else {
                            $scope.sort = "asc";
                            head.sort = "asc";
                            head.class = "sorting_asc";
                        }
                    } else {
                        curHead.class = "sorting";
                        curHead.sort = "";
                    }
                }
            });
            $scope.list();
        }
    }

    $scope.list();
});




lypApp.controller('roleUpdateController', function ($scope,  $http, $uibModalInstance, role) {

    $scope.role = role;

    $scope.save = function (vaild) {
        if(vaild){
            $http({
                method: 'POST',
                url: '/admin/role/update',
                data: role
            }).then(function successCallback(response) {
                console.log(response);
                if(response.status == 200){
                    $uibModalInstance.close("更新成功");
                }
            }, function errorCallback(response) {
                console.log(response);
            });
        } else {
            alert("请修正表单后，再提交");
        }
    };

    $scope.updateDel = function () {
        $http({
            method: 'POST',
            url: '/admin/role/del',
            data: role
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $uibModalInstance.close("删除成功");
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});