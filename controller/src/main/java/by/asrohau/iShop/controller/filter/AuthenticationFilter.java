package by.asrohau.iShop.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.asrohau.iShop.controller.ControllerFinals.*;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getParameter(COMMAND).matches("logout") ||
                request.getParameter(COMMAND).matches("logination") ||
                request.getParameter(COMMAND).matches("registration") ||
                request.getParameter(COMMAND).matches("changeLanguage") ||
                request.getParameter(COMMAND).matches("goToPage") ||
                request.getSession().getAttribute(ROLE) != null) {

            chain.doFilter(req, res);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
