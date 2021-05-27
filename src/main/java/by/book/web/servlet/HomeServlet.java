package by.book.web.servlet;

import by.book.entity.Book;
import by.book.exception.NotFoundException;
import by.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Book> bookRandList = bookService.getRandomBooks();
            req.setAttribute("bookRandList", bookRandList);
            getServletContext().getRequestDispatcher("/pages/home.jsp").forward(req, resp);
        } catch (NotFoundException e) {
            req.setAttribute("message", "Книг нет");
            getServletContext().getRequestDispatcher("/pages/error/notFound.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
