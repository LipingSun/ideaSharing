'use strict';

/**
 * @ngdoc function
 * @name ideaSharingApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the ideaSharingApp
 */
angular.module('ideaSharingApp')
    .controller('AccountCtrl', function (users) {

        var ctrl = this;

        var user = users.get({id: "1002"}, function () {
            console.log(user);

            ctrl.user = user;

        });



        //ctrl.submitComment = function () {
        //    var newComment = new comments(ctrl.idea.id, ctrl.userComment);
        //    newComment.$save();
        //}


    });
