package by.book.web.servlet;

import by.book.entity.Address;
import by.book.entity.Order;
import by.book.entity.Store;
import by.book.service.StaffStoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/delivery", name = "DeliveryServlet ")
public class DeliveryServlet extends HttpServlet {
    private StaffStoreService staffStoreService = new StaffStoreService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Store> storeList = staffStoreService.getAll();
        req.setAttribute("storeList", storeList);
        getServletContext().getRequestDispatcher("/pages/delivery.jsp").forward(req, resp);
    }
}
