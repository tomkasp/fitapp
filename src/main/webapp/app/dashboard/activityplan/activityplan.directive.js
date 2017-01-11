(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .directive('plan', PlanDirective);

    function PlanDirective() {
        var directive = {
            link: link,
            templateUrl: 'app/dashboard/activityplan/activityplan.view.html',
            restrict: 'E',
            controller: 'ActivityPlanController'
        };
        return directive;

        function link() {

        }
    }


})();
