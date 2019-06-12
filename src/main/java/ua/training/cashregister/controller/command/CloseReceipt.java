package ua.training.cashregister.controller.command;

import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.receipt.ReceiptService;

import javax.servlet.http.HttpServletRequest;

public class CloseReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService service = new ReceiptService();
        User user = (User)request.getSession().getAttribute("LoggedUser");
        service.closeLastReceipt(user.getId());
        return "/index.jsp";
    }
}
