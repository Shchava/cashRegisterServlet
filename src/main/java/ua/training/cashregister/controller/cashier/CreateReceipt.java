package ua.training.cashregister.controller.cashier;

import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;
import ua.training.cashregister.service.receipt.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cashier/createreceipt/create")
public class CreateReceipt extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // List<ReceiptEntry> addedGoods
        ReceiptService service = new ReceiptService();


        request.getRequestDispatcher("/cashier/CreateReceipt.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // List<ReceiptEntry> addedGoods
        ReceiptService service = new ReceiptService();
        service.closeLastReceipt((Long)request.getSession().getAttribute("userId"));

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
