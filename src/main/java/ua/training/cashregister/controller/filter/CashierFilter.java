package ua.training.cashregister.controller.filter;

import ua.training.cashregister.entity.enums.Roles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/cashier/*")
public class CashierFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (nonNull(session)
                && nonNull(session.getAttribute("login"))
                && nonNull(session.getAttribute("Role"))
                && (session.getAttribute("Role") == Roles.CASHIER
                || session.getAttribute("Role") == Roles.SENIOR_CASHIER)){

            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
