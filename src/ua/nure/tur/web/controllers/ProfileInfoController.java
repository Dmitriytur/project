package ua.nure.tur.web.controllers;

import ua.nure.tur.entities.UserProfile;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.services.UserService;
import ua.nure.tur.utils.Pages;
import ua.nure.tur.utils.validators.UserProfileValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/page/profile/settings")
public class ProfileInfoController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = ServiceFactory.getFactory().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String errorMessage = (String) session.getAttribute("errorMessage");
        String message = (String) session.getAttribute("message");

        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("message", message);

        session.removeAttribute("errorMessage");
        session.removeAttribute("message");
        Long userId = (Long) req.getSession().getAttribute("userId");

        try {
            UserProfile userProfile = userService.getUserProfileForUser(userId);
            req.setAttribute("profile", userProfile);
            req.getRequestDispatcher(Pages.PAGE_PREFIX + "profile_settings.jsp").forward(req, resp);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserProfile profile = new UserProfile();
        profile.setFirstName(req.getParameter("firstName"));
        profile.setLastName(req.getParameter("lastName"));
        profile.setCity(req.getParameter("city"));
        profile.setAddress(req.getParameter("address"));
        profile.setZipCode(req.getParameter("zip"));

        if (UserProfileValidator.validateProfile(profile)) {
            Long userId = (Long) req.getSession().getAttribute("userId");
            try {
                userService.setProfile(userId, profile);
                session.setAttribute("message", "Profile was updated");
            } catch (ServiceException e) {
                session.setAttribute("errorMessage", "Internal error");
            }


        } else {
            session.setAttribute("errorMessage", "Incorrect input");
        }
        resp.sendRedirect("/page/profile/settings");

    }
}
