(function() {
    'use strict';

    angular
        .module('fitappApp')
        .controller('DietDialogController', DietDialogController);

    DietDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Diet'];

    function DietDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Diet) {
        var vm = this;

        vm.diet = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.diet.id !== null) {
                Diet.update(vm.diet, onSaveSuccess, onSaveError);
            } else {
                Diet.save(vm.diet, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fitappApp:dietUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
