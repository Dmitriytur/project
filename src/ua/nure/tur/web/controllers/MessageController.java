package ua.nure.tur.web.controllers;

import ua.nure.tur.utils.Pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/message")
public class MessageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object message = req.getSession().getAttribute("message");
        req.setAttribute("message", message);
        req.getSession().removeAttribute("message");

        req.getRequestDispatcher(Pages.PAGE_PREFIX + "message.jsp").forward(req, resp);
    }
}
