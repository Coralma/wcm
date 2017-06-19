angular.module('userEdit.ctrl', [])
    .controller('UserEditCtrl', ['$scope','$http','Restangular','$state','$timeout','$stateParams','userService',
        function ($scope, $http, Restangular, $state, $timeout, $stateParams, userService) {
            $scope.result = {'message':'Initialize'};
            $scope.currentAccount = $stateParams.account;
            $scope.userForm = {username:"", startDate: _.now(), endDate:_.now(), applyNumber: 2, leaveType: 1};
            if(!_.isNull($scope.currentAccount)) {
                $scope.userPageTitle = "编辑用户";
                $scope.userForm.username = $scope.currentAccount.username;
            } else {
                $scope.userPageTitle = "新增用户";
            }

            // function to save user
            $scope.saveUser = function() {
                userService.saveUser($scope.currentAccount).then(function (data) {
                    console.log(JSON.stringify(data));
                });
            }
        }]);
