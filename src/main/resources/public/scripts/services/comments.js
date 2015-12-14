'use strict';

/**
 * @ngdoc service
 * @name ideaSharingApp.comments
 * @description
 * # comments
 * Service in the ideaSharingApp.
 */
angular.module('ideaSharingApp')
    .service('comments', function ($resource) {
        //return $resource('http://localhost:8080/api/ideas/:idea_id/comments/:id', {}, {});
        return $resource('/api/ideas/:idea_id/comments/:id', {idea_id: '@idea_id'}, {});
    });
