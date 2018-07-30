package by.asrohau.iShop.controller;

public enum ControllerFinals {

    /*
    simple finals
     */
    MAX_ROWS_AT_PAGE("15"), //same number is at DAOFinals.java
    NEW("new"),

    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    NEW_PASSWORD("newPassword"),
    ROLE("role"),
    ADMIN("admin"),
    USER("user"),
    USER_NAME("userName"),
    LAST_COMMAND("lastCMD"),
    ADDRESS("address"),
    ERROR_MESSAGE("errorMessage"),
    IS_CHANGED("isChanged"),
    MESSAGE("message"),

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
    errors
     */
    NO_SUCH_USER("noSuchUser"),

    /*
    Commands
     */
    COMMAND("command"),
    GO_TO_PAGE_COMMAND("GoToPage command"),
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
