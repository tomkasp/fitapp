(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .component('activitystatistics', {
            controller: 'ActivityStatisticController as vm',
            templateUrl: 'app/dashboard/statistics/statistics.view.html'
        })
})();
