package by.book.web.filter;

import by.book.entity.Role;
import by.book.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//protects against access to the role change by the administrator to himself
@WebFilter(servletNames = "UserEditServlet")
public class AdminUpdateHimself implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        User user =(User)req.getSession().getAttribute("user");
        long id = Long.parseLong(req.getParameter("id"));

        //the id of the administrator and the id of user that is being changed are compared
        if( id == user.getId()) {
            res.sendRedirect("/404");
        } else {
            chain.doFilter(request, response);
        }
    }
}
