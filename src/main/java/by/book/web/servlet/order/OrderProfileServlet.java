package by.book.web.servlet.order;

import by.book.entity.User;
import by.book.exception.UserDataException;
import by.book.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile/orderList", name = "OrderProfileServlet")
public class OrderProfileServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/pages/order/orderProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("listOrder", orderService.getOrderByUsername(user.getUsername()));
        req.getServletContext().getRequestDispatcher("/pages/order/orderProfile.jsp").forward(req, resp);
    }
}
