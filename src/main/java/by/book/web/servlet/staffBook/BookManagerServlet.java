package by.book.web.servlet.staffBook;

import by.book.service.StaffBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/staff/book",name = "BookManager")
public class BookManagerServlet extends HttpServlet {
    StaffBookService staffBookService = new StaffBookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
