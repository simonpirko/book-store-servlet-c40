package by.book.web.servlet.order;

import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.service.OrderService;
import by.book.service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/basket/remove", name = "BookRemoveInBasketServlet")
public class BookRemoveInBasketServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            orderService.removeBookInBasket(ValidationService.validAndTransformStringToLong(req.getParameter("id")));
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Неккоретные данные");
        }
        req.setAttribute("listBook", orderService.getListBookInBasket());
        getServletContext().getRequestDispatcher("/pages/order/basket.jsp").forward(req, resp);
    }
}
