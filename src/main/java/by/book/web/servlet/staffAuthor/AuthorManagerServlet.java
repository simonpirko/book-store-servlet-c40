package by.book.web.servlet.staffAuthor;

import by.book.service.StaffAuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/staff/author", name = "AuthorManager")
public class AuthorManagerServlet extends HttpServlet {
    StaffAuthorService  staffAuthorService = new StaffAuthorService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listAuthor", staffAuthorService.getAll());
        getServletContext().getRequestDispatcher("/pages/staffAuthor/authorManager.jsp").forward(req, resp);
    }
}
