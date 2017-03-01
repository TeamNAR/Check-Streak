// This is the version used for the HTML home-ajs.html with AngularJS
// This is the trending technology
var checkStreak = angular.module('checkStreak', []);

checkStreak.controller('ClndrCtrl', function ($scope, $http) {

  $scope.loadGoals = function() {
	   $http.get("getGoalsList")
	   	.success(function(data){
	   		$scope.goals = data;
	   });

  $scope.loadGoals();

});
