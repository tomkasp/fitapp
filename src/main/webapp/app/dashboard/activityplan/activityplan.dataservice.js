(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .factory('ActivityPlanDataservice', ActivityPlanDataservice);

    ActivityPlanDataservice.$inject = ['$http'];

    function ActivityPlanDataservice($http) {

        var service = {
            getAssignedPlan: getAssignedPlan
        };


        return service;

        function getAssignedPlan() {
            $http.get("/plans")
                .then(getAssignedPlansDataComplete)
                .catch(getAssignedPlansDataFailed);

            function getAssignedPlansDataComplete(response) {
                return response.data;
            }

            function getAssignedPlansDataFailed() {

            }
        }

    }
})();
