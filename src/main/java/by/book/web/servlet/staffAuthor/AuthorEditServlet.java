package by.book.web.servlet.staffAuthor;

import by.book.entity.Author;
import by.book.exception.DaoException;
import by.book.exception.DuplicateDataException;
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

@WebServlet(urlPatterns = "/staff/author/edit", name = "AuthorEdit")
public class AuthorEditServlet extends HttpServlet {
    StaffAuthorService staffAuthorService = new StaffAuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Author author = staffAuthorService.getById(ValidationService.validAndTransformStringToLong(req.getParameter("id")));
            req.setAttribute("author", author);
            getServletContext().getRequestDispatcher("/pages/staffAuthor/authorEdit.jsp").forward(req,resp);
        } catch (NotFoundException | InvalidRequestException e) {
            resp.sendRedirect("/404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            staffAuthorService.update(
                    ValidationService.validAndTransformStringToLong(req.getParameter("id")),
                    ValidationService.validationTrimString(req.getParameter("fName")),
                    ValidationService.validationTrimString(req.getParameter("lName")),
                    ValidationService.validationTrimString(req.getParameter("description")));
        } catch (NotFoundException e) {
            req.setAttribute("message", "Данные не найдены");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (DuplicateDataException e) {
            req.setAttribute("message", "Такой автор уже есть");
        } catch (DaoException e) {
            e.printStackTrace();
        }

        req.setAttribute("listAuthor", staffAuthorService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffAuthor/authorManager.jsp").forward(req, resp);
    }
}
