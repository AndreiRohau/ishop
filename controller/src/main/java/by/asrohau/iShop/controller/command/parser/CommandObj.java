package by.asrohau.iShop.controller.command.parser;

/**
 * Created by rohau.andrei on 23-Feb-18.
 */
public class CommandObj {
    private String webname;
    private String classname;

    public CommandObj(){
    }

    public String getWebname() {
        return webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
