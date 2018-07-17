package by.asrohau.iShop.controller;

public enum ControllerFinals {

    /*
    simple finals
     */
    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    ROLE("role"),
    ADMIN("admin"),
    USER("user"),
    LAST_COMMAND("lastCMD"),
    ADDRESS("address"),

    /*
    urls
     */

    GO_TO_PAGE_("FrontController?command=goToPage&address="),
    GO_TO_PAGE_INDEX("FrontController?command=goToPage&address=index.jsp"),
    GO_TO_PAGE_MAIN("FrontController?command=goToPage&address=main.jsp"),

    /*
    Commands
     */
    REGISTR_USER_COMMAND("Register user command"),
    LOGINATION_COMMAND("Logination command"),
    GO_TO_PAGE_COMMAND("GoToPage command");

    public String inString;
    ControllerFinals(String inString){
        this.inString = inString;
    }
}
