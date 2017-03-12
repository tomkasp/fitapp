(function() {
    'use strict';

    angular
        .module('fitappApp.training')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('training', {
            url: '/training',
            templateUrl: 'app/training/training.view.html',
            parent: 'app',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/training/training.view.html',
                    controller: 'TrainingController',
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

