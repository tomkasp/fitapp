(function () {
    'use strict';

    angular
        .module('fitappApp')
        .factory('StravaDataservice', StravaDataservice);

    StravaDataservice.$inject = ['$http'];

    function StravaDataservice($http) {

        var apiAddress = "/api/strava";
        var service = {
            getAuthorizationLink: getAuthorizationLink,
            getToken: getToken,
            getActivities: getActivities,
            activateStrava: activateStrava
        };

        activate();

        return service;

        function activate() {
        }


        function getAuthorizationLink() {

            return $http.get(apiAddress)
                .then(getAuthorizationDataComplete)
                .catch(getAuthorizationDataFailed);

            function getAuthorizationDataFailed(response) {

            }

            function getAuthorizationDataComplete(response) {
                return response.data;
            }
        }

        function getActivities(){
            return $http.get(apiAddress + "/activities")
                .then(function (response) {
                    return response.data;
                })
        }

        function getToken(code){
            $http.post("https://www.strava.com/oauth/token", {
                client_id: '14842',
                client_secret: '91ad80ea231505275883acc75d7c088c1cf07773',
                code: code
            })
                .then(getTokenHandler);

            function getTokenHandler(response){
                console.log(response)
            }

        }

        function activateStrava(){
            return $http.get(apiAddress + "/activate")
                .then(function (response) {
                    return response.data;
                })
        }


    }

})();

// nRequest.setClient_id("14842");
// tokenRequest.setClient_secret("91ad80ea231505275883acc75d7c088c1cf07773");
// tokenRequest.setCode(code);
