package by.asrohau.iShop.dao.util;

public enum DAOFinals {

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
    DATABASE_CONFIG_INIT_ERROR("Error while initializing DatabaseConfigReader"),

    /*
    SQL QUERIES
     */

    /*
    AdminDAO queries
     */
    FIND_ADMIN_WITH_LOGIN_PASSWORD_QUERY("SELECT * FROM shop.admins WHERE login = ? AND password = ?"),
    SELECT_ALL_ADMINS_QUERY("SELECT * FROM shop.users"),
    FIND_ADMIN_WITH_ID_QUERY("SELECT * FROM shop.users WHERE id = ?"),
    UPDATE_ADMIN_QUERY("UPDATE shop.users SET login = ?, password = ? WHERE id = ?"),

    /*
    UserDAO queries
     */
    FIND_USER_WITH_LOGIN_PASSWORD_QUERY("SELECT * FROM shop.users WHERE login = ? AND password = ?"),
    FIND_USER_WITH_LOGIN_QUERY("SELECT * FROM shop.users WHERE login = ?"),
    SAVE_USER_QUERY("INSERT INTO shop.users (login, password) VALUES (?,?)"),
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
    SELECT_ALL_RESERVED_QUERY("SELECT * FROM shop.reserve WHERE user_id = ? LIMIT ?, ?"),
    COUNT_RESERVED_QUERY("SELECT COUNT(*) FROM shop.reserve WHERE user_id = ?"),
    DELETE_RESERVED_QUERY("DELETE FROM shop.reserve WHERE id = ?"),
    SELECT_ALL_RESERVED_IDS_QUERY("SELECT * FROM shop.reserve WHERE user_id = ?"),
    DELETE_ALL_RESERVED_QUERY("DELETE FROM shop.reserve WHERE user_id = ?"),
    DELETE_ALL_ORDERS_WITH_USER_ID_QUERY("DELETE FROM shop.orders WHERE user = ?"),
    SAVE_NEW_ORDER_QUERY("INSERT INTO shop.orders (user, products, address, phone, status) VALUES (?,?,?,?,?)"),
    COUNT_All_ORDERS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE status = ?"),
    SELECT_ALL_ORDERS_QUERY("SELECT * FROM shop.orders WHERE status = ? LIMIT ?, ?"),
    DELETE_NEW_ORDER_QUERY("DELETE FROM shop.orders WHERE id = ?"),
    UPDATE_SET_ORDER_STATUS_QUERY("UPDATE shop.orders SET status = ? WHERE id = ?"),
    SELECT_ORDER_WITH_ID_QUERY("SELECT * FROM shop.orders WHERE id = ?"),
    UPDATE_ORDERS_PRODUCTS_QUERY("UPDATE shop.orders SET products = ? WHERE id = ?"),
    SELECT_ALL_ACTIVE_ORDERS_QUERY("SELECT * FROM shop.orders WHERE status = \'active\' LIMIT ?, ?"),
    SELECT_ALL_SUCCESS_ORDERS_QUERY("SELECT * FROM shop.orders WHERE status = \'success\' LIMIT ?, ?"),
    COUNT_All_CLIENTS_ORDERS_QUERY("SELECT COUNT(*) FROM shop.orders WHERE user = ?"),
    SELECT_ALL_CLIENTS_ORDERS_QUERY("SELECT * FROM shop.orders WHERE user = ? LIMIT ?, ?"),

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
