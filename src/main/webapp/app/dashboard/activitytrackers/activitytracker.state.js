(function() {
    'use strict';

    angular
        .module('fitappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('activitytracker', {
                parent: 'dashboard',
                url: '/trackers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'fitappApp.diet.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/dashboard/activitytrackers/activitytrackers.html',
                        controller: 'ActivityTrackersController',
                        controllerAs: 'vm'
                    }
                },
                params: {
                    page: {
                        value: '1',
                        squash: true
                    },
                    sort: {
                        value: 'id,asc',
                        squash: true
                    },
                    search: null
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('diet');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    }

})();
