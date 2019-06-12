package ua.training.cashregister.controller.servlet.cashier;

import ua.training.cashregister.controller.command.*;
import ua.training.cashregister.controller.command.FindGoods;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cashier/api/*")
public class CashierService extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("openreceipt", new OpenReceipt());
        commands.put("findgoods", new FindGoods());
        commands.put("addgoods", new AddGoods());
        commands.put("closereceipt", new CloseReceipt());
        commands.put("listreceipts", new ListReceipts());
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
        path = path.replaceAll(".*/cashier/api/" , "");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp");
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request,response);
    }
}