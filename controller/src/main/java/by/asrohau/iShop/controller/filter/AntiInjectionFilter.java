package by.asrohau.iShop.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AntiInjectionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        StringBuilder sb = new StringBuilder();
        Map<String, String[]> params = request.getParameterMap();
        for (String [] v : params.values()) {
            sb.append(v[0]);
        }
        System.out.println(sb.toString());
        if (sb.toString().trim().matches("[<|>]")) {
            System.out.println("script");
            System.out.println("script");
            System.out.println("script");
            System.out.println("script");
            System.out.println("script");
            res.sendRedirect("WEB-INF/errors/antiInjection.jsp");
        } else {
            System.out.println("normal logic");
            System.out.println("normal logic");
            System.out.println("normal logic");
            System.out.println("normal logic");
            System.out.println("normal logic");
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

}
