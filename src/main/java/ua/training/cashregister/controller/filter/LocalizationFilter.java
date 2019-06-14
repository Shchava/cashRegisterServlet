package ua.training.cashregister.controller.filter;

import javax.ejb.Local;
import javax.jms.Session;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

@WebFilter("/*")
public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession();
        Locale locale;

        if(nonNull(req.getParameter("lang"))){
            locale = getLocaleFromLang((String)req.getParameter("lang"));
        }else{
            if(nonNull(session.getAttribute("Locale"))){
                locale = (Locale)session.getAttribute("Locale");
            }else{
                locale = new Locale("en","EN");
            }
        }

        session.setAttribute("Locale",locale);
        servletResponse.setLocale(locale);

        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

    private Locale getLocaleFromLang(String lang){
        Locale locale;
        if(lang.equals("UA")){
            locale = new Locale("uk","UA");
        }else{
            locale = new Locale("en","EN");
        }
        return locale;
    }
}
