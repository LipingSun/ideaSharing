'use strict';

/**
 * @ngdoc function
 * @name ideaSharingApp.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of the ideaSharingApp
 */
angular.module('ideaSharingApp')
    .controller('HomeCtrl', function (ideas, users) {

        var ctrl = this;

        ctrl.newUser = {};

        ctrl.register = function () {
            users(newUser).$save();
        };

        ctrl.ideas = ideas.query(function () {
            ctrl.ideas.forEach(function (idea) {
                var date = new Date(idea.datetime * 1000);
                idea.datetimeString = monthNames[date.getMonth()] + ' ' + date.getDate() + ', ' + date.getFullYear();
            })
        });

    });

var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];