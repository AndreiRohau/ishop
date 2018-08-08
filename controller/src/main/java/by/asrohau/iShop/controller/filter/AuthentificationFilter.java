package by.asrohau.iShop.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class AuthentificationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getParameter(COMMAND.inString).matches("logout") ||
                request.getParameter(COMMAND.inString).matches("logination") ||
                request.getParameter(COMMAND.inString).matches("registration") ||
                request.getParameter(COMMAND.inString).matches("changeLanguage") ||
                request.getSession().getAttribute(ROLE.inString) != null) {

            chain.doFilter(req, res);
        } else {
            response.sendRedirect(INDEX.inString);
        }
    }

    @Override
    public void destroy() {

    }
}
