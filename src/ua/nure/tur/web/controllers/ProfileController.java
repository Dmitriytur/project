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

@WebServlet("/page/profile")
public class ProfileController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = ServiceFactory.getFactory().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        try {
            User user = userService.getUserById(userId);
            req.setAttribute("user", user);
        } catch (ServiceException e) {
            resp.setStatus(500);
            return;
        }
        req.getRequestDispatcher(Pages.PAGE_PREFIX + "profile_overview.jsp").forward(req, resp);
    }
}
