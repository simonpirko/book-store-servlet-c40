package by.book.web.servlet.user;

import by.book.entity.Address;
import by.book.entity.User;
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

@WebServlet(urlPatterns = "/profile/editPersonalData")
public class ProfileEditPersonalDate extends HttpServlet {
    UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");


        String userName = req.getParameter("userName").trim();
        String firstName = req.getParameter("firstName").trim();
        String lastName = req.getParameter("lastName").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.from(LocalDate.parse(req.getParameter("date"), formatter).atStartOfDay());

        try {
            userService.changPersonalData(user, userName, firstName, lastName, birthDate);
            req.setAttribute("message", "Ваши данные изменены");
        } catch (UserDataException e) {
            req.setAttribute("message", e.getMessage());
        }
        req.setAttribute("birthdate", formatter.format(user.getBirthDate()));
        req.getServletContext().getRequestDispatcher("/pages/user/editProfile.jsp").forward(req, resp);

    }
}
