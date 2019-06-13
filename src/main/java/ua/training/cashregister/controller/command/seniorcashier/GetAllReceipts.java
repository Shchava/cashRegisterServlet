package ua.training.cashregister.controller.command.seniorcashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.controller.command.utilities.PaginationUtility;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.receipt.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllReceipts implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService service = new ReceiptService();


        int rows = service.getNumberOfRecords();

        PaginationUtility utility = new PaginationUtility();

        utility.setAttributes(request,rows);

        List<Receipt> found = service.getReceipts(utility.getOffset(),utility.getRecordsPerPage());

        request.setAttribute("FoundReceipts",found);
        return "/seniorcashier/ListAllReceipts.jsp";
    }
}
