package by.book.web.servlet.staffStore;

import by.book.exception.DuplicateDataException;
import by.book.exception.InvalidRequestException;
import by.book.exception.ServerErrorException;
import by.book.service.StaffStoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/staff/store/create", name = "StoreCreate")
public class StoreCreate extends HttpServlet {
    private StaffStoreService staffStoreService = new StaffStoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/staffStore/storeCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            staffStoreService.create(req.getParameter("name"), req.getParameter("street"), req.getParameter("house"));
            req.setAttribute("message", "Магазин успешно создан");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Не верно введены данные");
        } catch (DuplicateDataException e) {
            req.setAttribute("message", "Магазин по данному адресу уже существует");
        } catch (ServerErrorException e) {
            req.setAttribute("message", "Произошла ошибка. Попробуйте повторить запрос через некоторое время");
        }

        getServletContext().getRequestDispatcher("/pages/staffStore/storeCreate.jsp").forward(req, resp);
    }
}
