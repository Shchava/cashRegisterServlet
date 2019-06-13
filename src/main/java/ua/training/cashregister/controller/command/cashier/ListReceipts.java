package ua.training.cashregister.controller.command.cashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.controller.command.utilities.PaginationUtility;
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

        int rows = service.countReceiptsByCashierId(user.getId());

        PaginationUtility utility = new PaginationUtility();

        utility.setAttributes(request,rows);

        List<Receipt> found = service.findReceiptsByCashierId(user.getId(),utility.getOffset(),utility.getRecordsPerPage());

        request.setAttribute("FoundReceipts",found);
        return "/cashier/ListMyReceipts.jsp";

    }
}
