package ua.training.cashregister.controller.servlet.cashier;

import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.entity.Receipt;
import ua.training.cashregister.entity.ReceiptEntry;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.service.goods.GoodsService;
import ua.training.cashregister.service.receipt.ReceiptService;
import ua.training.cashregister.service.user.UserAuthentification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/cashier/createreceipt/")
public class FindGoods extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceiptService receiptService = new ReceiptService();
        GoodsService goodsService = new GoodsService();
        Goods gd = null;
        String idAtr = req.getParameter("ID");
        String nameAtr = req.getParameter("name");
        if(idAtr != null){
            long id = Long.parseLong(idAtr);
            goodsService.findGoods(id).ifPresent((g)->{
                req.getSession().setAttribute("goodsToAdd",g);//TODO add to request
                req.setAttribute("found",g);
            });
        }else{
            if(nameAtr != null){
                goodsService.findFirst(nameAtr).ifPresent((g)->{
                    req.getSession().setAttribute("goodsToAdd",g);//TODO add to request
                    req.setAttribute("found",g);
                });
            }
        }
        Optional<User> userOptional = new UserAuthentification().findUser((long)req.getSession().getAttribute("userId"));
        User user = userOptional.orElseThrow(()->new IllegalArgumentException("wrong user id"));
        Receipt receipt = receiptService.getNotClosedOrOpen(user);

        req.setAttribute("AddedEntries",receipt.getEntries());
        req.getRequestDispatcher("/cashier/CreateReceipt.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceiptService receiptService = new ReceiptService();


        Goods good = (Goods)req.getSession().getAttribute("goodsToAdd"); //TODO add to request
        Optional<User> userOptional = new UserAuthentification().findUser((long)req.getSession().getAttribute("userId"));
        User user = userOptional.orElseThrow(()->new IllegalArgumentException("wrong user id"));

        Receipt receipt = receiptService.getNotClosedOrOpen(user);

        int amount = Integer.parseInt(req.getParameter("amount"));
        ReceiptEntry entry = new ReceiptEntry();
        entry.setGoods(good);
        entry.setAmount(amount);
        entry.setReceipt(receipt);

        receiptService.addReceiptEntry(receipt,entry);

        req.setAttribute("AddedEntries",receipt.getEntries());
        req.getRequestDispatcher("/cashier/CreateReceipt.jsp").forward(req, resp);
    }
}
