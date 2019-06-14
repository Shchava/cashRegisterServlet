package ua.training.cashregister.controller.filter;

import javax.ejb.Local;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter("/*")
public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setLocale(new Locale("en_US"));

        Locale uLocale = new Locale.Builder().setLanguage("uk").setRegion("UA").build();
        servletResponse.setLocale(uLocale);
        HttpServletRequest req =  (HttpServletRequest)servletRequest;
        req.setAttribute("loc",uLocale);
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");




        ResourceBundle rs = ResourceBundle.getBundle("messages",uLocale);

        System.out.println(rs.getString("login.password"));



        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
