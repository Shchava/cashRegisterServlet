package ua.training.cashregister.controller.servlet.seniorcashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.controller.command.seniorcashier.GetAllReceipts;
import ua.training.cashregister.controller.command.seniorcashier.GetAllStaff;
import ua.training.cashregister.controller.command.seniorcashier.Register;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/seniorcashier/api/*")
public class SeniorCashierServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("register", new Register());
        commands.put("getStaff", new GetAllStaff());
        commands.put("getAllReceipts", new GetAllReceipts());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/seniorcashier/api/" , "");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp");
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request,response);
    }
}