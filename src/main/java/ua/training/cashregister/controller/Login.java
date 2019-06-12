package ua.training.cashregister.controller;

import ua.training.cashregister.service.user.UserAuthentification;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserAuthentification auth = new UserAuthentification();
        String login = request.getParameter("username");
        String password = request.getParameter("password");


        if (auth.checkAuthority(login, password)) {
            auth.findUser(login).ifPresent(user -> {
                request.getSession().setAttribute("userId",user.getId());
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("Role", user.getRole());
            });
            response.sendRedirect("/index.jsp");
        } else {
            response.sendRedirect("/login");
        }
    }
}
