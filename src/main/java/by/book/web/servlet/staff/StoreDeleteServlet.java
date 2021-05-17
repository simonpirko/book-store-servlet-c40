package by.book.web.servlet.staff;

import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.exception.ServerErrorException;
import by.book.service.StaffStoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/staff/store/delete", name = "StoreDeleteServlet")
public class StoreDeleteServlet extends HttpServlet {
    private final StaffStoreService staffStoreService = new StaffStoreService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            staffStoreService.delete(req.getParameter("id"));
            req.setAttribute("message", "Магазин успешно удален");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Не верно переданы данные");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Магазин не найден");
        } catch (ServerErrorException e) {
            req.setAttribute("message", "Произошла ошибка. Попробуйте повторить запрос через некоторое время");
        }
        getServletContext().getRequestDispatcher("/pages/staff/storeList.jsp").forward(req, resp);
    }
}
