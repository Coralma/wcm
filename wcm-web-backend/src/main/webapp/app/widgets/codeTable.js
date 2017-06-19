angular.module("codeTable", [])
  .directive("codeTable", ['codeTableUtils','$q', function (codeTableUtils, $q) {
    return {
      require: 'ngModel',
      scope: {
        ngModel: "=",
        codeId: "@"
      },
      restrict: 'EA',
      replace: false,
      template: '<select class="weui-select" ng-model="ngModel" ng-options="opt.key as opt.value for opt in options"></select>',
      link: function ($scope, $elem, attrs, ngModelCtrl) {
        var ngm = $scope.ngModel;
        var ci = $scope.codeId;
        $scope.options=[];
        $q.when(codeTableUtils.loadCodeTable()).then(function (data) {
            //console.log(JSON.stringify(data));
            var options = _.filter(data, {'codeId': ci});
            $scope.options = options;
        });
        $scope.chosen = function (key) {
          $scope.ngModel = key;
        }
      }
    }
  }]);
