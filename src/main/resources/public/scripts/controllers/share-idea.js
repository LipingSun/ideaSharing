'use strict';

/**
 * @ngdoc function
 * @name ideaSharingApp.controller:ShareIdeaCtrl
 * @description
 * # ShareIdeaCtrl
 * Controller of the ideaSharingApp
 */
angular.module('ideaSharingApp')
  .controller('ShareIdeaCtrl', function (ideas) {
      var ctrl = this;

      ctrl.share = function () {
          var newIdea = new ideas(ctrl.idea);
          newIdea.$save();
      }
  });
