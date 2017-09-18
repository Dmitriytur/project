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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/page/login")
public class LoginController extends HttpServlet {

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = ServiceFactory.getFactory().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, session.getAttribute(ERROR_MESSAGE_ATTRIBUTE));
        req.getSession().removeAttribute(ERROR_MESSAGE_ATTRIBUTE);
        req.getRequestDispatcher(Pages.PAGE_PREFIX + "login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username != null && password != null){
            try {
                User user = userService.checkUser(username, password);
                if (user != null){
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/page/home");
                } else {
                    req.getSession().setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Wrong username or password");
                    resp.sendRedirect("/page/login");
                }
            } catch (ServiceException e) {
                resp.setStatus(500);
            }
        } else {
            resp.setStatus(400);
        }



    }
}
