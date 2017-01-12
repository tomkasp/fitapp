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
            controller: 'ActivityPlanController',
            controllerAs: 'vm'
        };
        return directive;

        function link() {

        }
    }


})();
