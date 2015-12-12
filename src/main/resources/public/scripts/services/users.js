'use strict';

/**
 * @ngdoc service
 * @name ideaSharingApp.users
 * @description
 * # users
 * Service in the ideaSharingApp.
 */
angular.module('ideaSharingApp')
    .service('users', function ($resource) {
      //return $resource('http://localhost:8080/api/users/:id', {}, {});
      return $resource('/api/users/:id', {}, {});
    });
