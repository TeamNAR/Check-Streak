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

  $scope.loadGoals();
  $scope.loadFilteredGoals();
});
