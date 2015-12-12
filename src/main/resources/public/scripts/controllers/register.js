/**
 * Created by Liping on 12/11/15.
 */

'use strict';

angular.module('ideaSharingApp')
    .controller('RegisterCtrl', function (users) {

        var ctrl = this;

        ctrl.register = function (newUser) {
            (new users(newUser)).$save();
        };

    });
