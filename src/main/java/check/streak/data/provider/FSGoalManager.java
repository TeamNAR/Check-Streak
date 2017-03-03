package check.streak.data.provider;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import check.streak.data.Goal;
import check.streak.data.GoalMap;
import check.streak.util.ResourceResolver;
import com.jayway.jsonpath.Filter;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static com.jayway.jsonpath.JsonPath.parse;

public class FSGoalManager implements GoalManager {

	private static final ObjectMapper JSON = new ObjectMapper();

	private GoalMap getGoalMap() {
		GoalMap goalMap = null;
		File goalFile =  ResourceResolver.getGoalFile();
		//File goalFile =  new File("/Users/Nada/Desktop/Check-Streak/src/main/resources/static/events.json");
		if (goalFile.exists()) {
			try {
				goalMap = JSON.readValue(goalFile, GoalMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			goalMap = new GoalMap();
		}
		return goalMap;
	}
	
	private void persistGoalMap(GoalMap userMap) {
		try {
			JSON.writeValue(ResourceResolver.getGoalFile(), userMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Goal getGoal(String goalId) {
		GoalMap goalMap = getGoalMap();
		return goalMap.get(goalId);
	}

	@Override
	public void updateGoal(Goal goal) {
		GoalMap goalMap = getGoalMap();
		goalMap.put(goal.getId(), goal);
		persistGoalMap(goalMap);
	}

	@Override
	public void deleteGoal(String userId) {
		GoalMap goalMap = getGoalMap();
		goalMap.remove(userId);
		persistGoalMap(goalMap);
	}

	@Override
	public List<Goal> listAllGoals() {
		GoalMap goalMap = getGoalMap();
		return new ArrayList<Goal>(goalMap.values());
	}

	public FSGoalManager() {
	}

	@Override
	public List<Goal> listFilteredGoals() {
		File goalFile =  new File("/Users/rashaalghofaili/Desktop/NAR/Check-Streak/src/main/resources/static/events.json");
		//File goalFile =  ResourceResolver.getGoalFile();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		String s = dateFormat.format(date);
		//System.out.println(s);

		Filter cheapFictionFilter = filter(where("startdate").lte(s));

		List<Goal> goalMap  = null;
		try {
			goalMap = parse(goalFile).read("$.monthly[?]", cheapFictionFilter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return goalMap;
	}
}