lypApp.controller('insuranceController', function ($scope, $http, $uibModal) {
    $scope.insurance = {};

    $scope.pager = {
        pageNum:1,
        pageSize:10,
    };
    $scope.orderBy = "id";
    $scope.sort = "desc";

    //初始化表头
    $scope.tableHead = new Array();
    $scope.tableHead.push({code:"id",                       name:"#",            class:"sorting_asc", style:"width:30px;",     sort:"desc"});
    $scope.tableHead.push({code:"certificateCode",        name:"激活码",      class:"sorting",     style:"width:60px;",    sort:""});
    $scope.tableHead.push({code:"telBrand",                name:"品牌",      class:"sorting",     style:"width:50px;",    sort:""});
    $scope.tableHead.push({code:"telModel",                name:"型号",          class:"sorting",     style:"width:80px;",     sort:""});
    $scope.tableHead.push({code:"telIMEI",                 name:"IMEI码",      class:"sorting",     style:"width:80px;",    sort:""});
    $scope.tableHead.push({code:"customerName",           name:"姓名",      class:"sorting",     style:"width:50px;",    sort:""});
    $scope.tableHead.push({code:"mobileNumber",           name:"手机号码",      class:"sorting",     style:"width:50px;",    sort:""});
    $scope.tableHead.push({code:"store",                   name:"门店",      class:"sorting",     style:"width:70px;",    sort:""});
    $scope.tableHead.push({code:"status",                  name:"状态",      class:"sorting",     style:"width:50px;",    sort:""});
    $scope.tableHead.push({code:"createTime",             name:"申请时间",      class:"sorting",     style:"width:80px;",    sort:""});
    $scope.tableHead.push({code:"",                         name:"操作",          class:"",            style:"width:100px;",    sort:""});

    $scope.list = function () {
        var data = {pageNum:$scope.pager.pageNum,pageSize:$scope.pager.pageSize,insurance:$scope.insurance,orderBy:$scope.orderBy, sort:$scope.sort};
        $http({
            method: 'POST',
            url: '/admin/insurance/list',
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

    $scope.getDictMap = function () {
        $http({
            method: 'GET',
            url: '/dicMap',
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.dictMaps = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.getCardStatus = function (brand) {
        $http({
            method: 'GET',
            url: '/cms/childNodes/status',
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.statuses = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.getDictMap();
    $scope.getCardStatus();

    $scope.upate = function (insurance) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'insuranceUpdateController',
            //size: "lg",
            resolve: { insurance: insurance }
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
        var insurance = {};
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'insuranceUpdateController',
            resolve: { insurance: insurance }
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
    $scope.delete = function (insurance) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'deleteConfirm.html',
            controller: 'insuranceUpdateController',
            size: "sm",
            resolve: { insurance: insurance }
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

    //导出提示弹框
    $scope.exportDialog = function () {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'exportDialog.html',
            controller: 'insuranceUpdateController',
            size: "sm",
            resolve: { insurance: $scope.insurance }
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            //$scope.selected = selectedItem;
            console.log(data);
            $scope.list();
            $('.bottom-right').notify({
                type:'info',
                message: { text: '导出成功!' }
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




lypApp.controller('insuranceUpdateController', function ($scope,  $http, $uibModalInstance, insurance) {

    $scope.insurance = insurance;
    
    $scope.getAttaches = function () {
        $http({
            method: 'GET',
            url: '/admin/insurance/getAttaches/'+insurance.id,
            data: insurance
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.attaches = response.data.attaches;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    if(insurance != null && insurance.id != null) {
        $scope.getAttaches();
    }
    

    $scope.save = function (vaild) {
        if(vaild){
            $http({
                method: 'POST',
                url: '/admin/insurance/update',
                data: insurance
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

    $scope.realExport = function () {
        var url = "/admin/insurance/exportExcel?time="+new Date().getTime();
        var param = "&insurance="+JSON.stringify($scope.insurance)+"&startTime="+$scope.startTime + "&endTime="+$scope.endTime;
        window.open(url+param);
        $uibModalInstance.close("导出成功");
    };

    $scope.updateDel = function () {
        $http({
            method: 'POST',
            url: '/admin/insurance/del',
            data: insurance
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

    $scope.getDictMap = function () {
        $http({
            method: 'GET',
            url: '/dicMap',
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.dictMaps = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.getCardStatus = function (brand) {
        $http({
            method: 'GET',
            url: '/cms/childNodes/status',
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.statuses = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };
    $scope.getCardStatus();
    $scope.getDictMap();
});