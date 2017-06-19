angular.module('codeTableUtils',[]).factory('codeTableUtils', ['Restangular','$timeout', function (Restangular,$timeout) {

    var CODE_TABLE_DATA = [];

    return {
        loadCodeTable: loadCodeTable
    };

    function loadCodeTable() {
        if(CODE_TABLE_DATA.length > 0 ) {
            return CODE_TABLE_DATA;
        } else {
            return Restangular.all('codeTable/loadCodeTables').post().then(function(data) {
                CODE_TABLE_DATA = data;
                return CODE_TABLE_DATA;
            });
        }
    }

}]);