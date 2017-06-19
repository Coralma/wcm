angular.module('wcmApp', ['restangular', 'ui.router',
    'home.controller','userEdit.ctrl','userList.ctrl'])
    .config(function ($stateProvider, $urlRouterProvider, RestangularProvider, $httpProvider) {
        RestangularProvider.setBaseUrl('/wcm');
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

        $stateProvider
            .state('home', {
                url: '/home',
                controller: 'HomeCtrl',
                templateUrl: 'app/home/home.html'
            })
            .state('userEdit', {
                url: '/userEdit',
                controller: 'UserEditCtrl',
                templateUrl: 'app/user/userEdit.html'
            })
            .state('userList', {
                url: '/userList',
                controller: 'UserListCtrl',
                templateUrl: 'app/user/userList.html'
            });
        $urlRouterProvider.otherwise('/home');
    });