/**
 * Created by macquest on 6/24/14.
 */

var app = angular.module('generatorApp', []);

var baseURL = "http://localhost:3000";

app.controller('MainCtrl', ['$scope', '$http', function ($scope, $http) {

	$scope.pages = [];
	$scope.cpage = false;
	$scope.postForm = function(form) {
		$http.put(baseURL + "/pages-list", form)
			.success(function (data) {
			   $scope.pages = data.pages;
				$scope.cpage = false;

				console.log(data.pages);

			})
			.error(function (err) {
				alert("data erroorrr");
			})
	}

	$scope.getPage = function ( num ) {
		$scope.cpage = true;
		$scope.currentPage = num;
		console.log(num);
	}
}])
