package by.asrohau.iShop.dao.util;

import by.asrohau.iShop.dao.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfigReader {

	private Properties properties;

	public DatabaseConfigReader() throws DAOException {
		load();
	}

	private void load() throws DAOException {
		try (InputStream is = DatabaseConfigReader.class.getClassLoader().getResourceAsStream(DAOFinals.DATABASE_SETTINGS_PATH.inString)) {
			properties = new Properties();
			properties.load(is);
		} catch (IOException e) {
			throw new DAOException(DAOFinals.DATABASE_CONFIG_INIT_ERROR.inString, e);
		}
	}

	public String get(String name) {
		return properties.getProperty(name);
	}
}
