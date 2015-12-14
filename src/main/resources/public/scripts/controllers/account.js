'use strict';

/**
 * @ngdoc function
 * @name ideaSharingApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the ideaSharingApp
 */
angular.module('ideaSharingApp')
    .controller('AccountCtrl', function (users, $http) {

        var ctrl = this;

        $http.get("/api/principal").then(function (res) {
            ctrl.user = users.get({id: res.data.id});
        });

    });
