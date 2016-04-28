lypApp.controller('userController', function ($scope, $http) {
    $scope.user = {};
    $scope.pager = {
        pageNum:1,
        pageSize:10
    };

    $scope.list = function () {
        var data = {pageNum:$scope.pager.pageNum,
                     pageSize:$scope.pager.pageSize,
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
                $scope.pager = response.data;
            }
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.list();
});
