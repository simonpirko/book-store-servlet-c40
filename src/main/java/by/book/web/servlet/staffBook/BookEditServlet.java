package by.book.web.servlet.staffBook;

import by.book.entity.Book;
import by.book.exception.DaoException;
import by.book.exception.IncorrectData;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.service.StaffAuthorService;
import by.book.service.StaffBookService;

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
            Book book = staffBookService.getById(req.getParameter("id"));
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
            staffBookService.update(req.getParameter("id"),req.getParameter("name"),req.getParameter("price"),req.getParameter("description"),req.getParameter("genre"),staffAuthorService.getListById(req.getParameterValues("authorIdList")),req.getParameter("publicDate"));
            req.setAttribute("message", "Книга обновлена");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Неккоретные данные");
        } catch (DaoException e) {
            req.setAttribute("message", "Такая книга уже существует");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Автор не найден");
        }catch (NullPointerException e){
            req.setAttribute("message", "Ошибка добавление автора");
        }catch (IncorrectData incorrectData) {
            req.setAttribute("message", "Неверная цена");
        }
        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
