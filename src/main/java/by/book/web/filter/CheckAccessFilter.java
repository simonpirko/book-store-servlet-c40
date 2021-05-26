package by.book.web.filter;

import by.book.entity.Role;
import by.book.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = {"StoreEditServlet", "StoreCreateServlet", "StoreDeleteServlet", "StoreListServlet", "UserListServlet", "UserEditServlet", "AuthorAdd", "BookEdit", "BookAdd", "BookManager", "BookRemove", "OrderManager"})
public class CheckAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (
                user != null && user.getRole() == Role.ADMIN ||
                        user != null && user.getRole() == Role.MODERATOR
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect("/404");
        }
    }
}
