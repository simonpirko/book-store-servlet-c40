package by.book.web.servlet.staffBook;

import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.service.StaffBookService;
import by.book.service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/staff/book/remove", name = "BookRemove")
public class BookRemoveServlet extends HttpServlet {
    StaffBookService staffBookService = new StaffBookService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            staffBookService.delete(ValidationService.validAndTransformStringToLong(req.getParameter("id")));
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Неккоретные данные");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Книга не найдена");
        }
        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
