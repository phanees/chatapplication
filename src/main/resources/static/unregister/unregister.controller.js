(function () {
    'use strict';

    angular
        .module('app')
        .controller('UnregisterController', UnregisterController);

    UnregisterController.$inject = ['UserService', '$rootScope', '$location', 'FlashService'];
    function UnregisterController(UserService, $rootScope, $location, FlashService) {
        var vm = this;

        vm.user = null;
        vm.deleteUser = deleteUser;
        
        initController();

        function initController() {
            loadCurrentUser();
        }

        function loadCurrentUser() {
        	UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function deleteUser() {
        	vm.dataLoading = true;
        	UserService.Delete(vm.user.username)
            .then(function (response) {
                if (response.success) {
                    FlashService.Success('User Unregistered successfully', true);
                    $location.path('/login');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }
    }

})();