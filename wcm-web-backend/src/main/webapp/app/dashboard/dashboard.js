angular.module('dashboard.controller', [])
    .controller('DashboardCtrl', ['$scope', '$rootScope', '$location', '$http', 'Restangular', '$state', '$timeout',
        function ($scope, $rootScope, $location, $http, Restangular, $state, $timeout) {
            $rootScope.mainTitle = "Dashboard";
        }]);
