/**
 * Created by Liping on 12/14/15.
 */

'use strict';

angular.module('ideaSharingApp')
    .controller('NavCtrl', function ($http, users) {

        var ctrl = this;

        $http.get("/api/principal").then(function (res) {
            ctrl.user = users.get({id: res.data.id});
        });

    });
