package by.asrohau.iShop.dao.util;

public enum DAOFinals {

    DATABASE_SETTINGS_PATH("databaseConfig.properties"),
    DB_URL("db.url"),
    DB_URL_FIXED("db.url.fixed"),
    DB_LOGIN("db.login"),
    DB_PASSWORD("db.password"),
    SQL_DRIVER("sql.driver"),

    CONNECTION_FAILED("Connection to database failed"),
    MYSQL_DRIVER_NOT_LOADED("MySQL driver is not loaded"),
    DATABASE_CONFIG_INIT_ERROR("Error while initializing DatabaseConfigReader");

    public String inString;
    DAOFinals(String inString){
        this.inString = inString;
    }
}
