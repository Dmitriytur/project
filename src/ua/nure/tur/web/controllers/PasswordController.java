package ua.nure.tur.web.controllers;

import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.services.ServiceResult;
import ua.nure.tur.services.ServiceResultStatus;
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

@WebServlet("/page/profile/password")
public class PasswordController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = ServiceFactory.getFactory().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Pages.PAGE_PREFIX + "profile_password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        Long userId = (Long) req.getSession().getAttribute("userId");
        if (UserValidator.validatePassword(password)){
            try {
                ServiceResult<String> result = userService.updateUserPassword(userId, oldPassword, password);
                if (result.getStatus() == ServiceResultStatus.SUCCESS){
                    resp.getWriter().print(result.getMessage());
                } else {
                    resp.setStatus(400);
                    resp.getWriter().print(result.getMessage());
                }
            } catch (ServiceException e) {
                resp.setStatus(500);
                resp.getWriter().print("Internal error");
            }
        } else {
            resp.setStatus(400);
            resp.getWriter().print("Incorrect request");
        }
    }
}
