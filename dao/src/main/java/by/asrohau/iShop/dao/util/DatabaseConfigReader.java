package by.asrohau.iShop.dao.util;

import by.asrohau.iShop.dao.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static by.asrohau.iShop.dao.util.DAOFinals.*;

public class DatabaseConfigReader {

	private Properties properties;

	public DatabaseConfigReader() {
		try {
			load();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	private void load() throws DAOException {
		try (InputStream is = DatabaseConfigReader.class.getClassLoader().getResourceAsStream(DATABASE_SETTINGS_PATH)) {
			properties = new Properties();
			properties.load(is);
			is.close();
		} catch (IOException e) {
			throw new DAOException("Error while initializing DatabaseConfigReader", e);
		}
	}

	public String get(String name) {
		return properties.getProperty(name);
	}
}
