package ua.training.cashregister.controller.command.merchandiser;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.controller.command.utilities.PaginationUtility;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.service.goods.GoodsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowWarehouse implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        GoodsService service = new GoodsService();

        int rows = service.getNumberOfRecords();
        PaginationUtility utility = new PaginationUtility();
        utility.setAttributes(request,rows);

        request.setAttribute("editingId",request.getParameter("editingId"));//TODO show editing window in js

        List<Goods> goods = service.getGoods(utility.getOffset(),utility.getRecordsPerPage());

        request.setAttribute("StoredGoods",goods);

        return  "/merchandiser/warehouse.jsp";
    }
}
