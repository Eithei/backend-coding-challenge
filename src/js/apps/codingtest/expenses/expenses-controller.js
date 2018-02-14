"use strict";

/******************************************************************************************

 Expenses controller

 ******************************************************************************************/

let app = angular.module("expenses.controller", []);

app.controller("ctrlExpenses", ["$rootScope", "$scope", "config", "restalchemy", function ExpensesCtrl($rootScope, $scope, $config, $restalchemy) {
    // Update the headings
    $rootScope.mainTitle = "Expenses";
    $rootScope.mainHeading = "Expenses";

    // Update the tab sections
    $rootScope.selectTabSection("expenses", 0);

    let restExpenses = $restalchemy.init({root: $config.apiroot}).at("expenses");
    let restCurrencies = $restalchemy.init({root: $config.apiroot}).at("currencies");

    $scope.dateOptions = {
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
    };

    let loadExpenses = function () {
        // Retrieve a list of expenses via REST
        restExpenses.get().then(function (expenses) {
            $scope.expenses = expenses;
        });
    };

    $scope.clearExpense = function () {
        $scope.newExpense = {};
    };

    // Check if there are any alphabetic characters in amount.
    // (Example 12.00 => false; 12.00 EUR => true;)
    function containsCurrencyString(amount) {
        return /([A-Za-z])\w+/.test(amount);
    }

    // Return amount without non-alphabet characters.
    // (Example 12.00 EUR => EUR)
    function getCurrencyType(amount) {
        return amount.replace(/([^A-Za-z])/g, '');
    }

    // Return amount without alphabet characters.
    // (Example 12.00 EUR => 12.00)
    function getAmountWithoutCurrency(amount) {
        return amount.replace(/([A-Za-z])/g, '');
    }

    // Calculate amount and VAT from given currency rate to GBP.
    function convertToGBP(amount, result) {
        let amountInGBP = amount / result;
        $scope.newExpense.amount = precisionRound(amountInGBP, 2).toFixed(2);
        $scope.newExpense.vat = getVat($scope.newExpense.amount);
    }

    function persistExpense() {
        restExpenses.post($scope.newExpense).then(function () {
            // Reload new expenses list
            loadExpenses();
            $scope.clearExpense();
        });
    }

    // Get currency exchange rate, convert Expense to GBP, and persist.
    $scope.saveExpense = function () {
        if ($scope.expensesform.$valid) {
            if (containsCurrencyString($scope.newExpense.amount)) {
                let currency = getCurrencyType($scope.newExpense.amount);
                let amount = getAmountWithoutCurrency($scope.newExpense.amount);
                restCurrencies.get({currency: currency})
                    .then(function (result) {
                        convertToGBP(amount, result);
                        persistExpense();
                    })
            } else {
                persistExpense();
            }
        }
    };

    // HMRC allows both rounding up and down.
    // Source: https://www.ukbusinessforums.co.uk/threads/vat-round-up-or-down.164941/
    // Availability: 10th of February, 2018
    function precisionRound(number, precision) {
        let factor = Math.pow(10, precision);
        return Math.round(number * factor) / factor;
    }

    function calculateVat(amount) {
        let unroundedVat = amount * 0.2;
        return precisionRound(unroundedVat, 2).toFixed(2);
    }

    function getVat(rawAmount) {
        if (containsCurrencyString(rawAmount)) {
            let currency = getCurrencyType(rawAmount);
            let amount = getAmountWithoutCurrency(rawAmount);
            return calculateVat(amount) + ' ' + currency;
        } else {
            return calculateVat(rawAmount)
        }
    }

    $scope.setVat = function () {
        $scope.newExpense.vat = getVat($scope.newExpense.amount);
    };

    // Initialise scope variables
    loadExpenses();
    $scope.clearExpense();
}]);
