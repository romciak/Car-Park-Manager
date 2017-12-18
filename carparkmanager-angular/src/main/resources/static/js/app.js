'use strict';

var app = angular.module('app', ['ngRoute', 'ngResource']);

app.config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/pa165/employee/cars',{
            templateUrl: '/templates/employee-cars.html',
            controller: 'carsController'
        })
        /*.when('/pa165', {
            templateUrl: '/templates/login.html',
            controller: 'loginController'
        })*/
        .otherwise({
            redirectTo: '/pa165'
        });
});

app.run(['$rootScope', '$location', '$http', '$timeout', function ($rootScope, $location, $http, $timeout) {
    $rootScope.loggedIn = false;
    $rootScope.isAdmin = false;

    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };

    // triggered on every location change
    $rootScope.$on('$locationChangeStart', function (event, next, current) {

        // redirect after page refresh
        if (!isLoggedIn()) {
            $location.path('/pa165');
        }

        // if the user is not logged in and trying to access a restricted page, switch to login page
        if ((isOnRestrictedPage() && !isAdmin()) || (!isOnRestrictedPage() && isAdmin())) {
            $location.path('/pa165');
            $rootScope.loggedIn = false;
            $rootScope.isAdmin = false;
            $rootScope.currentUser = undefined;
        }

        function isAdmin() {
            return $rootScope.isAdmin;
        }

        function isOnRestrictedPage() {
            return $location.path().indexOf('admin') > 0;
        }

        function isLoggedIn() {
            return $rootScope.loggedIn;
        }
    });
}]);