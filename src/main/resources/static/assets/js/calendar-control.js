var checkStreak = angular.module('checkStreak', []);

checkStreak.controller('ClndrCtrl', function ($scope, $http) {

    $scope.loadGoals = function() {
        $http.get("loadGoalsForCalendar")
        .success(function(data){
            $scope.goals = data;
        });
    }

    $scope.loadFilteredGoals = function() {
        $http.get("loadFilteredGoals")
        .success(function(data){
            $scope.filteredGoals = data;
        });
    }

    $scope.updateGoal = function(goalId) {
        $http.get("updateGoal/" + goalId)
        .success(function(data){
            $scope.loadGoals();
        });
    }

    $scope.deleteGoal = function(goalId) {
  	  $http.delete("deleteGoal/" + goalId)
  	  	.success(function(data){
  	  		$scope.loadGoals();
  	  	});
    }

  $scope.loadGoals();
  //$scope.loadFilteredGoals();
});
