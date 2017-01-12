(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .controller('ActivityPlanController', ActivityPlanController);

    ActivityPlanController.$inject = ['ActivityPlanDataservice'];

    function ActivityPlanController(ActivityPlanDataservice) {
        var vm = this;

        vm.showPlan = false;

        activate();

        function activate() {
            // ActivityPlanDataservice.getAssignedPlan().then(function (data) {
            //     if(angular.isObject(data)){
            //         vm.showPlan = true;
            //     }
            // });
        }
    }


})();
