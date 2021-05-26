package by.book.web.servlet.order;

import by.book.entity.*;
import by.book.exception.IncorrectData;
import by.book.exception.InvalidRequestException;
import by.book.service.OrderService;
import by.book.service.StaffStoreService;
import by.book.service.UserService;
import by.book.service.ValidationService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/profile/order", name = "OrderServlet")
public class OrderAddServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();
    private StaffStoreService storeService = new StaffStoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("totalPrice", orderService.totalPrice());
        req.setAttribute("listBook", orderService.getListBookInBasket());
        getServletContext().getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userService.getByUserName(req.getParameter("login"));
            List<Book> books = orderService.getListBookInBasket();
            orderService.save(books, user, ValidationService.validAndTransformStringToLong(req.getParameter("id")), Type.valueOf(req.getParameter("role")));
            req.setAttribute("message", "Заказ сделан");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (IncorrectData incorrectData) {
            incorrectData.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }
}
