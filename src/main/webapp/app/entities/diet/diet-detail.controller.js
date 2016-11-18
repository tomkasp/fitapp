(function() {
    'use strict';

    angular
        .module('fitappApp')
        .controller('DietDetailController', DietDetailController);

    DietDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Diet'];

    function DietDetailController($scope, $rootScope, $stateParams, previousState, entity, Diet) {
        var vm = this;

        vm.diet = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fitappApp:dietUpdate', function(event, result) {
            vm.diet = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
