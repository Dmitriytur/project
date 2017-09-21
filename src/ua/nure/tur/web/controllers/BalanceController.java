package ua.nure.tur.web.controllers;

import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.services.UserService;
import ua.nure.tur.utils.Pages;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/profile/balance")
public class BalanceController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = ServiceFactory.getFactory().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = (String) req.getSession().getAttribute("message");
        req.setAttribute("message", message);
        req.getSession().removeAttribute("message");


        Long userId = (Long) req.getSession().getAttribute("userId");

        try {
            User user = userService.getUserById(userId);
            req.setAttribute("balance", user.getBalance());
        } catch (ServiceException e) {
            resp.setStatus(500);
        }
        req.getRequestDispatcher(Pages.PAGE_PREFIX + "balance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String amountParam = req.getParameter("amount");

        try {
            double amount = Double.parseDouble(amountParam);
            userService.changeBalanceForUser(userId, amount);
            req.getSession().setAttribute("message", String.format("Your balance was increased by $%.2f", amount));
        } catch (NullPointerException | NumberFormatException e){
            req.getSession().setAttribute("message", "Incorrect format");
        } catch (ServiceException e) {
            req.getSession().setAttribute("message", "Internal error");
        }
        resp.sendRedirect("/page/profile/balance");

    }
}
