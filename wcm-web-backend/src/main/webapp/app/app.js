angular.module('wcmApp', ['restangular', 'ui.router', 'formatDate','codeTable','codeTableUtils',
    'userEdit.ctrl','userList.ctrl','userService'])
    .config(function ($stateProvider, $urlRouterProvider, RestangularProvider, $httpProvider) {
        RestangularProvider.setBaseUrl('/backend');
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

        $stateProvider
            .state('userEdit', {
                url: '/userEdit',
                controller: 'UserEditCtrl',
                templateUrl: 'app/user/userEdit.html',
                params:{account:null}
            })
            .state('userList', {
                url: '/userList',
                controller: 'UserListCtrl',
                templateUrl: 'app/user/userList.html'
            });
        $urlRouterProvider.otherwise('/userList');
    });