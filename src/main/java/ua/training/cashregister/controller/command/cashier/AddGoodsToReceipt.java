package ua.training.cashregister.controller.command.cashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.receipt.ReceiptService;
import ua.training.cashregister.service.user.UserAuthentification;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AddGoodsToReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService receiptService = new ReceiptService();

        Goods goods = (Goods)request.getSession().getAttribute("goodsToAdd"); //TODO add to request
        User user = (User)request.getSession().getAttribute("LoggedUser");

        Receipt receipt = receiptService.getNotClosedOrOpenNew(user);

        int amount = Integer.parseInt(request.getParameter("amount"));

        ReceiptEntry entry = new ReceiptEntry();
        entry.setGoods(goods);
        entry.setAmount(amount);
        entry.setReceipt(receipt);

        receiptService.addReceiptEntry(receipt,entry);

        request.setAttribute("AddedEntries",receipt.getEntries());

        return "/cashier/CreateReceipt.jsp";
    }
}
