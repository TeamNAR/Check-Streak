package check.streak.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import check.streak.data.Goal;
import check.streak.data.GoalMap;
import check.streak.util.ResourceResolver;

public class FSGoalManager implements GoalManager {

	private static final ObjectMapper JSON = new ObjectMapper();

	private GoalMap getUserMap() {
		GoalMap goalMap = null;
		File userFile = ResourceResolver.getUserFile();
		if (userFile.exists()) {
			try {
				goalMap = JSON.readValue(userFile, GoalMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			goalMap = new GoalMap();
		}
		return goalMap;
	}
	
	private void persistUserMap(GoalMap userMap) {
		try {
			JSON.writeValue(ResourceResolver.getUserFile(), userMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Goal getGoal(String goalId) {
		GoalMap userMap = getUserMap();
		return userMap.get(goalId);
	}

	@Override
	public void updateGoal(Goal goal) {
		GoalMap userMap = getUserMap();
		userMap.put(goal.getId(), goal);
		persistUserMap(userMap);
	}

	@Override
	public List<Goal> listAllGoals() {
		GoalMap goalMap = getUserMap();
		return new ArrayList<Goal>(goalMap.values());
	}

}
