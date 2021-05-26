package by.book.web.servlet.user;

import by.book.entity.User;
import by.book.exception.UserDataException;
import by.book.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/profile/editPassword", name = "ProfileEditPasswordServlet")
public class ProfileEditPasswordServlet extends HttpServlet {

   UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword").trim();
        String newPassword = req.getParameter("newPassword").trim();
        String confirmNewPassword = req.getParameter("confirmNewPassword").trim();

        User user =(User)req.getSession().getAttribute("user");

        try {
            userService.changPassword(user,oldPassword,newPassword, confirmNewPassword);
            req.setAttribute("message", "Address changed successfully!");
        } catch (UserDataException e) {
            req.setAttribute("message", e.getMessage() );

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        req.setAttribute("birthdate", formatter.format(user.getBirthDate()));

        req.getServletContext().getRequestDispatcher("/pages/user/editProfile.jsp").forward(req, resp);

    }
}
