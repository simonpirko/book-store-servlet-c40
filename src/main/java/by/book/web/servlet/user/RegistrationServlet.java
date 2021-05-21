package by.book.web.servlet.user;

import by.book.entity.Address;
import by.book.exception.UserDataException;
import by.book.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/reg", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/user/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName").trim();
        String firstName = req.getParameter("firstName").trim();
        String lastName = req.getParameter("lastName").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.from(LocalDate.parse(req.getParameter("birthDate"), formatter).atStartOfDay());
        String userStreet = req.getParameter("userStreet").trim();
        int userHome = Integer.parseInt(req.getParameter("userHome"));
        String password = req.getParameter("password");
        Address userAddress = new Address(2, userStreet, userHome);
        try {
            userService.add(userName, firstName, lastName, birthDate, userAddress, password);
            req.setAttribute("message", "Успешно зарегистрирован!");
        } catch (UserDataException e) {
            req.setAttribute("message", e.getMessage());
        }
        getServletContext().getRequestDispatcher("/pages/user/registration.jsp").forward(req, resp);

    }
}
