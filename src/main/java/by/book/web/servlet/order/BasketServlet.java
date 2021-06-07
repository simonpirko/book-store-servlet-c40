package by.book.web.servlet.order;

import by.book.entity.Book;
import by.book.exception.NotFoundException;
import by.book.service.BookService;
import by.book.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/basket", name = "BasketServlet")
public class BasketServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> basket = (List<Book>) req.getSession().getAttribute("basket");
        int result = 0;
        for (Book book : basket) {
            result += book.getPrice();
        }
        if (!basket.isEmpty()) {
            req.setAttribute("totalPrice", result);
            req.setAttribute("listBook", basket);
        } else {
            req.setAttribute("message", "Ваша корзина пуста");
        }
        getServletContext().getRequestDispatcher("/pages/order/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> basket = (List<Book>) req.getSession().getAttribute("basket");
        int idBook = Integer.parseInt((req.getParameter("id")));
        try {
            basket.remove(bookService.getBook(idBook));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/basket");
    }
}
