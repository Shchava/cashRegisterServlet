package ua.training.cashregister.controller.command.merchandiser;

import ua.training.cashregister.controller.command.Command;
import ua.training.cashregister.entity.Goods;
import ua.training.cashregister.service.goods.GoodsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Objects.nonNull;

public class ShowWarehouse implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        GoodsService service = new GoodsService();

        int page = 0;
        int recordsPerPage = 5;
        if(nonNull(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(nonNull(request.getParameter("recordsPerPage"))){
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        int rows = service.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(rows * 1.0 / recordsPerPage);
        int offset = page*recordsPerPage;

        request.setAttribute("numberOfPages",numberOfPages);
        request.setAttribute("page",page);
        request.setAttribute("recordsPerPage",recordsPerPage);


        List<Goods> goods = service.getGoods(offset,recordsPerPage);

        request.setAttribute("StoredGoods",goods);

        return  "/merchandiser/warehouse.jsp";
    }
}
