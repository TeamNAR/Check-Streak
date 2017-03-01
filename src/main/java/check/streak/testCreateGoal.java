package check.streak;

import check.streak.data.Goal;
import check.streak.data.GoalMap;
import check.streak.data.provider.FSGoalManager;
import check.streak.data.provider.GoalManager;
import check.streak.util.ResourceResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nada on 2/28/17.
 */
public class testCreateGoal {

    private static final ObjectMapper JSON = new ObjectMapper();

    private static GoalMap getGoalMap() {
        GoalMap goalMap = null;
        File goalFile =  new File("/Users/Nada/Desktop/Check-Streak/src/main/resources/static/pages/events2.json");
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

    public static void updateGoal(Goal goal) {
        GoalMap goalMap = getGoalMap();
        goalMap.put(goal.getId(), goal);
        persistGoalMap(goalMap);
    }

    private static void persistGoalMap(GoalMap userMap) {
        try {
            JSON.writeValue(ResourceResolver.getGoalFile(), userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) throws IOException {
        GoalManager goalManager;

        GoalMap nada = getGoalMap();

        System.out.print(nada.size());

        Goal goal = new Goal();
        goal.setId("test");
        goal.setName("NAdaaaaaaaaaaa");
        goal.setStartDate("");
        goal.setEndDate("");
        goal.setEndDate2(" ");
        goal.setStartTime(" ");
        goal.setEndTime("");
        goal.setColor("");
        goal.setUrl("");

        updateGoal(goal);

    }
}
