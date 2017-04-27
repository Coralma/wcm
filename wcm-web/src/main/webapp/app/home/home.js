angular.module('home.controller', [])
    .controller('HomeCtrl', ['$scope', '$rootScope', '$location', '$http', 'Restangular', '$state', '$timeout',
        function ($scope, $rootScope, $location, $http, Restangular, $state, $timeout) {
            $rootScope.mainTitle = "首页";
        }]);
