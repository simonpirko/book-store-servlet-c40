package by.book.web.servlet.staffBook;

import by.book.entity.Author;
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
import java.util.List;

@WebServlet(urlPatterns = "/staff/book/add", name ="BookAdd")
public class BookAddServlet extends HttpServlet {
    StaffBookService staffBookService = new StaffBookService();
    StaffAuthorService staffAuthorService = new StaffAuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listAuthor",staffAuthorService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            staffBookService.save(
                    ValidationService.validationTrimString(req.getParameter("name")),
                    ValidationService.validAndTransformStringToInt(req.getParameter("price")),
                    ValidationService.validationTrimString(req.getParameter("description")),
                    ValidationService.validationTrimString(req.getParameter("genre")),
                    ValidationService.validTransformArrayStringToListLong(req.getParameterValues("authorIdList")),
                    ValidationService.validAndTransformStringToLocalDate(req.getParameter("publicDate")));
        } catch (DuplicateDataException e) {
            req.setAttribute("message", "Такая книга уже есть");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Автор не найден");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (IncorrectData e) {
            req.setAttribute("message","Данные указаны неверно");
        }

        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
