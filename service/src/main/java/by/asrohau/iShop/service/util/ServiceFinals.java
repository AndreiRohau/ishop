package by.asrohau.iShop.service.util;

public enum ServiceFinals {

        /*
        Messages for logs
         */
        CONNECTION_FAILED("Connection to database failed"),
        MYSQL_DRIVER_NOT_LOADED("MySQL driver is not loaded"),
        DATABASE_CONFIG_INIT_ERROR("Error while initializing DatabaseConfigReader"),
        ERROR_IN_DAO_METHOD_FINAL_BLOCK("Error while closing resultSet or prepared statement, or connection"),
        CANNOT_IDENTIFY_USER_BY_LOGIN_AND_PASSWORD("Can not identify User with login and password");

        public String inString;
        ServiceFinals(String inString){
            this.inString = inString;
        }
    }

