package ua.training.cashregister.controller;

import ua.training.cashregister.entity.enums.Roles;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.user.UserRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/seniorcashier/register/")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Roles role = Roles.valueOf(request.getParameter("role"));

        UserRegistration register = new UserRegistration();
        User user = register.createUser(login,email,password,role);
        boolean registered =  register.registerUser(user);

        request.setAttribute("registered", registered);
        request.getRequestDispatcher("/seniorcashier/register.jsp").forward(request, response);

    }
}
