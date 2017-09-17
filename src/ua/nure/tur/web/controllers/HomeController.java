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
import java.util.List;


@WebServlet("/page/home")
public class HomeController extends HttpServlet {

    private PeriodicalService periodicalService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        periodicalService = ServiceFactory.getFactory().getPeriodicalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Periodical> periodicals = periodicalService.findAll();
            req.setAttribute("periodicalsList", periodicals);
            req.getRequestDispatcher(Pages.PAGE_PREFIX + "home.jsp").forward(req, resp);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }
    }
}
