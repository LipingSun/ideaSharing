'use strict';

/**
 * @ngdoc function
 * @name ideaSharingApp.controller:IdeaCtrl
 * @description
 * # IdeaCtrl
 * Controller of the ideaSharingApp
 */
angular.module('ideaSharingApp')
    .controller('IdeaCtrl', function ($routeParams, ideas) {

        var ctrl = this;

        var idea = ideas.get({id: $routeParams.ideaId}, function () {

            var date = new Date(idea.datetime * 1000);

            ctrl.idea = idea;

            ctrl.idea.datetimeString = monthNames[date.getMonth()] + ' ' + date.getDate() + ', ' + date.getFullYear();

        });


    });

var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];