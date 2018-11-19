angular.module('salaryApp', [])
    .controller('salaryController', function ($scope, $http) {
        $scope.selectedCurrency = "";
        $scope.currency = "";
        $scope.dailyGrossAmount = "";
        $http.get('/countries')
            .then(function (response) {
                $scope.countriesList = response.data;
            });
        $scope.calculateSalary = function (grossSalary, selectedCurrency) {
            $http.get('/salary', {
                params: {
                    selectedCurrency: selectedCurrency,
                    grossSalary: grossSalary
                }
            }).then(function (response) {
                $scope.nettSalary = response.data;
            });
        };
    });


