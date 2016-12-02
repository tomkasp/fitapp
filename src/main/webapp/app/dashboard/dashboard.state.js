(function() {
    'use strict';

    angular
        .module('fitappApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('dashboard', {
            abstract: true,
            parent: 'app'
        });
    }
})();
