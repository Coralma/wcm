angular.module('userEdit.ctrl', [])
    .controller('UserEditCtrl', ['$scope','$http','Restangular','$state','$timeout','$stateParams','userService',
        function ($scope, $http, Restangular, $state, $timeout, $stateParams, userService) {
            $scope.isSubmit = false;
            $scope.currentUser = $stateParams.account;
            $scope.userForm = {username:"", mobile:"", email:"",doeDate: _.now(), roleId: "2", status: "1"};
            if(!_.isNull($scope.currentUser)) {
                $scope.userPageTitle = "编辑用户";
                $scope.userForm = $scope.currentUser;
            } else {
                $scope.userPageTitle = "新增用户";
            }

            // function to save user
            $scope.saveUser = function() {
                $scope.isSubmit = true;
                userService.saveUser($scope.userForm).then(function (data) {
                    console.log(JSON.stringify(data));
                    $scope.backUserList();
                });
            };

            // function to delete user
            $scope.deleteUser = function() {
                $scope.isSubmit = true;
                userService.deleteUser($scope.userForm.id).then(function (data) {
                    console.log(JSON.stringify(data));
                    $scope.backUserList();
                });
            };

            // function to back to user list
            $scope.backUserList = function() {
                $scope.isSubmit = true;
                $state.go("userList");
            }


        }]);
