angular.module('userService',[]).factory('userService', ['Restangular','$timeout', function (Restangular,$timeout) {
    return {
        loadUsers: loadUsers,
        saveUser: saveUser,
        deleteUser: deleteUser
    };

    function loadUsers() {
        return Restangular.all('user/loadUsers').post();
    }

    function saveUser(user) {
        return Restangular.all('user/saveUser').post(user, {}, {'Content-Type': 'application/json' });
    }

    function deleteUser(id) {
        return Restangular.all('user/deleteUser').post(id, {}, {'Content-Type': 'application/json' });
    }
}]);