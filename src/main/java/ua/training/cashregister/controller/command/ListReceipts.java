package ua.training.cashregister.controller.command;

import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.receipt.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListReceipts implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService service = new ReceiptService();

        User user = (User)request.getSession().getAttribute("LoggedUser");
        List<Receipt> found = service.findReceiptsByCashierId(user.getId());

        request.setAttribute("FoundReceipts",found);
        return "/cashier/ListMyReceipts.jsp";

    }
}