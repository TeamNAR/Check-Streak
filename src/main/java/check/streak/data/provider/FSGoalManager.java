package check.streak.data.provider;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import check.streak.data.Goal;
import check.streak.data.GoalMap;
import check.streak.util.ResourceResolver;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.jsonpath.Filter;
import net.minidev.json.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.Days;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static com.jayway.jsonpath.JsonPath.parse;

public class FSGoalManager implements GoalManager {

	private static final ObjectMapper JSON = new ObjectMapper();

	public FSGoalManager() {
	}

	private List<Goal> getGoalMap() {
		List<Goal> goalMap = null;
		File goalFile =  ResourceResolver.getGoalFile();
		if (goalFile.exists()) {
			// read the file and convert the JSON content
			// to the UserMap object
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode response = mapper.readTree(goalFile).path("monthly");
				CollectionType collectionType =  TypeFactory
						.defaultInstance()
						.constructCollectionType(List.class, Goal.class);

				goalMap = mapper.reader(collectionType).readValue(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			goalMap = new ArrayList<Goal>();
		}
		return goalMap;
	}
	
	private void persistGoalMap(JSONObject goalMap) {
		File goalFile =  ResourceResolver.getGoalFile();
		try {
			JSON.writeValue(goalFile, goalMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Goal getGoal(String goalId) {
		List<Goal> goalMap = getGoalMap();
		Goal goal = null;
		for(Goal myGoal : goalMap){
			if(myGoal.getId().equals(goalId))
				goal = myGoal;
		}
		return goal;
	}

	@Override
	public void updateGoal(Goal goal) {
		List<Goal> goalMap = getGoalMap();

		Goal removeMe = null;

		for(Goal myGoal : goalMap){
			if(myGoal.getId().equals(goal.getId()))
				removeMe = myGoal;
		}

		goalMap.remove(removeMe);
		goalMap.add(goal);

		JSONObject JSONGoal = new JSONObject();
		JSONGoal.put("monthly", goalMap);

		persistGoalMap(JSONGoal);
	}

	@Override
	public void deleteGoal(String userId) {
		//GoalMap goalMap = getGoalMap();
		//goalMap.remove(userId);
		//persistGoalMap(goalMap);
	}

	@Override
	public List<Goal> listAllGoals() {
		List<Goal> goalMap = getGoalMap();
		return goalMap;
	}

	@Override
	public List<Goal> listFilteredGoals() {
		File goalFile =  ResourceResolver.getGoalFile();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(new Date());

		Filter cheapFictionFilter = filter(where("startdate").lt(today).and("enddate2").gte(today).and("enddate").lt(today));

		List<Goal> goalMap  = null;
		try {
			goalMap = parse(goalFile).read("$.monthly[?]", cheapFictionFilter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return goalMap;
	}
}