package ua.training.cashregister.controller.cashier;

import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.service.receipt.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cashier/listmyreceipts")
public class ListMyReceipts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ReceiptService service = new ReceiptService();
        List<Receipt> found = service.findReceiptsByCashierId((Long)req.getSession().getAttribute("userId"));

        req.setAttribute("FoundReceipts",found);
        req.getRequestDispatcher("/cashier/ListMyReceipts.jsp").forward(req, resp);

    }
}
