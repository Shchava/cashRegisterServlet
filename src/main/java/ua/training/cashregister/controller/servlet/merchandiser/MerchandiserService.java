package ua.training.cashregister.controller.servlet.merchandiser;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.controller.command.merchandiser.AddGoodsToWarehouse;
import ua.training.cashregister.controller.command.merchandiser.ChangeGoodsEntry;
import ua.training.cashregister.controller.command.merchandiser.ShowWarehouse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/merchandiser/api/*")
public class MerchandiserService extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        commands.put("showWarehouse", new ShowWarehouse());
        commands.put("addGoods", new AddGoodsToWarehouse());
        commands.put("editEntry", new ChangeGoodsEntry());
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
        path = path.replaceAll(".*/merchandiser/api/" , "");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp");
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request,response);
    }
}
