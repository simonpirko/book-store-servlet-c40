package by.book.web.servlet.user;

import by.book.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/profile/edit", name = "ProfileEditServlet")
public class ProfileEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User)req.getSession().getAttribute("user");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        req.setAttribute("birthdate", formatter.format(user.getBirthDate()));
        req.setAttribute("house", Integer.toString (user.getAddress().getHome()));

        getServletContext().getRequestDispatcher("/pages/user/editProfile.jsp").forward(req, resp);
    }

}
