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
            .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl',
                controllerAs: 'about'
            })
            .when('/idea', {
                templateUrl: 'views/idea.html',
                controller: 'IdeaCtrl',
                controllerAs: 'idea'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
