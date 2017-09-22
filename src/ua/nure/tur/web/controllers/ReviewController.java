package ua.nure.tur.web.controllers;

import ua.nure.tur.exceptions.ServiceException;
import ua.nure.tur.services.ReviewService;
import ua.nure.tur.services.ServiceFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/page/review")
public class ReviewController extends HttpServlet {

    private ReviewService reviewService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = ServiceFactory.getFactory().getReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String scoreParam = req.getParameter("score");
        String periodicalIdParam = req.getParameter("periodicalId");
        String comment = req.getParameter("comment");

        try {
            int score = Integer.parseInt(scoreParam);
            int periodicalId = Integer.parseInt(periodicalIdParam);
            reviewService.addReview(userId, periodicalId, score, comment);
            resp.sendRedirect(String.format("/page/magazines?item=%d", periodicalId));
        } catch (NumberFormatException e){
            resp.setStatus(400);
        } catch (ServiceException e) {
            resp.setStatus(500);
        }
    }
}
