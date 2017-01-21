(function () {
    'use strict';

    angular
        .module('fitappApp.dashboard')
        .controller('ActivityStatisticController', ActivityStatisticController);


    function ActivityStatisticController() {
        var vm = this;

        vm.data = [60, 30, 10];
        vm.data2 = [16, 15, 15, 14, 17, 18, 16, 15, 16];
        vm.data3 = [16, 15, 15, 14, 17, 18, 16, 15, 16];
        vm.data4 = [16, 15, 15, 14, 17, 18, 16, 15, 16];

        vm.options = {
            type: 'pie',
            height: 19,
            sliceColors: ['#23b7e5', '#fff', '#fad733']
        };

        vm.options2 = {
            type: 'bar',
            height: 19,
            barWidth: 4,
            barSpacing: 2,
            barColor: '#27c24c'
        };

        vm.options3 = {
            type: 'line',
            height: 19,
            width: 60,
            lineColor: '#7266ba',
            fillColor: '#fff'
        };

        vm.options4 = {type: 'discrete', height: 19, width: 60, lineColor: '#27c24c'};
    }

})();


// ng-init="data2=[ 60,30,10 ]" ui-jq="sparkline" ui-options="{{data2}},
