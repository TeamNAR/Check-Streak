package check.streak;

import check.streak.data.GoalMap;

import check.streak.data.provider.GoalManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

public class testList {

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

    public static void main(String [] args) throws IOException {
        GoalMap goalMap = null;
        File goalFile =  new File("/Users/Nada/Desktop/Check-Streak/src/main/resources/static/pages/events2.json");
        goalMap = JSON.readValue(goalFile, GoalMap.class);


        System.out.print(goalMap.size());
    }
}
