package by.asrohau.iShop.controller;

import by.asrohau.iShop.dao.util.DAOFinals;

public class ControllerFinals {
    
    /*
    same number is at DAOFinals.java : setts max rows at the tables/list of items per page
     */
    public static final int MAX_ROWS_AT_PAGE = DAOFinals.MAX_ROWS_AT_PAGE;


    public static final String NULL = "null";
    public static final String PAGE = "page";
    public static final String STATUS = "status";
    public static final String NEW = "new";
    public static final String ACTIVE = "active";
    public static final String CLOSED = "closed";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String ROLE = "role";
    public static final String ADMIN = "admin";
    public static final String USER = "user";
//    public static final String USER_NAME = "userName";
    public static final String LAST_COMMAND = "lastCMD";
    public static final String LAST_COMMAND_PAGE = "lastCMDneedPage";
    public static final String LOCAL = "local";
    public static final String ADDRESS = "address";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String IS_CHANGED = "isChanged";
    public static final String MESSAGE = "message";
    public static final String NO_SUCH_USER = "noSuchUser";

    /*
    urls
     */
    public static final String INDEX = "index.jsp";
    public static final String ERROR = "error.jsp";
    public static final String GO_TO_PAGE_ = "FrontController?command=goToPage&address=";
    public static final String GO_TO_PAGE_INDEX = "FrontController?command=goToPage&address=index.jsp";
    public static final String GO_TO_PAGE_MAIN = "FrontController?command=goToPage&address=main.jsp";
    public static final String GO_TO_PAGE_PROFILE = "FrontController?command=goToPage&address=profile.jsp";

    /*
    Commands
     */
    public static final String COMMAND = "command";
    public static final String GO_TO_PAGE_COMMAND = "GoToPage command";
    public static final String CHANGE_LANGUAGE = "ChangeLanguage command";
    public static final String REGISTRATION_COMMAND = "Registration command";
    public static final String LOGINATION_COMMAND = "Logination command";
    public static final String CREATE_ORDER_COMMAND = "CreateOrderCommand command";
    public static final String CHANGE_PASSWORD_COMMAND = "ChangePasswordCommand command";
    public static final String DELETE_USER_COMMAND = "DeleteUserCommand command";

}
