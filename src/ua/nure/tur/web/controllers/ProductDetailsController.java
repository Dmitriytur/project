package ua.nure.tur.web.controllers;

import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.PeriodicalService;
import ua.nure.tur.services.ServiceFactory;
import ua.nure.tur.utils.Pages;
import ua.nure.tur.viewmodels.PeriodicalDetailsViewModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/magazines")
public class ProductDetailsController extends HttpServlet {

    private static final int SIMILAR_PERIODICALS_LIMIT = 4;

    private PeriodicalService periodicalService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        periodicalService = ServiceFactory.getFactory().getPeriodicalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long periodicalId;
        try {
            periodicalId = Integer.parseInt(req.getParameter("item"));
            PeriodicalDetailsViewModel viewModel = periodicalService.getPeriodicalDetails(periodicalId, 4);
            req.setAttribute("model", viewModel);
            req.getRequestDispatcher(Pages.PAGE_PREFIX + "magazine_details.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }


    }
}
