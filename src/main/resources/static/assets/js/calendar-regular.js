function addGoal() {

	var goalId = $('#input_id').val();
	var goalName = $('#input_name').val();
	var goalStartDate = $('#input_start').val();
	var goalEndDate = $('#input_end').val();
	var goalColor = $('#input_color').val();

	if (goalId) {
		$.ajax(
				{
					type : "POST",
					url  : "/addGoal/" + goalId,
					data : {
						"name" : goalName,
						"startDate" : goalStartDate,
						"endDate" : goalEndDate,
						"color" : goalColor
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs." + jqXHR.textStatus);
					}
				});
	} else {
		alert("Invalid goal Id");
	}
}
