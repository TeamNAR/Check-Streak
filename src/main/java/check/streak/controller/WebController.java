package check.streak.controller;

import check.streak.data.Goal;
import check.streak.data.GoalMap;
import check.streak.util.ResourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

	@RequestMapping(value = "/loadFilteredGoals", method = RequestMethod.GET)
	List<Goal> listFilteredGoals() {
		return goalManager.listFilteredGoals();
	}


	@RequestMapping(value = "/addGoal/{goalId}", method = RequestMethod.POST)
	Goal addGoal(
			@PathVariable("goalId") String goalId,
			@RequestParam("name") String goalName,
			@RequestParam("startDate") String goalStartDate,
			@RequestParam("endDate") String goalEndDate,
			@RequestParam("color") String goalColor) {
		Goal goal = new Goal();
		goal.setId(goalId);
		goal.setName(goalName);
		goal.setStartDate(goalStartDate);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String s = dateFormat.format(date);

		goal.setEndDate(goalStartDate);
		goal.setEndDate2(goalEndDate);
		goal.setStartTime("");
		goal.setEndTime("");
		goal.setColor("#"+goalColor);
		goal.setUrl("");
		goalManager.updateGoal(goal);
		return goal;
	}

	@RequestMapping(value = "/updateGoal/{goalId}", method = RequestMethod.GET)
	Goal updateGoal(
			@PathVariable("goalId") String goalId) {
		Goal goal = goalManager.getGoal(goalId);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String s = dateFormat.format(date);

		goal.setEndDate(s);
		goalManager.updateGoal(goal);
		return goal;
	}

	@RequestMapping(value = "/getEventsPath", method = RequestMethod.GET)
	String getEventsPath() throws IOException {

		File file1 = ResourceResolver.getGoalFile();
/*		File file2 = ResourceResolver.getGoalFile2();
		FileChannel src = new FileInputStream(file1).getChannel();
		FileChannel dest = new FileOutputStream(file2).getChannel();
		dest.transferFrom(src, 0, src.size());*/
		String content = new Scanner(file1).useDelimiter("\\Z").next();

		return content;
	}
}
