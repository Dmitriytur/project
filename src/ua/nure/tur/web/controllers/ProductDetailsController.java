package ua.nure.tur.web.controllers;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.PeriodicalService;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.utils.Pages;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/magazines")
public class ProductDetailsController extends HttpServlet {

    private PeriodicalService periodicalService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        periodicalService = ServiceFactory.getFactory().getPeriodicalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int periodicalId;
        try {
            periodicalId = Integer.parseInt(req.getParameter("item"));
            Periodical periodical = periodicalService.getById(periodicalId);
            if (periodical != null){
                req.setAttribute("periodical", periodical);
                req.getRequestDispatcher(Pages.PAGE_PREFIX + "magazine_details.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e){
            resp.setStatus(400);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }


    }
}
