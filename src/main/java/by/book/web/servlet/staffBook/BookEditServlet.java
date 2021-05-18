package by.book.web.servlet.staffBook;

import by.book.entity.Book;
import by.book.exception.*;
import by.book.service.StaffAuthorService;
import by.book.service.StaffBookService;
import by.book.service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/staff/book/edit", name = "BookEdit")
public class BookEditServlet extends HttpServlet {
    StaffBookService staffBookService = new StaffBookService();
    StaffAuthorService staffAuthorService = new StaffAuthorService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Book book = staffBookService.getById(ValidationService.validAndTransformStringToLong(req.getParameter("id")));
            req.setAttribute("book", book);
            req.setAttribute("listAuthor",staffAuthorService.getAll());
            getServletContext().getRequestDispatcher("/pages/staffBook/bookChanger.jsp").forward(req, resp);
        } catch (NotFoundException | InvalidRequestException e) {
            resp.sendRedirect("/404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            staffBookService.update(
                    ValidationService.validAndTransformStringToLong(req.getParameter("id")),
                    ValidationService.validationTrimString(req.getParameter("name")),
                    ValidationService.validAndTransformStringToInt(req.getParameter("price")),
                    ValidationService.validationTrimString(req.getParameter("description")),
                    ValidationService.validationTrimString(req.getParameter("genre")),
                    ValidationService.validTransformArrayStringToListLong(req.getParameterValues("authorIdList")),
                    ValidationService.validAndTransformStringToLocalDate(req.getParameter("publicDate")));
        } catch (DuplicateDataException e) {
            req.setAttribute("message", "Такая книга уже есть");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Данные не найдены");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (IncorrectData incorrectData) {
            req.setAttribute("message", "Некорректная дата");
        }

        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
