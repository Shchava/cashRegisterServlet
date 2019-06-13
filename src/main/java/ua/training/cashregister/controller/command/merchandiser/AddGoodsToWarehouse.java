package ua.training.cashregister.controller.command.merchandiser;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.enums.GoodsTypes;
import ua.training.cashregister.service.goods.GoodsService;

import javax.servlet.http.HttpServletRequest;

public class AddGoodsToWarehouse implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        GoodsTypes type = GoodsTypes.valueOf(request.getParameter("type"));

        Goods goods = Goods.createGoods(name,type,price,amount);

        GoodsService services = new GoodsService();

        boolean registered = services.addGoods(goods);

        request.setAttribute("registered", registered);
        return "/merchandiser/addGoods.jsp";
    }
}
