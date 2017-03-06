package check.streak.util;

import java.io.File;

public class ResourceResolver {
	public static File getGoalFile2() {
		ClassLoader classLoader = ResourceResolver.class.getClassLoader();
		File file = new File(classLoader.getResource("static/events.json").getFile());

		return file;
	}

	private static final String BASE_DIR = System.getProperty("user.home") + "/check.streak";
	public static File getGoalFile() {
		File file = new File(BASE_DIR + "/" + "events.json");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}
}
