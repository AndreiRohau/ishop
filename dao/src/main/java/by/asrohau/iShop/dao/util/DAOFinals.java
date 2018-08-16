package by.asrohau.iShop.dao.util;

public enum DAOFinals {

    /*
    simple finals
     */
    MAX_ROWS_AT_PAGE("15"),

    /*
    Database config finals
     */
    DATABASE_SETTINGS_PATH("databaseConfig.properties"),
    DB_URL_FIXED("db.url.fixed"),
    SQL_DRIVER("sql.driver"),
    DB_CONNECTIONS("db.connections"),
    DB_URL("db.url"),
    DB_LOGIN("db.login"),
    DB_PASSWORD("db.password"),

    /*
    Messages for logs
     */
    CONNECTION_FAILED("Connection to database failed"),
    MYSQL_DRIVER_NOT_LOADED("MySQL driver is not loaded"),
    MYSQL_DRIVER_IS_LOADED("MySQL driver is loaded"),
    DATABASE_CONFIG_INIT_ERROR("Error while initializing DatabaseConfigReader"),
    ERROR_IN_DAO_METHOD_FINAL_BLOCK("Error while closing resultSet or prepared statement, or connection"),
    CANNOT_IDENTIFY_USER_BY_LOGIN_AND_PASSWORD("Can not identify User with login and password"),
    CANNOT_IDENTIFY_PRODUCT("Can not identify Product"),
    CANNOT_IDENTIFY_USER_BY_ID("Can not identify User with id"),
    CANNOT_IDENTIFY_ORDER_BY_ID("Can not identify Order with id"),
    CANNOT_IDENTIFY_PRODUCT_BY_ID("Can not identify Product with id"),
    EXCEPTION_WHILE_EXECUTING_DAO_METHOD("Error in DAO method"),
    EXCEPTION_WHILE_ROLL_BACK("Error during rollback"),

    /*
    SQL QUERIES
     */

    /*
    UserDAO queries
     */
    SAVE_USER_QUERY("INSERT INTO shop.users (login, password, role) VALUES (?,?,?)"),
    FIND_USER_BY_ID_QUERY("SELECT * FROM shop.users WHERE id = ?"),
    FIND_USER_BY_LOGIN_QUERY("SELECT * FROM shop.users WHERE login = ?"),
    FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY("SELECT * FROM shop.users WHERE login = ? AND password = ?"),
    FIND_USERS_LIMIT_QUERY("SELECT * FROM shop.users LIMIT ?,?"),
    COUNT_USERS_QUERY("SELECT COUNT(*) FROM shop.users"),
    UPDATE_USER_BY_ID_QUERY("UPDATE shop.users SET login = ?, password = ? WHERE id = ?"),
    UPDATE_PASSWORD_BY_LOGIN_AND_PASSWORD_QUERY("UPDATE shop.users SET password = ? WHERE login = ? AND password = ?"),
    DELETE_USER_BY_LOGIN_AND_PASSWORD_QUERY("DELETE FROM shop.users WHERE login = ? AND password = ?"),

    /*
    OrderDAO queries
     */
    SAVE_RESERVATION_QUERY("INSERT INTO shop.reserve (userId, productId) VALUES (?,?)"),
    FIND_RESERVE_BY_ID_QUERY("SELECT * FROM shop.reserve WHERE id = ?"),
    FIND_RESERVES_BY_USER_ID_QUERY("SELECT * FROM shop.reserve WHERE userId = ?"),
    FIND_RESERVES_BY_USER_ID_LIMIT_QUERY("SELECT * FROM shop.reserve WHERE userId = ? LIMIT ?, ?"),
    COUNT_RESERVED_QUERY("SELECT COUNT(*) FROM shop.reserve WHERE userId = ?"),
    DELETE_RESERVATION_BY_ID_QUERY("DELETE FROM shop.reserve WHERE id = ?"),
    DELETE_RESERVATIONS_BY_USER_ID_QUERY("DELETE FROM shop.reserve WHERE userId = ?"),
    SAVE_NEW_ORDER_QUERY("INSERT INTO shop.orders (user, products, address, phone, status, dateCreated) VALUES (?,?,?,?,?,?)"),
    FIND_ORDER_BY_ID_QUERY("SELECT * FROM shop.orders WHERE id = ?"),
    FIND_ORDERS_LIMIT_QUERY("SELECT * FROM shop.orders LIMIT ?, ?"),
    FIND_ORDERS_BY_STATUS_LIMIT_QUERY("SELECT * FROM shop.orders WHERE status = ? LIMIT ?, ?"),
    FIND_ORDERS_BY_USER_ID_LIMIT_QUERY("SELECT * FROM shop.orders WHERE user = ? LIMIT ?, ?"),
    FIND_ORDERS_BY_USER_ID_AND_STATUS_LIMIT_QUERY("SELECT * FROM shop.orders WHERE user = ? AND status = ? LIMIT ?, ?"),
    COUNT_ORDERS_QUERY("SELECT COUNT(*) FROM shop.orders"),
    COUNT_ORDERS_BY_STATUS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE status = ?"),
    COUNT_ORDERS_BY_USER_ID_QUERY("SELECT COUNT(*) FROM shop.orders WHERE user = ?"),
    COUNT_ORDERS_BY_USER_ID_AND_STATUS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE user = ? AND status = ?"),
    UPDATE_ORDER_STATUS_QUERY("UPDATE shop.orders SET status = ? WHERE id = ?"),
    UPDATE_ORDER_PRODUCTS_QUERY("UPDATE shop.orders SET products = ? WHERE id = ?"),
    DELETE_ORDERS_BY_USER_ID_QUERY("DELETE FROM shop.orders WHERE user = ?"),
    DELETE_ORDER_BY_ID_QUERY("DELETE FROM shop.orders WHERE id = ?"),

    /*
    ProductDAO queries
     */
    FIND_EQUAL_PRODUCT_QUERY("SELECT * FROM shop.products WHERE company = ? AND name = ? AND type = ? AND price = ?"),
    SAVE_PRODUCT_QUERY("INSERT INTO shop.products (company, name, type, price, description) VALUES (?,?,?,?,?)"),
    FIND_PRODUCT_BY_ID_QUERY("SELECT * FROM shop.products WHERE id = ?"),
    UPDATE_PRODUCT_BY_ID_QUERY("UPDATE shop.products SET company = ?, name = ?, type = ?, price = ?, description = ? WHERE id = ?"),
    DELETE_PRODUCT_BY_ID_QUERY("DELETE FROM shop.products WHERE id = ?"),
    FIND_PRODUCTS_LIMIT_QUERY("SELECT * FROM shop.products LIMIT ?, ?"),
    COUNT_PRODUCTS_QUERY("SELECT COUNT(*) FROM shop.products"),
    COUNT_PRODUCTS_COMPREHENSIVE_QUERY("SELECT COUNT(*) FROM shop.products WHERE id"),
    FIND_PRODUCTS_BY_ID_COMPREHENSIVE_QUERY("SELECT * FROM shop.products WHERE id");


    public String inString;
    DAOFinals(String inString){
        this.inString = inString;
    }
}
