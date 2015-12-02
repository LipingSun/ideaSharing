'use strict';

/**
 * @ngdoc overview
 * @name ideaSharingApp
 * @description
 * # ideaSharingApp
 *
 * Main module of the application.
 */
angular
    .module('ideaSharingApp', [
        'ngResource',
        'ngRoute'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/home.html',
                controller: 'HomeCtrl',
                controllerAs: 'home'
            })
            .when('/home', {
                templateUrl: 'views/home.html',
                controller: 'HomeCtrl',
                controllerAs: 'home'
            })
            .when('/ideas/:ideaId', {
                templateUrl: 'views/idea.html',
                controller: 'IdeaCtrl',
                controllerAs: 'idea'
            })
            .when('/share-idea', {
              templateUrl: 'views/share-idea.html',
              controller: 'ShareIdeaCtrl',
              controllerAs: 'shareIdea'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
