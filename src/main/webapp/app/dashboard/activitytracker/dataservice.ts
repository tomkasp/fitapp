namespace dashboard {

    class DataserviceTest {

        static $inject = ["$http"];

        constructor(private $http: ng.IHttpService) {

        }

        check(): ng.IPromise<ng.IHttpPromiseCallbackArg<dashboard.ActivitiesModel[]>> {
            return this.$http.get("/api/activities")
                .then(function (data) {
                    return data;
                })
        }
    }
    angular
        .module("fitappApp.dashboard")
        .service("DataserviceTest", DataserviceTest);
}
