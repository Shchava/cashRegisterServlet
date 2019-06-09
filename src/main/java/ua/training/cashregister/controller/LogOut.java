package ua.training.cashregister.controller;

import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.user.Authentification;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogOut extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request);
        response.sendRedirect("/login.jsp");
    }

    private void logout(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ses.invalidate();
    }
}
