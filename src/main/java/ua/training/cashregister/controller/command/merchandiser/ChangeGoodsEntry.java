package ua.training.cashregister.controller.command.merchandiser;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.enums.GoodsTypes;
import ua.training.cashregister.service.goods.GoodsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeGoodsEntry implements Command {





    @Override
    public String execute(HttpServletRequest request) {
        GoodsService goodsService = new GoodsService();
        long id = Long.parseLong(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        Optional<Goods> changing = goodsService.findGoods(id);

        changing.ifPresent(ch->{
            ch.setPrice(price);
            ch.setAmount(amount);
            goodsService.changeGoods(ch);
        });

        return "/merchandiser/api/showWarehouse";
    }


}
