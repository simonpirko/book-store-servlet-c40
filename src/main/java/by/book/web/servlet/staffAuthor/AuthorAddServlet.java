package by.book.web.servlet.staffAuthor;

import by.book.exception.DaoException;
import by.book.exception.DuplicateDataException;
import by.book.exception.InvalidRequestException;
import by.book.service.StaffAuthorService;
import by.book.service.StaffBookService;
import by.book.service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/staff/book/addAuthor" , name = "AuthorAdd")
public class AuthorAddServlet extends HttpServlet {
    StaffBookService staffBookService = new StaffBookService();
    StaffAuthorService staffAuthorService = new StaffAuthorService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/staffAuthor/authorAdd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            staffAuthorService.save(
                    ValidationService.validationTrimString(req.getParameter("fName")),
                    ValidationService.validationTrimString(req.getParameter("lName")),
                    ValidationService.validationTrimString(req.getParameter("description")));
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (DuplicateDataException e) {
            req.setAttribute("message", "Такой автор уже есть");
        }

        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
