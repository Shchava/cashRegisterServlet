package ua.training.cashregister.controller.command.cashier;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.service.receipt.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ReceiptService service = new ReceiptService();
        long id = Long.parseLong(request.getParameter("id"));

        Optional<Receipt> receipt = service.findReceipt(id);
        receipt.ifPresent(rec -> {
            request.setAttribute("receipt",rec);
            request.setAttribute("entries",rec.getEntries());
        });
        return "/cashier/ShowReceipt.jsp";
    }
}
