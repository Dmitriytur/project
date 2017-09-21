package ua.nure.tur.web.controllers;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.entities.User;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.*;
import ua.nure.tur.utils.Pages;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/page/subscribe")
public class SubscriptionController extends HttpServlet {


    private SubscriptionService subscriptionService;
    private  PeriodicalService periodicalService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        subscriptionService = ServiceFactory.getFactory().getSubscriptionService();
        periodicalService = ServiceFactory.getFactory().getPeriodicalService();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object errorMessage =  req.getSession().getAttribute("errorMessage");
        req.setAttribute("errorMessage", errorMessage);
        req.getSession().removeAttribute("errorMessage");

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String periodParam = req.getParameter("period");
        String periodicalIdParam = req.getParameter("periodicalId");

        try {
            int period = Integer.parseInt(periodParam);
            long periodicalId = Long.parseLong(periodicalIdParam);
            Periodical periodical = periodicalService.getById(periodicalId);
            req.setAttribute("periodical", periodical);
            req.setAttribute("period", period);
            req.getRequestDispatcher(Pages.PAGE_PREFIX + "subscribing.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String periodParam = req.getParameter("period");
        String periodicalIdParam = req.getParameter("periodicalId");

        try {
            int period = Integer.parseInt(periodParam);
            long periodicalId = Long.parseLong(periodicalIdParam);
            ServiceResult<String> result = subscriptionService.subscribeUserToPeriodical(userId, periodicalId, period);
            if (result.getStatus() == ServiceResultStatus.SUCCESS){
                req.getSession().setAttribute("message", result.getMessage());
                resp.sendRedirect("/page/message");
            } else {
                req.getSession().setAttribute("errorMessage", result.getMessage());
                resp.sendRedirect(String.format("/page/subscribe?period=%d&periodicalId=%d", period, periodicalId));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(400);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }
    }


}
