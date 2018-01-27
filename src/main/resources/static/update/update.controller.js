(function () {
    'use strict';

    angular
        .module('app')
        .controller('UpdateProfileController', UpdateProfileController);

    UpdateProfileController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function UpdateProfileController(UserService, $location, $rootScope, FlashService) {
        var vm = this;
        vm.user = null;
               
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
        
        vm.update = update;
        
        function update() {
            vm.dataLoading = true;
            UserService.Update(vm.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Profile updation successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();
