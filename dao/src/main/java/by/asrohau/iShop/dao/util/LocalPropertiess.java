package by.asrohau.iShop.dao.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalPropertiess {
	private Properties properties;

	public LocalPropertiess() {
		load();
	}

	private void load() {
		String path = LocalPropertiess.class.getClassLoader().getResource("resources/local.properties").getPath();
		try (FileInputStream fis = new FileInputStream(path)) {
			properties = new Properties();
			properties.load(fis);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String get(String name) {
		return properties.getProperty(name);
	}
}
