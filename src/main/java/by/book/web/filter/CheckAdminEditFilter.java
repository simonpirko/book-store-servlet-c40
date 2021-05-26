package by.book.web.filter;

import by.book.entity.Role;
import by.book.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//blocks access to the staff menu for all user roles,  except administrator
@WebFilter(servletNames = {"StoreEditServlet", "StoreCreateServlet", "StoreDeleteServlet", "StoreListServlet", "UserEditServlet"})
public class CheckAdminEditFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        User user = (User) req.getSession().getAttribute("user");

        if(user != null && user.getRole() == Role.ADMIN) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/404");
        }
    }
}
