package by.book.web.servlet.staff;

import by.book.entity.Store;
import by.book.service.StaffStoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/staff/store", name = "StoreListServlet")
public class StoreListServlet extends HttpServlet {
    private StaffStoreService staffStoreService = new StaffStoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Store> storeList = staffStoreService.getAll();
        req.setAttribute("storeList", storeList);
        getServletContext().getRequestDispatcher("/pages/staff/storeList.jsp").forward(req, resp);
    }
}
