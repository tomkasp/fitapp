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
        vm.activities = [];

        vm.getToken = getToken;


        vm.getActivities = getActivities;
        vm.activateStrava = activateStrava;
        activate();


        function activate() {

            StravaDataservice.getAuthorizationLink().then(function (data) {
                vm.stravaActivationLink = data.activationLink;
            });
        }

        function getToken() {

            StravaDataservice.getToken(vm.code);
        }

        function activateStrava() {
            StravaDataservice.activateStrava()
        }

        function getActivities() {
            StravaDataservice.getActivities().then(function (data) {
                vm.activities = data;
            });
        }

    }
})();
