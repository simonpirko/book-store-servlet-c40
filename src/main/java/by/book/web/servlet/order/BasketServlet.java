package by.book.web.servlet.order;

import by.book.exception.InvalidRequestException;
import by.book.service.OrderService;
import by.book.service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/basket", name = "BasketServlet")
public class BasketServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (orderService.basketIsEmpty()) {
            req.setAttribute("totalPrice", orderService.totalPrice());
            req.setAttribute("listBook", orderService.getListBookInBasket());
        } else {
            req.setAttribute("message", "Ваша корзина пуста");
        }
        getServletContext().getRequestDispatcher("/pages/order/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            orderService.addBookInBasket(ValidationService.validAndTransformStringToLong(req.getParameter("id")));
        } catch (InvalidRequestException e) {
            req.setAttribute("massage", "Неккоректные данные");
        }
        getServletContext().getRequestDispatcher("/pages/book.jsp").forward(req, resp);
    }
}
