(function () {
    'use strict';

    angular
        .module('fitappApp')
        .directive('sparkline', sparkline);

    function sparkline() {
        var directive = {
            restrict: 'A',
            // require: '?ngModel',
            scope: {
                sparklinedata: '=',
                options: '='
            },
            link: linkFunction
        };

        return directive;

        function linkFunction(scope, element, attrs) {
            angular.element(element).sparkline(scope.sparklinedata, scope.options)
        }
    }
})();


