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


        ctrl.submitComment = function (commentContent) {
            var newComment = {
                idea_id: ctrl.idea.id,
                content: commentContent
            };

            var comment = new comments(newComment, {idea_id: ctrl.idea.id});
            comment.$save(function () {
                ctrl.comments.push(comment)
            });
        }


    });

var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];