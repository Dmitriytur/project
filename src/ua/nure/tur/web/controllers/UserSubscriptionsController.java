package ua.nure.tur.web.controllers;


import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.services.SubscriptionService;
import ua.nure.tur.utils.Pages;
import ua.nure.tur.viewmodels.UserSubscriptionViewModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/page/profile/subscriptions")
public class UserSubscriptionsController extends HttpServlet {


    private SubscriptionService subscriptionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        subscriptionService = ServiceFactory.getFactory().getSubscriptionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        try {
            List<UserSubscriptionViewModel> subscriptions = subscriptionService.getUserSubscriptions(userId);
            req.setAttribute("subscriptions", subscriptions);
            req.getRequestDispatcher(Pages.PAGE_PREFIX + "profile_subscriptions.jsp").forward(req, resp);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }
    }
}
