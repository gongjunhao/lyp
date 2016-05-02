lypApp.controller('cmsController', function ($scope, $http, $uibModal, Upload) {
    $scope.card = {};

    $scope.activeCard = function (vaild) {
        if(vaild && $scope.files != null && $scope.files.length > 0){
            $scope.upload($scope.files, $scope.card);
        } else {
            alert("请修正表单");
        }
    };
    // upload on files
    $scope.upload = function (files, card) {
        Upload.upload({
            method: 'POST',
            url: '/cms/card/active',
            data: {file: files, 'card': Upload.json(card)},
            arrayKey: ''
        }).then(function (resp) {
            console.log(resp);
            console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
            if(resp.status == 200){
                alert("注册成功！");
            }
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
    };

    $scope.remove = function(item) {
        var index = $scope.files.indexOf(item);
        $scope.files.splice(index, 1);
    }

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
    
    $scope.getStores = function () {
        $http({
            method: 'GET',
            url: '/cms/childNodes/store'
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.stores = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    }

    $scope.getMobiles = function () {
        $http({
            method: 'GET',
            url: '/cms/childNodes/mobile'
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.mobiles = response.data;
                $scope.getTelModels($scope.mobiles[0].code);
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    }

    $scope.getTelModels = function (brand) {
        $http({
            method: 'GET',
            url: '/cms/childNodes/'+brand,
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.models = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    }
    
    $scope.pageInit = function () {
        $scope.getMobiles();
        $scope.getStores();
    }
});