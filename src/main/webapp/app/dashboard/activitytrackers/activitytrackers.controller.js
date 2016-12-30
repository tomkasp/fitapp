(function () {
    'use strict';

    angular
        .module('fitappApp')
        .controller('ActivityTrackersController', ActivityTrackersController);

    ActivityTrackersController.$inject = ['StravaDataservice'];

    function ActivityTrackersController(StravaDataservice,$scope) {
        var vm = this;
        vm.stravaActivationLink = '';
        vm.code = '';
        vm.activities = [];

        vm.getToken = getToken;


        vm.getActivities = getActivities;
        vm.activateStrava = activateStrava;
        vm.itemClass = itemClass;
        activate();

        vm.tab=1;


        function activate() {
            StravaDataservice.getActivities().then(function (data) {
                vm.activities = data;
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

        function itemClass(activityType) {
            var styles = {STRAVA: "b-primary",
            GARMIN: "b-success"};

            return styles[activityType];
        }

    }
})();
