package com.javarush.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/restart")
public class RestartServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(RestartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.setAttribute("restart", "restart");
        session.removeAttribute("playerName");
        session.removeAttribute("fragments");
        session.removeAttribute("lucidity");

        logger.debug("User restarted a game. Session id: {}", session.getId());

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
