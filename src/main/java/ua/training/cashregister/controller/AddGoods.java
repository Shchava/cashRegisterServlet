package ua.training.cashregister.controller;

import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.entity.enums.GoodsTypes;
import ua.training.cashregister.entity.enums.Roles;
import ua.training.cashregister.service.goods.GoodsService;
import ua.training.cashregister.service.user.UserRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/merchandiser/addGoods/")
public class AddGoods extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        GoodsTypes type = GoodsTypes.valueOf(request.getParameter("type"));

        Goods goods = Goods.createGoods(name,type,price,amount);

        GoodsService services = new GoodsService();

        boolean registered = services.addGoods(goods);

        request.setAttribute("registered", registered);
        request.getRequestDispatcher("/merchandiser/addGoods.jsp").forward(request, response);

    }

}
