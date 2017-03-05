package check.streak.util;

import java.io.File;

public class ResourceResolver {
	public static File getGoalFile() {
		ClassLoader classLoader = ResourceResolver.class.getClassLoader();
		File file = new File(classLoader.getResource("static/events.json").getFile());

		return file;
	}
}
