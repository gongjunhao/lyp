lypApp.controller('dictionaryController', function ($scope, $http, $uibModal) {
    $scope.dictionary = {};
    $scope.pager = {
        pageNum:1,
        pageSize:10,
    };
    $scope.orderBy = "id";
    $scope.sort = "asc";
    //Z-tree setting
    $scope.setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId"
            }
        },
        callback: {
            onClick:  dictTreeOnClick
        }
    };
    //点击树相应事件
    function dictTreeOnClick(event, treeId, treeNode) {
        console.log(treeNode);
        $scope.selectNode = treeNode;
        $scope.dictionary.parentId = treeNode.id;
        $scope.list();
    }

    //页面初始化
    $scope.treeInit = function (id) {
        $http({
            method: 'POST',
            url: '/admin/dictionary/nodes'
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $.fn.zTree.init($("#dictTree"), $scope.setting, response.data);
                var treeObj = $.fn.zTree.getZTreeObj("dictTree");
                var rootNode = treeObj.getNodeByParam("id", 1, null);
                if(rootNode != null){
                    treeObj.expandNode(rootNode, true, null, null, null);
                }
                if(id != null){
                    var tempNode = treeObj.getNodeByParam("id", id, null);
                    if(tempNode != null){
                        treeObj.expandNode(tempNode, true, null, null, null);
                    }
                }
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    }

    //初始化表头
    $scope.tableHead = new Array();
    $scope.tableHead.push({code:"id",                   name:"ID",           class:"sorting_asc", style:"width:40px;",     sort:"asc"});
    $scope.tableHead.push({code:"parentId",            name:"父ID",       class:"sorting",     style:"width:40px;",    sort:""});
    $scope.tableHead.push({code:"name",                name:"名称",          class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"code",                name:"编码",          class:"sorting",     style:"width:100px;",     sort:""});
    $scope.tableHead.push({code:"status",              name:"状态",          class:"sorting",     style:"width:50px;",     sort:""});
    $scope.tableHead.push({code:"createTime",         name:"注册时间",      class:"sorting",     style:"width:100px;",    sort:""});
    $scope.tableHead.push({code:"",                     name:"操作",          class:"",            style:"width:100px;",    sort:""});

    //加载列表
    $scope.list = function () {
        var data = {pageNum:$scope.pager.pageNum,pageSize:$scope.pager.pageSize,dictionary:$scope.dictionary,orderBy:$scope.orderBy, sort:$scope.sort};
        $http({
            method: 'POST',
            url: '/admin/dictionary/list',
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

    $scope.upate = function (dictionary) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'updateDialog.html',
            controller: 'dictionaryUpdateController',
            resolve: { dictionary: dictionary }
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            //$scope.selected = selectedItem;
            console.log(data);
            $scope.list();
            $scope.treeInit(dictionary.parentId);
            $('.bottom-right').notify({
                type:'info',
                message: { text: '更新成功! ' }
            }).show();
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    $scope.addDictionary = function () {
        if($scope.selectNode != null){
            var dictionary = {parentId:$scope.dictionary.parentId};
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'updateDialog.html',
                controller: 'dictionaryUpdateController',
                resolve: { dictionary: dictionary}
            });
            //弹窗更新完毕后，刷新列表
            modalInstance.result.then(function (data) {
                console.log(data);
                $scope.list();
                $scope.treeInit(dictionary.parentId);
                $('.bottom-right').notify({
                    type:'success',
                    message: { text: '新增成功!' }
                }).show();
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        } else {
            alert("新增字典请先选中左侧字典分类树，作为父级");
        }
    };

    //删除提示弹框
    $scope.delete = function (dictionary) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'deleteConfirm.html',
            controller: 'dictionaryUpdateController',
            size: "sm",
            resolve: { dictionary: dictionary}
        });
        //弹窗更新完毕后，刷新列表
        modalInstance.result.then(function (data) {
            //$scope.selected = selectedItem;
            console.log(data);
            $scope.list();
            $scope.treeInit(dictionary.parentId);
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




lypApp.controller('dictionaryUpdateController', function ($scope,  $http, $uibModalInstance, dictionary) {

    $scope.dictionary = dictionary;

    var treeObj = $.fn.zTree.getZTreeObj("dictTree");
    $scope.parentNode = treeObj.getNodeByParam("id", $scope.dictionary.parentId, null);

    $scope.save = function (vaild) {
        if(vaild){
            $http({
                method: 'POST',
                url: '/admin/dictionary/update',
                data: dictionary
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
            url: '/admin/dictionary/del',
            data: dictionary
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