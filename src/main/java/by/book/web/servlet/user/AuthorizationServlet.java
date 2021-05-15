package by.book.web.servlet.user;

import by.book.entity.Address;
import by.book.entity.Role;
import by.book.entity.User;
import by.book.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

@WebServlet(urlPatterns = "/auth", name = "Authorization")
public class AuthorizationServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/user/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login").trim();
        String password = req.getParameter("password");

        if (userService.authCheck(login, password)) {
            req.getSession().setAttribute("user", userService.getByUserName(login));
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("authMessage", "Incorrect login or password");
            getServletContext().getRequestDispatcher("/pages/user/authorization.jsp").forward(req, resp);
        }
    }
}