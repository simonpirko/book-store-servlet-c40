package by.book.web.servlet.staffStore;

import by.book.entity.Store;
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

@WebServlet(urlPatterns = "/staff/store/edit", name = "StoreEdit")
public class StoreEdit extends HttpServlet {
    private final StaffStoreService staffStoreService = new StaffStoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Store store = staffStoreService.getOne(req.getParameter("id"));
            req.setAttribute("store", store);
            getServletContext().getRequestDispatcher("/pages/staffStore/storeEdit.jsp").forward(req, resp);
        } catch (NotFoundException | InvalidRequestException e) {
            resp.sendRedirect("/404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            staffStoreService.update(req.getParameter("id"), req.getParameter("name"), req.getParameter("street"), req.getParameter("house"));
            req.setAttribute("message", "Магазин успешно изменен");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Не верно введены данные");
        } catch (ServerErrorException e) {
            req.setAttribute("message", "Произошла ошибка. Попробуйте повторить запрос через некоторое время");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Магазин не найден");
        }

        try {
            Store store = staffStoreService.getOne(req.getParameter("id"));
            req.setAttribute("store", store);
        } catch (NotFoundException | InvalidRequestException e) {
            resp.sendRedirect("/404");
        }

        getServletContext().getRequestDispatcher("/pages/staffStore/storeEdit.jsp").forward(req, resp);
    }
}
