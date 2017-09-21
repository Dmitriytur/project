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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/page/search")
public class SearchController extends HttpServlet {


    private PeriodicalService periodicalService;

    private int defaultLimit;

    private Set<String> allowedColumnsToSort;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        periodicalService = ServiceFactory.getFactory().getPeriodicalService();

        defaultLimit = Integer.parseInt(getServletContext().getInitParameter("defaultLimit"));

        allowedColumnsToSort = new HashSet<>();
        String parameter = getServletContext().getInitParameter("allowedColumnsToSort");
        allowedColumnsToSort.addAll(Arrays.asList(parameter.split(" ")));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String sortBy = req.getParameter("sortBy");
        String order = req.getParameter("order");
        String limitParameter = req.getParameter("limit");
        String pageParameter = req.getParameter("page");
        


        if (sortBy != null && !allowedColumnsToSort.contains(sortBy)) {
            resp.setStatus(400);
            return;
        }

        boolean desc = order != null && "desc".equals(order);

        int limit = defaultLimit;
        int page = 1;
        int offset = 0;

        try {
            if (limitParameter != null) {
                limit = Integer.parseInt(limitParameter);
            }
            if (pageParameter != null) {
                page = Integer.parseInt(pageParameter);
            }
            offset = limit * (page - 1);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            return;
        }
        if (limit < 0 || offset < 0) {
            resp.setStatus(400);
            return;
        }


        try {
            List<Periodical> periodicals = periodicalService.search(name, category, sortBy, desc, limit, offset);
            req.setAttribute("periodicalsList", periodicals);
            req.getRequestDispatcher(Pages.PAGE_PREFIX + "search.jsp").forward(req, resp);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }

    }
}
