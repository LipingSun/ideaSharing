'use strict';

/**
 * @ngdoc service
 * @name ideaSharingApp.ideas
 * @description
 * # ideas
 * Service in the ideaSharingApp.
 */
angular.module('ideaSharingApp')
    .service('ideas', function ($resource) {
      //return $resource('http://localhost:8080/api/ideas/:id', {}, {});
      return $resource('/api/ideas/:id', {}, {});
    });
