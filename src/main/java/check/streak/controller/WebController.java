package check.streak.controller;

import check.streak.data.Goal;
import check.streak.data.GoalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import check.streak.data.provider.GoalManager;

@RestController
public class WebController {

	@Autowired
	private GoalManager goalManager;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	String healthCheck() {
		return "OK-CheckStreakWebController";
	}


	@RequestMapping(value = "/loadGoalsForCalendar", method = RequestMethod.GET)
	List<Goal> listAllGoals() {
		return goalManager.listAllGoals();
	}


	@RequestMapping(value = "/addGoal/{goalId}", method = RequestMethod.POST)
	Goal updateGoal(
			@PathVariable("goalId") String goalId,
			@RequestParam("name") String goalName,
			@RequestParam("startDate") String goalStartDate,
			@RequestParam("endDate") String goalEndDate,
			@RequestParam("color") String goalColor) {
		Goal goal = new Goal();
		goal.setId(goalId);
		goal.setName(goalName);
		goal.setStartDate(goalStartDate);
		goal.setEndDate(goalEndDate);
		goal.setEndDate2(" ");
		goal.setStartTime(" ");
		goal.setEndTime("");
		goal.setColor(goalColor);
		goal.setUrl("");
		goalManager.updateGoal(goal);
		return goal;
	}

	@RequestMapping(value = "/testAdd", method = RequestMethod.GET)
	Goal createUser() {
		Goal goal = new Goal();
		goal.setId("12");
		goal.setName("1222kjflkjads;gjkfslerkjgnkngvlrhgskr.nrnv");
		goal.setStartDate("");
		goal.setEndDate("");
		goal.setEndDate2(" ");
		goal.setStartTime(" ");
		goal.setEndTime("");
		goal.setColor("");
		goal.setUrl("");
		goalManager.updateGoal(goal);
		return goal;
	}

}
