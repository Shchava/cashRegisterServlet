package ua.training.cashregister.controller;

import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.user.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/seniorcashier/getStaff/")
public class GetAllStaff extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServices userService = new UserServices();
        List<User> users = userService.getAllUsers();

        request.setAttribute("staff",users);
        request.getRequestDispatcher("/seniorcashier/allStaff.jsp").forward(request,response);
    }
}
