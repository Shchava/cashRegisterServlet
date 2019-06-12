package ua.training.cashregister.controller.command;

import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.goods.GoodsService;
import ua.training.cashregister.service.receipt.ReceiptService;
import ua.training.cashregister.service.user.UserAuthentification;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class FindGoods implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService receiptService = new ReceiptService();
        GoodsService goodsService = new GoodsService();

        String idAtr = request.getParameter("ID");
        String nameAtr = request.getParameter("name");
        if(idAtr != null){
            long id = Long.parseLong(idAtr);
            goodsService.findGoods(id).ifPresent((g)->{
                request.getSession().setAttribute("goodsToAdd",g);//TODO add to request
                request.setAttribute("found",g);
            });
        }else{
            if(nameAtr != null){
                goodsService.findFirst(nameAtr).ifPresent((g)->{
                    request.getSession().setAttribute("goodsToAdd",g);//TODO add to request
                    request.setAttribute("found",g);
                });
            }
        }

        User user = (User)request.getSession().getAttribute("LoggedUser");
        Receipt receipt = receiptService.getNotClosedOrOpenNew(user);

        request.setAttribute("AddedEntries",receipt.getEntries());


        return "/cashier/CreateReceipt.jsp";
    }
}
