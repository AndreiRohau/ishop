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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getParameter(COMMAND).matches("logout") ||
                req.getParameter(COMMAND).matches("logination") ||
                req.getParameter(COMMAND).matches("registration") ||
                req.getParameter(COMMAND).matches("changeLanguage") ||
                req.getParameter(COMMAND).matches("goToPage") ||
                req.getSession().getAttribute(ROLE) != null) {

            chain.doFilter(request, response);
        } else {
            res.sendRedirect("index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
