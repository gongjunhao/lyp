lypApp.controller('userController', function ($scope, $http) {
    $scope.users = [];
    $scope.user = {};
    $scope.pageNum = 1;
    $scope.pageSize = 10;

    $scope.list = function () {
        var data = {pageNum:$scope.pageNum,
                     pageSize:$scope.pageSize,
                    user:$scope.user,
                    orderby:$scope.orderby,
                    sort:$scope.sort
                    };
        $http({
            method: 'POST',
            url: '/admin/user/list',
            data:data
        }).then(function successCallback(response) {
            console.log(response);
            if(response.status == 200){
                $scope.users = response.data.list;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.list();
});
