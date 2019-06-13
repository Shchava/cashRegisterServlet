package ua.training.cashregister.controller.command.seniorcashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.controller.command.utilities.PaginationUtility;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.user.UserServices;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllStaff implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserServices userService = new UserServices();

        int rows = userService.getNumberOfRecords();
        PaginationUtility utility = new PaginationUtility();

        utility.setAttributes(request,rows);

        List<User> users = userService.getAllUsers(utility.getOffset(),utility.getRecordsPerPage());

        request.setAttribute("staff",users);
        return "/seniorcashier/allStaff.jsp";
    }
}
