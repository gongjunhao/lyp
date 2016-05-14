lypApp.controller('cmsController', function ($scope, $http, $uibModal, Upload) {

    $scope.card = {};
    //激活
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
                $scope.card = {};
                $scope.files = [];
                alert("注册成功！");
                window.location.href = "/cms/card/query";
            }
        }, function (resp) {
            console.log('Error status: ' + resp.status);
        }, function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
        });
    };

    //删除图片
    $scope.remove = function(item) {
        var index = $scope.files.indexOf(item);
        $scope.files.splice(index, 1);
    };

    //查询
    $scope.query = function (vaild) {
        if(vaild){
            if(($scope.card.certificateCode != null && $scope.card.certificateCode != "") || ($scope.card.mobileNumber != null && $scope.card.mobileNumber != "") ){
                $http({
                    method: 'POST',
                    url: '/cms/card/query',
                    data:$scope.card
                }).then(function successCallback(response) {
                    console.log(response);
                    if(response.status == 200){
                        $scope.insurances = response.data.insurances;
                    }
                }, function errorCallback(response) {
                    console.log(response);
                });
            } else {
                alert("至少输入1项");
            }
        } else {
            alert("请修正表单");
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

    $scope.getProvinces = function () {
        $http({
            method: 'GET',
            url: '/cms/childNodes/province',
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.provinces = response.data;
                $scope.getCitys($scope.provinces[0].code);
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.getCitys = function (provinceCode) {
        $http({
            method: 'GET',
            url: '/cms/childNodes/'+provinceCode,
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.citys = response.data;
                $scope.getProviders($scope.citys[0].code);
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.getProviders = function (cityCode) {
        $http({
            method: 'GET',
            url: '/cms/childNodes/'+cityCode,
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.providers = response.data;
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

    $scope.getDictMap();
    $scope.pageInit = function () {
        $scope.getMobiles();
        $scope.getProvinces();
        $scope.getStores();
    }
});