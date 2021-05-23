package by.book.web.servlet;

import by.book.entity.Book;
import by.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns="/selection", name = "SelectionServlet")
public class SelectionServlet extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int yearMin;
        int yearMax;

        try {
            yearMin = Integer.parseInt(req.getParameter("yearMin"));
        } catch(NumberFormatException e) {
            yearMin = 0;
        }
        try {
            yearMax = Integer.parseInt(req.getParameter("yearMax"));
        } catch(NumberFormatException e) {
            yearMax = 0;
        }


        List<Book> bookList = bookService.getBook(req.getParameter("genre"), req.getParameter("author"), yearMin, yearMax);
        req.setAttribute("bookList", bookList);

        req.setAttribute("filter", bookService.getFilter());
        getServletContext().getRequestDispatcher("/pages/bookSelection.jsp").forward(req, resp);
    }
}
