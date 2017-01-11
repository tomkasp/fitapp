(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .component('counter', {
            bindings: {
                count: '='
            },
            controller: 'ActivityCalendarController',
            templateUrl: 'app/dashboard/activitycalendar/activitycalendar.view.html'
        })
})();
