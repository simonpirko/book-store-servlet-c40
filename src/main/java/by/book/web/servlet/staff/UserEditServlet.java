package by.book.web.servlet.staff;

import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.exception.ServerErrorException;
import by.book.service.StaffUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/staff/user/edit", name = "UserEditServlet")
public class UserEditServlet extends HttpServlet {
    private StaffUserService staffUserService = new StaffUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = staffUserService.getOne(req.getParameter("id"));
            req.setAttribute("user", user);

            List<Role> roleList = Arrays.asList(Role.values());
            req.setAttribute("roleList", roleList);

            getServletContext().getRequestDispatcher("/pages/staff/userEdit.jsp").forward(req, resp);
        } catch (NotFoundException | InvalidRequestException e) {
            resp.sendRedirect("/404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            staffUserService.update(req.getParameter("id"), req.getParameter("role"));
            req.setAttribute("message", "Пользователь успешно изменен");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Не верно введены данные");
        } catch (ServerErrorException e) {
            req.setAttribute("message", "Произошла ошибка. Попробуйте повторить запрос через некоторое время");
        } catch (NotFoundException e) {
            req.setAttribute("message", "Пользователь не найден");
        }

        try {
            User user = staffUserService.getOne(req.getParameter("id"));
            req.setAttribute("user", user);

            List<Role> roleList = Arrays.asList(Role.values());
            req.setAttribute("roleList", roleList);
        } catch (NotFoundException | InvalidRequestException e) {
            resp.sendRedirect("/404");
        }

        getServletContext().getRequestDispatcher("/pages/staff/userEdit.jsp").forward(req, resp);
    }
}
