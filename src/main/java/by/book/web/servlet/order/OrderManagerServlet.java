package by.book.web.servlet.order;

import by.book.entity.Order;
import by.book.entity.User;
import by.book.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/staff/orderList", name = "OrderManager")
public class OrderManagerServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderService.getAll();
        req.setAttribute("listOrder", orderList);
        getServletContext().getRequestDispatcher("/pages/order/orderManager.jsp").forward(req, resp);
    }
}
