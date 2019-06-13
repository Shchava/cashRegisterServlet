package ua.training.cashregister.controller.command.seniorcashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.user.UserServices;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllStaff implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserServices userService = new UserServices();
        List<User> users = userService.getAllUsers();

        request.setAttribute("staff",users);
        return "/seniorcashier/allStaff.jsp";
    }
}
