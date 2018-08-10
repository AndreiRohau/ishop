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
    DB_URL("db.url"),
    DB_URL_FIXED("db.url.fixed"),
    DB_LOGIN("db.login"),
    DB_PASSWORD("db.password"),
    SQL_DRIVER("sql.driver"),

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
    FIND_USER_WITH_LOGIN_PASSWORD_QUERY("SELECT * FROM shop.users WHERE login = ? AND password = ?"),
    FIND_USER_WITH_LOGIN_QUERY("SELECT * FROM shop.users WHERE login = ?"),
    SAVE_USER_QUERY("INSERT INTO shop.users (login, password, role) VALUES (?,?,?)"),
    CHANGE_PASSWORD_QUERY("UPDATE shop.users SET password = ? WHERE login = ? AND password = ?"),
    DELETE_USER_QUERY("DELETE FROM shop.users WHERE login = ? AND password = ?"),
    SELECT_ALL_USERS_QUERY("SELECT * FROM shop.users LIMIT ?,?"),
    FIND_USER_WITH_ID_QUERY("SELECT * FROM shop.users WHERE id = ?"),
    UPDATE_USER_QUERY("UPDATE shop.users SET login = ?, password = ? WHERE id = ?"),
    COUNT_USERS_QUERY("SELECT COUNT(*) FROM shop.users"),

    /*
    OrderDAO queries
     */
    SAVE_RESERVATION_QUERY("INSERT INTO shop.reserve (user_id, product_id) VALUES (?,?)"),
    SAVE_NEW_ORDER_QUERY("INSERT INTO shop.orders (user, products, address, phone, status, dateCreated) VALUES (?,?,?,?,?,?)"),
    SELECT_ALL_RESERVED_QUERY("SELECT * FROM shop.reserve WHERE user_id = ? LIMIT ?, ?"),
    COUNT_RESERVED_QUERY("SELECT COUNT(*) FROM shop.reserve WHERE user_id = ?"),
    SELECT_ALL_RESERVED_IDS_QUERY("SELECT * FROM shop.reserve WHERE user_id = ?"),
    COUNT_All_ORDERS_QUERY("SELECT COUNT(*) FROM shop.orders"),
    COUNT_All_ORDERS_STATUS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE status = ?"),
    SELECT_ORDER_WITH_ID_QUERY("SELECT * FROM shop.orders WHERE id = ?"),
    UPDATE_SET_ORDER_STATUS_QUERY("UPDATE shop.orders SET status = ? WHERE id = ?"),
    UPDATE_ORDERS_PRODUCTS_QUERY("UPDATE shop.orders SET products = ? WHERE id = ?"),
    DELETE_RESERVED_QUERY("DELETE FROM shop.reserve WHERE id = ?"),
    DELETE_ALL_RESERVED_QUERY("DELETE FROM shop.reserve WHERE user_id = ?"),
    DELETE_ALL_ORDERS_WITH_USER_ID_QUERY("DELETE FROM shop.orders WHERE user = ?"),
    DELETE_ORDER_BY_ID_QUERY("DELETE FROM shop.orders WHERE id = ?"),
    SELECT_ALL_ORDERS_QUERY("SELECT * FROM shop.orders LIMIT ?, ?"),
    SELECT_ALL_ORDERS_STATUS_QUERY("SELECT * FROM shop.orders WHERE status = ? LIMIT ?, ?"),
    COUNT_USER_ORDERS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE user = ?"),
    COUNT_USER_ORDERS_BY_STATUS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE user = ? AND status = ?"),
    SELECT_USER_ORDERS_QUERY("SELECT * FROM shop.orders WHERE user = ? LIMIT ?, ?"),
    SELECT_USER_ORDERS_BY_STATUS_QUERY("SELECT * FROM shop.orders WHERE user = ? AND status = ? LIMIT ?, ?"),

    /*
    ProductDAO queries
     */
    FIND_EQUAL_PRODUCT_QUERY("SELECT * FROM shop.products WHERE company = ? AND name = ? AND type = ? AND price = ?"),
    ADD_NEW_PRODUCT_QUERY("INSERT INTO shop.products (company, name, type, price, description) VALUES (?,?,?,?,?)"),
    FIND_PRODUCT_WITH_ID_QUERY("SELECT * FROM shop.products WHERE id = ?"),
    UPDATE_PRODUCT_QUERY("UPDATE shop.products SET company = ?, name = ?, type = ?, price = ?, description = ? WHERE id = ?"),
    DELETE_PRODUCT_QUERY("DELETE FROM shop.products WHERE id = ?"),
    SELECT_ALL_PRODUCTS_QUERY("SELECT * FROM shop.products LIMIT ?, ?"),
    COUNT_PRODUCTS_QUERY("SELECT COUNT(*) FROM shop.products"),
    COUNT_PRODUCTS_COMPREHENSIVE_QUERY("SELECT COUNT(*) FROM shop.products WHERE id"),
    SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY("SELECT * FROM shop.products WHERE id");


    public String inString;
    DAOFinals(String inString){
        this.inString = inString;
    }
}
