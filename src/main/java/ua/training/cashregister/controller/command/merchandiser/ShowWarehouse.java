package ua.training.cashregister.controller.command.merchandiser;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.service.goods.GoodsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowWarehouse implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        GoodsService service = new GoodsService();
        List<Goods> goods = service.getAllGoods();

        request.setAttribute("StoredGoods",goods);

        return  "/merchandiser/warehouse.jsp";
    }
}
