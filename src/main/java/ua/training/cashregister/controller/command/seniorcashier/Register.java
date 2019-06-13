package ua.training.cashregister.controller.command.seniorcashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.entity.enums.Roles;
import ua.training.cashregister.service.user.UserRegistration;

import javax.servlet.http.HttpServletRequest;

public class Register implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Roles role = Roles.valueOf(request.getParameter("role"));

        UserRegistration register = new UserRegistration();
        User user = register.createUser(login,email,password,role);
        boolean registered =  register.registerUser(user);

        request.setAttribute("registered", registered);
        return"/seniorcashier/register.jsp";
    }
}
