lypApp.controller('cardController', function ($scope, $http, $uibModal) {
    $scope.card = {};

    $scope.hasProduce = false;
    
    $scope.pager = {
        pageNum:1,
        pageSize:10,
    };
    $scope.orderBy = "id";
    $scope.sort = "asc";

    //初始化表头
    $scope.tableHead = new Array();
    $scope.tableHead.push({code:"id",           name:"ID",            class:"sorting_asc", style:"width:40px;",     sort:"asc"});
    $scope.tableHead.push({code:"code",         name:"激活码",        class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"mark",         name:"备注",          class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"status",       name:"状态",          class:"sorting",     style:"width:80px;",     sort:""});
    $scope.tableHead.push({code:"createTime",  name:"生成时间",      class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"",              name:"操作",          class:"",            style:"width:100px;",    sort:""});

    $scope.list = function () {
        var data = {pageNum:$scope.pager.pageNum,pageSize:$scope.pager.pageSize,card:$scope.card,orderBy:$scope.orderBy, sort:$scope.sort};
        $http({
            method: 'POST',
            url: '/admin/certificateCard/list',
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
    
    $scope.produce = function (vaild) {
        var data = {cardNum:$scope.cardNum, mark:$scope.mark};
        if(vaild){
            if(isNaN($scope.cardNum)){
                alert("请输入一个整数！");
            } else {
                $http({
                    method: 'POST',
                    url: '/admin/certificateCard/produce',
                    data: data
                }).then(function successCallback(response) {
                    console.log(response);
                    if(response.status == 200){
                        $scope.hasProduce = true;
                        $scope.cards = response.data.cards;
                    }
                }, function errorCallback(response) {
                    console.log(response);
                });
            }
        } else {
            alert("请完善表单！一次生成个数不得超过1000个");
        }
    };
    
    $scope.saveCards = function () {
        $http({
            method: 'POST',
            url: '/admin/certificateCard/saveProduce',
            data: $scope.cards
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $('.bottom-right').notify({
                    type:'info',
                    message: { text: '存库成功! ' }
                }).show();
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    }
    

    $scope.upate = function (card) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'cardUpdateController',
            //size: "lg",
            resolve: { card: card }
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
        var card = {};
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'cardUpdateController',
            resolve: { card: card }
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
    $scope.delete = function (card) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'deleteConfirm.html',
            controller: 'cardUpdateController',
            size: "sm",
            resolve: { card: card }
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




lypApp.controller('cardUpdateController', function ($scope,  $http, $uibModalInstance, card) {

    $scope.card = card;

    $scope.save = function (vaild) {
        if(vaild){
            $http({
                method: 'POST',
                url: '/admin/certificateCard/update',
                data: card
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
            url: '/admin/certificateCard/del',
            data: card
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