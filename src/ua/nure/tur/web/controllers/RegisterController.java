package ua.nure.tur.web.controllers;

import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.RegistrationException;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.services.UserService;
import ua.nure.tur.utils.Pages;
import ua.nure.tur.utils.validators.UserValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/register")
public class RegisterController extends HttpServlet {

    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = ServiceFactory.getFactory().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Pages.PAGE_PREFIX + "register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserName(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        if (UserValidator.validateUser(user)){
            try {
                userService.registerClient(user);
            } catch (ServiceException e){
                resp.setStatus(500);
                resp.getWriter().print("Internal server error");
            } catch (RegistrationException e){
                resp.setStatus(400);
                resp.getWriter().print(e.getMessage());
            }

        } else {
            resp.setStatus(400);
            resp.getWriter().print("Incorrect request");
        }

    }

}
