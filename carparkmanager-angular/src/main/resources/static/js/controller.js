
/**
 * 
 * Controllers
 */

app.controller('carsController', function($scope, $http, $rootScope, $timeout) {
    $scope.headingTitle = "Car list";
    loadAllCars($http, $rootScope);
});




/**
 * 
 * Loading functions
 */

function loadAllCars($http, $rootScope) {
    $http.get('/pa165/rest/car/getAll').success(function(data) {
        $rootScope.cars = data;
    });
}



function clearForm($rootScope) {
    $rootScope.car = null;
    $rootScope.employee = null;
    $rootScope.admin = null;
    $rootScope.reservation = null;
}

function loadAll($http, $rootScope, objectName) {
    switch (objectName) {
        case 'car':
            return loadAllCars($http, $rootScope);
    }
}

app.service('sharedProperties', function () {
    var _car = {
        'vin': '',
        'brand': '',
        'type': '',
        'engineType': '',
        'productionYear': '',
        'kmCount': 0
    };

    this.car = _car;
});

app.run(function($rootScope, sharedProperties) {
    $rootScope.getResponseObject = function(objectName) {
        switch (objectName) {
            case 'car':
                return sharedProperties.car;
        }
    }
});

app.controller('navbarController', ['$rootScope', '$scope', '$location', '$timeout',
    function ($rootScope, $scope, $location, $timeout) {
    var $ctrl = this;

    $ctrl.logout = function () {
        console.log("Logging out " + $rootScope.currentUser);
        $rootScope.successAlert = 'User logged out.';
        $rootScope.loggedIn = false;
        $rootScope.isAdmin = false;
        $rootScope.currentUser = undefined;
    };
}]);