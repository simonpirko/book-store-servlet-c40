package by.book.web.servlet.staffBook;

import by.book.entity.Author;
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
            List<Author> authors = staffAuthorService.getListById(req.getParameterValues("authorIdList"));
            staffBookService.save(req.getParameter("name"),req.getParameter("price"),req.getParameter("description"),req.getParameter("genre"),authors,req.getParameter("publicDate"));
            req.setAttribute("message", "Книга добавлена");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (DaoException e) {
            req.setAttribute("message", "Такая книга уже существует");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Автор не найден");
        }catch (NullPointerException e){
            req.setAttribute("message", "Ошибка добавления автора");
        } catch (IncorrectData incorrectData) {
            req.setAttribute("message", "Неверная цена");
        }
        req.setAttribute("listBook",staffBookService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffBook/bookManager.jsp").forward(req,resp);
    }
}
