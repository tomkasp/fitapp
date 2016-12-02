(function() {
    'use strict';

    angular
        .module('fitappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('diet', {
            parent: 'entity',
            url: '/diet?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fitappApp.diet.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diet/diets.html',
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
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diet');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('diet-detail', {
            parent: 'entity',
            url: '/diet/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fitappApp.diet.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diet/diet-detail.html',
                    controller: 'DietDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diet');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Diet', function($stateParams, Diet) {
                    return Diet.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'diet',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('diet-detail.edit', {
            parent: 'diet-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diet/diet-dialog.html',
                    controller: 'DietDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Diet', function(Diet) {
                            return Diet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('diet.new', {
            parent: 'diet',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diet/diet-dialog.html',
                    controller: 'DietDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('diet', null, { reload: 'diet' });
                }, function() {
                    $state.go('diet');
                });
            }]
        })
        .state('diet.edit', {
            parent: 'diet',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diet/diet-dialog.html',
                    controller: 'DietDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Diet', function(Diet) {
                            return Diet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diet', null, { reload: 'diet' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('diet.delete', {
            parent: 'diet',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diet/diet-delete-dialog.html',
                    controller: 'DietDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Diet', function(Diet) {
                            return Diet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diet', null, { reload: 'diet' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
