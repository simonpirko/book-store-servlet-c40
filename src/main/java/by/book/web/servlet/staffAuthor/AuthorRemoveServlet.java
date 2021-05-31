package by.book.web.servlet.staffAuthor;

import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.service.StaffAuthorService;
import by.book.service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/staff/author/remove", name = "AuthorRemove")
public class AuthorRemoveServlet extends HttpServlet {
    StaffAuthorService staffAuthorService = new StaffAuthorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            staffAuthorService.remove(ValidationService.validAndTransformStringToLong(req.getParameter("id")));
        } catch (NotFoundException e) {
            req.setAttribute("message", "Автор не найден");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Неккоретные данные");
        }
        req.setAttribute("listAuthor", staffAuthorService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffAuthor/authorManager.jsp").forward(req, resp);
    }
}
