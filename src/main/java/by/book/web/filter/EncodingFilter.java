package by.book.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = {"StoreCreate", "StoreList", "StoreEdit"})
public class EncodingFilter implements Filter {
    private final String encoding = "UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
