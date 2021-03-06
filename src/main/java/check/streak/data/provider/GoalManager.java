package check.streak.data.provider;

import java.util.List;
import check.streak.data.Goal;
import check.streak.data.GoalMap;

public interface GoalManager {

	public Goal getGoal(String goalId);

	public void updateGoal(Goal goal);

	public void deleteGoal(String goalId);

	public List<Goal> listAllGoals();

	public List<Goal> listFilteredGoals();

}
