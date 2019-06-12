package ua.training.cashregister.controller.command;

import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.receipt.ReceiptService;
import ua.training.cashregister.service.user.UserAuthentification;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OpenReceipt implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService service = new ReceiptService();
        User user = (User)request.getSession().getAttribute("LoggedUser");
        Receipt receipt = service.getNotClosedOrOpenNew(user);
        request.setAttribute("AddedEntries",receipt.getEntries());
        return "/cashier/CreateReceipt.jsp";
    }
}
