package ua.training.cashregister.controller;

import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.service.goods.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/merchandiser/showWarehouse/")
public class ShowWarehouse  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        GoodsService service = new GoodsService();
        List<Goods> goods = service.getAllGoods();

        request.setAttribute("StoredGoods",goods);
        request.getRequestDispatcher("/merchandiser/warehouse.jsp").forward(request,response);
    }
}
