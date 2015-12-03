'use strict';

/**
 * @ngdoc function
 * @name ideaSharingApp.controller:IdeaCtrl
 * @description
 * # IdeaCtrl
 * Controller of the ideaSharingApp
 */
angular.module('ideaSharingApp')
    .controller('IdeaCtrl', function ($routeParams, ideas, comments) {

        var ctrl = this;

        var idea = ideas.get({id: $routeParams.ideaId}, function () {

            var date = new Date(idea.datetime * 1000);

            ctrl.idea = idea;

            ctrl.idea.datetimeString = monthNames[date.getMonth()] + ' ' + date.getDate() + ', ' + date.getFullYear();

        });

        ctrl.comments = comments.query({idea_id: $routeParams.ideaId}, function () {
            ctrl.comments.forEach(function (comment) {
                var date = new Date(comment.datetime * 1000);
                comment.datetimeString = monthNames[date.getMonth()] + ' ' + date.getDate() + ', ' + date.getFullYear();
            })
        });


        //ctrl.submitComment = function () {
        //    var newComment = new comments(ctrl.idea.id, ctrl.userComment);
        //    newComment.$save();
        //}


    });

var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];