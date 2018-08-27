package by.asrohau.iShop.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractCommand implements Command {

    protected String defineCommand(HttpServletRequest req, boolean withPage) {
        /*
        name and value as Map<String, String[]>
         */
        Map<String, String[]> params = req.getParameterMap();
        /*
        FrontController is only servlet
         */
        StringBuilder sb = new StringBuilder("FrontController?");
        /*
        creating a line of [name=value&]
         */
        for (String k : params.keySet()) {
            sb.append(k).append("=").append(params.get(k)[0]).append("&");
        }
        /*
        if withPage = false, then [...page=]
         */
        int end = sb.length() - 2;
        /*
        else [...page=?], not ?, but any number according to the 'web' value
         */
        if (withPage) {
            end++;
        }
        return sb.substring(0, end);
    }

}
