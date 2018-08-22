package by.asrohau.iShop.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class CommandAbstract implements Command {

    protected String defindCommand(HttpServletRequest req, boolean withPage) {
        Map<String, String[]> params = req.getParameterMap();
        StringBuilder sb = new StringBuilder("FrontController?");
        for (String k : params.keySet()) {
            sb.append(k).append("=").append(params.get(k)[0]).append("&");
        }
        int end = sb.length() - 2;
        if (withPage) {
            end++;
        }
        return sb.substring(0, end);
    }

}
