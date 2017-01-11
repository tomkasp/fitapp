(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .component('activitytracker', {
            controller: 'ActivityTrackersController as vm',
            templateUrl: 'app/dashboard/activitytracker/activitytrackers.html'
        })
})();
