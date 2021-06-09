package by.book.web.servlet.order;

import by.book.entity.*;
import by.book.exception.IncorrectData;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;
import by.book.service.OrderService;
import by.book.service.StaffStoreService;
import by.book.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/order", name = "OrderServlet")
public class OrderAddServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();
    private StaffStoreService storeService = new StaffStoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> basket = (List<Book>) req.getSession().getAttribute("basket");
        List<Store> storeList = storeService.getAll();
        User user = (User) req.getSession().getAttribute("user");
        int totalPrice = 0;
        for (Book book : basket) {
            totalPrice += book.getPrice();
        }

        req.setAttribute("storeList", storeList);
        req.setAttribute("user", user);
        req.setAttribute("totalPrice", totalPrice);
        req.setAttribute("listBook", basket);
        getServletContext().getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> basket = (List<Book>) req.getSession().getAttribute("basket");
        User user = (User) req.getSession().getAttribute("user");
        Type type = (Type) req.getAttribute("type");

        String one = req.getParameter("one");

        int totalPrice = 0;
        for (Book book : basket) {
            totalPrice += book.getPrice();
        }

        try {
            List<Book> basketInOrder = basket;
            System.out.println(basketInOrder);
            if (one.equals("delivery")) {
                orderService.save(basketInOrder, user, user.getAddress(), totalPrice, type.DELIVERY);
            } else {
                orderService.save(basketInOrder, user, null, totalPrice, type.PICKUP);
            }
            req.setAttribute("message", "Заказ сделан");
        } catch (InvalidRequestException e) {
            req.setAttribute("message", "Некорректные данные");
        } catch (IncorrectData incorrectData) {
            incorrectData.printStackTrace();
        }
        basket.clear();
        resp.sendRedirect("/basket");
    }
}
