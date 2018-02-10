(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        //service.GetAll = GetAll;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        //function GetAll() {
           // return $http.get('http://localhost:8080/api/users').then(handleSuccess, handleError('Error getting all users'));
        //}

        function GetByUsername(username) {
            return $http.get('http://localhost:8888/api/users/' + username).then(handleSuccessData, handleError('Error getting user by id'));
        }

      
        function Create(user) {
            return $http.post('http://localhost:8888/api/register', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('http://localhost:8888/api/update/' + user.username, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(username) {
            return $http.get('http://localhost:8888/api/delete/' + username).then(handleSuccess, handleError('Error deleting user'));
           /* .then(function OnSuccess(success) {
        		var response = { success: true };
               callback(response);
            },
            function onError(error){
            	var response = { success: false, message: 'Unable to delete the user' };
            	callback(response)
            });*/
        }

        // private functions
        
        function handleSuccessData(res) {
        	return res.data;          
        }
        
        function handleSuccess(res) {
        	return { success: true };          
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
