package by.asrohau.iShop.dao.databaseConnectionImpl;

import by.asrohau.iShop.dao.DatabaseConfigReader;
import by.asrohau.iShop.dao.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static by.asrohau.iShop.dao.DAOFinals.DATABASE_SETTINGS_PATH;

public class DatabaseConfigReaderImpl implements DatabaseConfigReader{

	private Properties properties;

	public DatabaseConfigReaderImpl() {
		try {
			load();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Loads properties' file</p>
	 * @throws DAOException module exception
	 */
	private void load() throws DAOException {
		try (InputStream is = DatabaseConfigReaderImpl.class.getClassLoader().getResourceAsStream(DATABASE_SETTINGS_PATH)) {
			properties = new Properties();
			properties.load(is);
			is.close();
		} catch (IOException e) {
			throw new DAOException("Error while initializing DatabaseConfigReader", e);
		}
	}

	@Override
	public String get(String name) {
		return properties.getProperty(name);
	}
}
