(function () {
    'use strict';

    angular
        .module('fitappApp.training')
        .controller('TrainingController', TrainingController);

    TrainingController.$inject = [];

    function TrainingController() {
        var vm = this;
        vm.slider = 0;
        vm.steps=['krotki', 'sredni', 'dlugi', 'N/A'];
    }

})();
