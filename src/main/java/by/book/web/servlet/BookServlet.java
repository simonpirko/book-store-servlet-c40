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

@WebServlet(urlPatterns = "/book", name = "BookServlet")
public class BookServlet extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {
            id = 0;
        }

        if(id != 0) {
            try {
                Book book = bookService.getBook(id);
                req.setAttribute("book", book);
                getServletContext().getRequestDispatcher("/pages/book.jsp").forward(req, resp);
            } catch (NotFoundException e) {
                req.setAttribute("message", "Такой книги не существует");
                getServletContext().getRequestDispatcher("/pages/error/notFound.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Проверьте правильность введеного адреса");
            getServletContext().getRequestDispatcher("/pages/error/error.jsp").forward(req, resp);
        }
    }
}
