package by.book.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = {"StoreEditServlet", "StoreCreateServlet", "StoreDeleteServlet", "StoreListServlet",
        "UserListServlet", "UserEditServlet", "AuthorAddServlet", "BookAddServlet", "BookEditServlet", "BookManagerServlet",
        "BookRemoveServlet", "CheckAccessFilter", "AuthorizationServlet", "LogOutServlet", "ProfileEditAddressServlet",
        "ProfileEditPasswordServlet", "ProfileEditPersonalDate", "ProfileEditServlet", "ProfileServlet", "RegistrationServlet",
        "AboutUsServlet", "BasketServlet", "BookRemoveInBasketServlet", "OrderServlet", "OrderManager", "OrderProfileServlet",
        "CatalogServlet", "DotsServlet", "HomeServlet", "SearchServlet", "SelectionServlet", "SettingsServlet"})
public class EncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
