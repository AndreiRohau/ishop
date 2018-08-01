package by.asrohau.iShop.controller;

import by.asrohau.iShop.dao.util.DAOFinals;

public enum ControllerFinals {

    /*
    These are for parser
     */
    WEBNAME(""), CLASSNAME(""), COMMANDS(""),
    /*
    simple finals
     */
    /*
    same number is at DAOFinals.java
     */
    MAX_ROWS_AT_PAGE(DAOFinals.MAX_ROWS_AT_PAGE.inString),
    /*
    path for creation commmand instances
    */
    COMMAND_PATH("by.asrohau.iShop.controller.command.impl."),
    /*
    path of the command.xml
    */
    COMMANDS_XML("Commands.xml"),
    NEW("new"),
    NULL("null"),

    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    NEW_PASSWORD("newPassword"),
    ROLE("role"),
    ADMIN("admin"),
    USER("user"),
    USER_NAME("userName"),
    LAST_COMMAND("lastCMD"),
    LOCAL("local"),
    ADDRESS("address"),
    ERROR_MESSAGE("errorMessage"),
    IS_CHANGED("isChanged"),
    MESSAGE("message"),
    NO_SUCH_USER("noSuchUser"),

    /*
    urls
     */
    INDEX("index.jsp"),
    ERROR("error.jsp"),
    GO_TO_PAGE_("FrontController?command=goToPage&address="),
    GO_TO_PAGE_INDEX("FrontController?command=goToPage&address=index.jsp"),
    GO_TO_PAGE_MAIN("FrontController?command=goToPage&address=main.jsp"),
    GO_TO_PAGE_PROFILE("FrontController?command=goToPage&address=profile.jsp"),

    /*
    Commands
     */
    COMMAND("command"),
    GO_TO_PAGE_COMMAND("GoToPage command"),
    CHANGE_LANGUAGE("ChangeLanguage command"),
    REGISTRATION_COMMAND("Registration command"),
    LOGINATION_COMMAND("Logination command"),
    CREATE_ORDER_COMMAND("CreateOrderCommand command"),
    CHANGE_PASSWORD_COMMAND("ChangePasswordCommand command"),
    DELETE_USER_COMMAND("DeleteUserCommand command");

    public String inString;
    ControllerFinals(String inString){
        this.inString = inString;
    }
}
