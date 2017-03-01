package check.streak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import check.streak.data.provider.FSGoalManager;
import check.streak.data.provider.GoalManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public GoalManager goalManager() {
		GoalManager goalManager =  new FSGoalManager();
		return goalManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
