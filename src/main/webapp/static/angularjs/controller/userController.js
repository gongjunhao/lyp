lypApp.controller('userController', function ($scope, $http, $uibModal) {
    $scope.user = {};

    $scope.pager = {
        pageNum:1,
        pageSize:10,
    };
    $scope.orderBy = "id";
    $scope.sort = "asc";

    //初始化表头
    $scope.tableHead = new Array();
    $scope.tableHead.push({code:"id",           name:"ID",            class:"sorting_asc", style:"width:40px;",     sort:"asc"});
    $scope.tableHead.push({code:"loginName",    name:"登录名",        class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"nickName",     name:"昵称",          class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"status",       name:"状态",          class:"sorting",     style:"width:80px;",     sort:""});
    $scope.tableHead.push({code:"createTime",   name:"注册时间",      class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"",             name:"操作",          class:"",            style:"width:100px;",    sort:""});

    $scope.list = function () {
        var data = {pageNum:$scope.pager.pageNum,pageSize:$scope.pager.pageSize,user:$scope.user,orderBy:$scope.orderBy, sort:$scope.sort};
        $http({
            method: 'POST',
            url: '/admin/user/list',
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


    $scope.upate = function (user) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'userUpdateController',
            //size: "lg",
            resolve: { user: user }
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            //$scope.selected = selectedItem;
            console.log(data);
            $scope.list();
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




lypApp.controller('userUpdateController', function ($scope,  $http, $uibModalInstance, user) {

    $scope.user = user;

    $scope.save = function () {
        $http({
            method: 'POST',
            url: '/admin/user/update',
            data: user
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $uibModalInstance.close("更新成功");
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});