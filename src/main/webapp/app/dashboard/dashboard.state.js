(function() {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('dashboard', {
            url: '/dashboard',
            templateUrl: 'app/dashboard/dashboard.view.html',
            parent: 'app',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/dashboard/dashboard.view.html',
                    controller: 'DashboardController',
                    controllerAs: 'vm'
                },

            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]


            }

        });
    }
})();

