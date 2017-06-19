angular.module('userList.ctrl', [])
    .controller('UserListCtrl', ['$scope','$rootScope', 'Restangular','$timeout', '$filter','$state','userService','$q',
        function ($scope, $rootScope, Restangular, $timeout, $filter,$state,userService,$q) {
            // load full time accc
            $rootScope.mainTitle = "User List";
            $scope.loadType = false;

            // init the user list data
            userService.loadUsers().then(function (data) {
                //console.log(JSON.stringify(data));
                $scope.users = data;
                $scope.accountBackups  = $scope.users;
            });

            $scope.accountFilter = function(data) {
                $scope.users = _.filter($scope.accountBackups, function(o) {
                    return o.username.indexOf(data) >= 0;
                });
            };

            $scope.myPagingFunction = function() {
                console.log('myPagingFunction');
                $scope.loadType = true;
            };

            $scope.editAccount = function(account) {
                $state.go("userEdit", {account:account});
            };

            $scope.addNewUser = function() {
                $state.go("userEdit");
            };

            var $searchBar = $('#searchBar'),
                $searchText = $('#searchText'),
                $searchInput = $('#searchInput'),
                $searchClear = $('#searchClear');

            function cancelSearch(){
                $searchBar.removeClass('weui-search-bar_focusing');
                $searchText.show();
            }

            $searchText.on('click', function(){
                $searchBar.addClass('weui-search-bar_focusing');
                $searchInput.focus();
            });
            $searchInput.on('blur', function () {
                if(!this.value.length) cancelSearch();
            });
            $searchClear.on('click', function() {
                $searchInput.focus();
            });
        }
    ]);