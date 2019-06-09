package ua.training.cashregister.controller;

import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.user.Authentification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login/")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authentification auth = new Authentification();
        String login = request.getParameter("username");
        String password = request.getParameter("password");


        if(auth.checkAuthority(login,password)) {
            User user = auth.findUser(login).get();
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("Role", user.getRole().name());
            response.sendRedirect("/hello");
        }else{
            response.sendRedirect("/login");
        }
    }
}
