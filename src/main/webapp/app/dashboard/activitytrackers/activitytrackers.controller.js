(function () {
    'use strict';

    angular
        .module('fitappApp')
        .controller('ActivityTrackersController', ActivityTrackersController);

    ActivityTrackersController.$inject = ['StravaDataservice'];

    function ActivityTrackersController(StravaDataservice) {
        var vm = this;
        vm.stravaActivationLink = '';

        vm.code = '';

        vm.getToken = getToken;

        function getToken() {

            StravaDataservice.getToken(vm.code);
        }

        vm.getActivities = getActivities;

        activate();


        function activate() {
            StravaDataservice.getAuthorizationLink().then(function (data) {
                vm.stravaActivationLink = data.activationLink;
            });
        }

        function getActivities() {
            StravaDataservice.getActivities().then(function (data) {
                vm.activities = data;
            });
        }

    }
})();
