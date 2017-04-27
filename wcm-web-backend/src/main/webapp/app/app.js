angular.module('wcmApp', ['restangular', 'ui.router', 'ng-bootstrap-grid',
    'main.controller', 'dashboard.controller'])
    .config(function ($stateProvider, $urlRouterProvider, RestangularProvider, $httpProvider) {
        RestangularProvider.setBaseUrl('/wcb');
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

        $stateProvider
            .state('main', {
                url: '/main',
                controller: 'MainCtrl',
                templateUrl: 'app/main/main.html'
            })
            .state('main.dashboard', {
                url: '/dashboard',
                controller: 'DashboardCtrl',
                templateUrl: 'app/dashboard/dashboard.html'
            });

        $urlRouterProvider.otherwise('/main/dashboard');
    });