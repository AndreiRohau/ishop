package by.asrohau.iShop.controller;

public enum ControllerFinals {

    /*
    simple finals
     */
    MAX_ROWS_AT_PAGE("15"), //same number is at DAOFinals.java

    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    ROLE("role"),
    ADMIN("admin"),
    USER("user"),
    USER_NAME("userName"),
    LAST_COMMAND("lastCMD"),
    ADDRESS("address"),
    ERROR_MESSAGE("errorMessage"),

    /*
    urls
     */

    INDEX("index.jsp"),
    ERROR("error.jsp"),
    GO_TO_PAGE_("FrontController?command=goToPage&address="),
    GO_TO_PAGE_INDEX("FrontController?command=goToPage&address=index.jsp"),
    GO_TO_PAGE_MAIN("FrontController?command=goToPage&address=main.jsp"),

    /*
    errors
     */
    NO_SUCH_USER("noSuchUser"),

    /*
    Commands
     */
    COMMAND("command"),
    REGISTRATION_COMMAND("Registration command"),
    LOGINATION_COMMAND("Logination command"),
    GO_TO_PAGE_COMMAND("GoToPage command");

    public String inString;
    ControllerFinals(String inString){
        this.inString = inString;
    }
}
